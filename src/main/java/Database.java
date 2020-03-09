import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

public class Database {

	public Database() {
		contacts = FXCollections.observableArrayList();
	}

	private ObservableList<Contact> contacts;

	public static int getNewContactId() {
		try (Connection connection = Sql.getDataSource().getConnection()) {

			try (PreparedStatement statement = connection.prepareStatement("SELECT MAX(idcontact) AS maxid FROM "+Sql.getTableName()+";")) {

				try (ResultSet results = statement.executeQuery()) {

					if(results.next()){
						return (results.getInt("maxid") + 1);
					}


				}
			}
		} catch (SQLException e) {
			Sql.parseSQLException(e);
		}
		throw new IllegalStateException();
	}

	public ObservableList<Contact> getContacts(){
		if (contacts == null){
			contacts = FXCollections.observableArrayList();
		}
		return this.contacts;
	}

	public ObservableList<Contact> pullFromRemote(){
		ObservableList<Contact> remoteList = FXCollections.observableArrayList();

		String s = "SELECT * FROM " + Sql.getTableName() + ";";

		try (Connection connection = Sql.getDataSource().getConnection()) {

			try (PreparedStatement statement = connection.prepareStatement(s)) {

				try (ResultSet results = statement.executeQuery()) {

					while (results.next()) {
						Contact cont = new Contact();
						try{
							cont.setPhone(Contact.toPhone(results.getString("phone")));
							cont.setBirth(Contact.toBirth(results.getDate("birth").toString()));
							cont.setAddress(Contact.toAddress(results.getString("address")));
							cont.setEmail(Contact.toEmail(results.getString("email")));
							cont.setName(new Contact.Name(results.getString("firstname"),results.getString("lastname")));
							cont.setId(results.getInt("idcontact"));
							remoteList.add(cont);
						} catch (SQLException e) {
							Sql.parseSQLException(e);
						}


					}

				}
			}
		} catch (SQLException e) {
			Sql.parseSQLException(e);
		}

		return remoteList;
	}

	public void startFromRemote(){

		contacts.clear();

		String s = "SELECT * FROM " + Sql.getTableName() + ";";

		try (Connection connection = Sql.getDataSource().getConnection()) {

			try (PreparedStatement statement = connection.prepareStatement(s)) {

				try (ResultSet results = statement.executeQuery()) {

					while (results.next()) {
						Contact cont = new Contact();
						try{
							cont.setPhone(Contact.toPhone(results.getString("phone")));
							cont.setBirth(Contact.toBirth(results.getDate("birth").toString()));
							cont.setAddress(Contact.toAddress(results.getString("address")));
							cont.setEmail(Contact.toEmail(results.getString("email")));
							cont.setName(new Contact.Name(results.getString("firstname"),results.getString("lastname")));
							cont.setId(results.getInt("idcontact"));
							contacts.add(cont);
						} catch (SQLException e) {
							Sql.parseSQLException(e);
						}


					}

				}
			}
		} catch (SQLException e) {
			Sql.parseSQLException(e);
		}

		if(contacts.isEmpty()){
			Error.create("It appears you haven't created any contacts yet! You can import somme dummy data, using provided insertDummyData.sql script.",ContactView.parent);
		}

	}

	public void synchronizeWithRemote() {
		ObservableList<Contact> remoteList = pullFromRemote();

		ObservableList<Contact> contactsToAddToRemote = findNotIn(contacts, remoteList);
		ObservableList<Contact> contactsToRemoveFromRemote = findNotIn(remoteList, contacts);

		if(!contactsToAddToRemote.isEmpty()){	addToRemote(contactsToAddToRemote);}
		if(!contactsToRemoveFromRemote.isEmpty()){	deleteFromRemote(contactsToRemoveFromRemote);}


	}

	public void addToRemote(ObservableList<Contact> ol){
		try (Connection connection = Sql.getDataSource().getConnection()) {

			ol.forEach(contact -> {

				try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO " + Sql.getTableName() + " (lastname, firstname, phone, address, email, birth) " +
						"VALUES (?, ?, ?, ?, ?, date(?));")) {

					stmt.setString(1, contact.getName().getLast());
					stmt.setString(2, contact.getName().getFirst());
					stmt.setString(3, contact.getPhone().get());
					stmt.setString(4, contact.getAddress().get());
					stmt.setString(5, contact.getEmail().get());
					stmt.setString(6, contact.getBirth().get());
					stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			});

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteFromRemote(ObservableList<Contact> ol){
		try (Connection connection = Sql.getDataSource().getConnection()) {

			ol.forEach(contact -> {

				try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM "+Sql.getTableName()+" WHERE (`idcontact` = ?);")) {

					stmt.setString(1, Integer.toString(contact.getId()));
					stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			});

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ObservableList<Contact> findNotIn(ObservableList<Contact> first, ObservableList<Contact> second){
		ObservableList<Contact> difference = FXCollections.observableArrayList();

		first.forEach(contact1 -> {
			AtomicReference<Boolean> found = new AtomicReference<>(false);
			second.forEach(contact2 -> {
				if(contact1.getId() == contact2.getId()){
					found.set(true);
				}
			});
			if (!found.get()){
				difference.add(contact1);
			}
		});

		return difference;
	}

	public void deleteContact(Contact c){
		contacts.remove(c);
		synchronizeWithRemote();
	}

	public void addContact(Contact c){
		contacts.add(c);
		synchronizeWithRemote();
	}

	public void updateContact(Contact selected) {
		try (Connection connection = Sql.getDataSource().getConnection()) {

			try (PreparedStatement stmt = connection.prepareStatement("UPDATE "+Sql.getTableName()+" " +
					"SET firstname=?, lastname=?,phone=?,address=?,email=?,birth=date(?) WHERE idcontact=?;")) {

				stmt.setString(1, selected.getName().getFirst());
				stmt.setString(2, selected.getName().getLast());
				stmt.setString(3, selected.getPhone().get());
				stmt.setString(4, selected.getAddress().get());
				stmt.setString(5, selected.getEmail().get());
				stmt.setString(6, selected.getBirth().get());
				stmt.setString(7, Integer.toString(selected.getId()));
				stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static class Lengths{
		static final public int PHONE = 15;
		static final public int EMAIL = 150;
		static final public int ADDRESS = 200;
		static final public int NAME = 45;
	}

}

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {

	public Database() {
		this.contacts = new ArrayList<Contact>();
	}

	private ArrayList<Contact> contacts;

	public void pullFromRemote(){

		try (ResultSet res = Sql.executeLiteralStatement("SELECT * FROM " + Sql.getTableName() + "; ")){



			while (res.next()) {
				Contact cont = new Contact();
				cont.setPhone(res.getString("phone"));
				System.out.println(cont.getPhone().get());
			}

		} catch (SQLException e) {
			Sql.parseSQLException(e);
		}
	}

	public void pushToRemote() {
	}

	public static class Lengths{
		static final public int PHONE = 15;
		static final public int EMAIL = 150;
		static final public int ADDRESS = 200;
		static final public int NAME = 45;
	}

	/* ///
	INSERT INTO contact (lastname, firstname, phone, address, email, birth)
VALUES ('DAMAY', 'Jean-Hadrien', '0768401120', '74 rue Meurein, 59800, Lille', 'jh.damay@gmail.com', date('1998-06-10'));

	/// */




}

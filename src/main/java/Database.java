import java.util.ArrayList;

public class Database {

	public Database() {
		this.contacts = new ArrayList<Contact>();
	}

	private ArrayList<Contact> contacts;

	public int length_Phone = 15;
	public int length_Email = 150;
	public int length_Address = 200;
	public int length_Name = 45;

}

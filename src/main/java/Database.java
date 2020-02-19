import java.util.ArrayList;
import java.util.List;

public class Database {

	Database() {
		this.contacts = new ArrayList<Contact>();
	}

	private ArrayList<Contact> contacts;

	public int length_Phone = 15;
	public int length_Email = 150;
	public int length_Address = 200;
	public int length_Name = 45;

}

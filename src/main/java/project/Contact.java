package project;

public class Contact {
	
	static class Regex {
		static String Phone = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";
		static String Email = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\\\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\\\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
		static String Basic = "^[a-zA-Z0-9\\ \\-\\'À-ú]+$";
		static String Word = "^[a-zA-ZÀ-ú]+$";
	}
	
	Contact(Database db){
		this.db = db;
	}

	private Database db;
	private int ID;
	private String lastName,firstName,nickName;
	private Date birthDate;
		
	public class Address{
		private String street, city, state, postal, country;
		
		Address(String street, String city, String state, String postal, String country) throws IllegalArgumentException{
			this.set(street, city, state, postal, country);
		}
		
		String get() {
			return this.toString();
		}
		
		void set(String street, String city, String state, String postal, String country) throws IllegalArgumentException {
			if (street.matches(Regex.Basic) && city.matches(Regex.Word) && state.matches(Regex.Word)
					 && postal.matches(Regex.Basic) && country.matches(Regex.Word)
					 && street.length()+city.length() + state.length() + postal.length() + country.length() <= db.length_Address){
				this.street = street;
				this.city = city;
				this.state = state;
				this.postal = postal;
				this.country = country;				
			}
			else { throw new IllegalArgumentException("Failed to match regex.");}	
		}
		
		@Override
		public String toString() {
			return this.street + "\n"
					+ this.city + ", " + this.state + " "+ this.postal + "\n"
					 + this.country;
		}
		
	}
	
	class Phone {
		private String number;   				
		
		Phone(String s) throws IllegalArgumentException{
			this.set(s);
		}
		
		String get() {
			return this.number;
		}
		
		void set(String s) throws IllegalArgumentException {
			if (s.matches(Regex.Phone) && s.length()<=db.length_Phone){
				this.number = s;
			}
			else { throw new IllegalArgumentException("Failed to match regex.");}	
		}

		
		@Override
		public String toString() {
			return this.number;
		}
		
	}
	
	class Email{
		private String email = "null";
		
		Email(String s) throws IllegalArgumentException {
			this.set(s);
		}
		
		String get() {
			return this.email;
		}
		
		void set(String s) throws IllegalArgumentException {
			if (s.matches(Regex.Email) && s.length()<=db.length_Email){
				this.email = s;
			}
			else { throw new IllegalArgumentException("Failed to match regex.");}	
		}
		
		@Override
		public String toString() {
			return this.email;
		}
		
	}
	
		
	
	
	class Date{
		
	}
	
}

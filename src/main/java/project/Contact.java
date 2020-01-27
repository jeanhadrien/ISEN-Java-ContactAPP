package project;

public class Contact {
	
	Contact(){
		
	}

	
	
	private int ID;
	private Address address;
	private String lastName,firstName,nickName;
	Phone phone = new Phone(15, "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$");
	private Email email;
	private Date birthDate;
	
	class Address{
		private String number;
		
	}
	
	class Phone{
		
		private String number = "null";   				
		private String regex = "null";
		// maxChar of number
		private int maxChar = -1;
		
		Phone(int maxchar, String regex){
			this.regex= regex;
			this.maxChar = maxchar;
		}
		
		String get() {
			return this.number;
		}
		
		Boolean set(String s) {
			if (s.matches(this.regex) && s.length()<=this.maxChar){
				this.number = s;
				return true;
			}
			else { return false;}	
		}
		
		Boolean isValid() {
			if(this.number.equals("null") || this.regex.equals("null") || this.maxChar <=0){
				return false;
			}
			else {return true;}
		}
		
		@Override
		public String toString() {
			return this.number;
		}
		
	}
	
	class Email{
		
	}
	
	class Date{
		
	}
	
}

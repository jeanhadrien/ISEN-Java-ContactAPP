public class Contact {

	static final class Regex {
		static final String PHONE = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";
		static final String EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\\\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\\\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
		static final String BASIC = "^[a-zA-Z0-9\\ \\-\\'À-ú]+$";
		static final String WORD  = "^[a-zA-ZÀ-ú]+$";
		static final String NUMBER = "^[0-9]+$";
	}

	Contact(Database db) {
		this.db = db;
	}
	
	private Database db;

	public class Name {
		private String last, first, nick;

		public String getLast() {
			return last;
		}

		public String getFirst() {
			return first;
		}

		public String getNick() {
			return nick;
		}

		public void setLast(String last) {
			if (last.matches(Regex.BASIC) && last.length()<= db.length_Name) {
				this.last = last;
			}
			else {
				throw new IllegalArgumentException("Invalid LastName");
			}
		}

		public void setFirst(String first) {
			if (first.matches(Regex.BASIC) && first.length()<= db.length_Name) {
				this.first = first;
			}
			else {
				throw new IllegalArgumentException("Invalid FirstName");
			}
		}

		public void setNick(String nick) {
			if (nick.matches(Regex.BASIC) && nick.length()<= db.length_Name) {
				this.nick = nick;
			}
			else {
				throw new IllegalArgumentException("Invalid NickName");
			}
		}
		
		@Override
		public String toString() {
			return "".concat(this.first).concat(" '").concat(this.nick).concat("' ").concat(this.last);
		}		

	}

	public class Birth {
		private int day = 0, month = 0, year = 0;

		Birth(String year, String month, String day) throws NumberFormatException, IllegalArgumentException {
			int intDay = 0, intYear = 0, intMonth = 0;
			try {
				intDay = Integer.parseInt(day.trim());
				intYear = Integer.parseInt(year.trim());
				intMonth = Integer.parseInt(month.trim());

			} catch (NumberFormatException nfe) {
				throw new NumberFormatException("Can't convert Date strings to ints");
			} finally {
				this.set(intYear, intMonth, intDay);
			}
		}

		String get() {
			return this.toString();
		}

		public void set(int year, int month, int day) {
			if (this.day > 30 || this.day <= 0 || this.month > 12 || this.month <= 0 || this.year > 2020
					|| this.year <= 1900 || (this.month == 2 && this.day > 28)) {
				throw new IllegalArgumentException("Invalid date values");
			} else {
				this.day = day;
				this.year = year;
				this.month = month;
			}
		}

		@Override
		public String toString() {
			return "".concat(Integer.toString(this.year)).concat("-").concat(Integer.toString(this.month)).concat("-")
					.concat(Integer.toString(this.day));
		}

	}

	public class Address {
		private String street, city, state, postal, country;

		Address(String street, String city, String state, String postal, String country)
				throws IllegalArgumentException {
			this.set(street, city, state, postal, country);
		}

		String get() {
			return this.toString();
		}

		public void set(String street, String city, String state, String postal, String country)
				throws IllegalArgumentException {
			if (street.matches(Regex.BASIC) && city.matches(Regex.WORD) && state.matches(Regex.WORD)
					&& postal.matches(Regex.BASIC) && country.matches(Regex.WORD) && street.length() + city.length()
							+ state.length() + postal.length() + country.length() <= db.length_Address) {
				this.street = street;
				this.city = city;
				this.state = state;
				this.postal = postal;
				this.country = country;
			} else {
				throw new IllegalArgumentException("Invalid Address");
			}
		}

		@Override
		public String toString() {
			return this.street + "\n" + this.city + ", " + this.state + " " + this.postal + "\n" + this.country;
		}

	}

	class Phone {
		private String number;

		Phone(String s) throws IllegalArgumentException {
			this.set(s);
		}

		String get() {
			return this.number;
		}

		void set(String s) throws IllegalArgumentException {
			if (s.matches(Regex.PHONE) && s.length() <= db.length_Phone) {
				this.number = s;
			} else {
				throw new IllegalArgumentException("Invalid Phone");
			}
		}

		@Override
		public String toString() {
			return this.number;
		}

	}

	class Email {
		private String email = "null";

		Email(String s) throws IllegalArgumentException {
			this.set(s);
		}

		String get() {
			return this.email;
		}

		void set(String s) throws IllegalArgumentException {
			if (s.matches(Regex.EMAIL) && s.length() <= db.length_Email) {
				this.email = s;
			} else {
				throw new IllegalArgumentException("Invalid Email");
			}
		}

		@Override
		public String toString() {
			return this.email;
		}

	}

}

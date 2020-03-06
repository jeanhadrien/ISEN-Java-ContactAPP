public class Contact {

	Contact() {
	}

	private Name name;
	private Birth birth;
	private Address address;
	private Phone phone;
	private Email email;

	public static class Name {
		private String last, first, nick;

		Name(String first, String last, String nick){
			setFirst(first);
			setLast(last);
			setNick(nick);
		}

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
			if (last.matches(Regex.BASIC) && last.length()<= Database.Lengths.NAME) {
				this.last = last;
			}
			else {
				throw new IllegalArgumentException("Invalid LastName");
			}
		}

		public void setFirst(String first) {
			if (first.matches(Regex.BASIC) && first.length()<= Database.Lengths.NAME) {
				this.first = first;
			}
			else {
				throw new IllegalArgumentException("Invalid FirstName");
			}
		}

		public void setNick(String nick) {
			if (nick.matches(Regex.BASIC) && nick.length()<= Database.Lengths.NAME) {
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

	public static class Birth {
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

	public static class Address {
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
							+ state.length() + postal.length() + country.length() <= Database.Lengths.ADDRESS) {
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

	public static class Phone {
		private String number;

		Phone(String s) throws IllegalArgumentException {
			this.set(s);
		}

		public String get() {
			return this.number;
		}

		void set(String s) throws IllegalArgumentException {
			if (s.matches(Regex.PHONE) && s.length() <= Database.Lengths.PHONE) {
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

	public static class Email {
		private String email = "null";

		Email(String s) throws IllegalArgumentException {
			this.set(s);
		}

		String get() {
			return this.email;
		}

		void set(String s) throws IllegalArgumentException {
			if (s.matches(Regex.EMAIL) && s.length() <= Database.Lengths.EMAIL) {
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

	public Name getName() {
		return name;
	}

	public void setName(String first, String last, String nick) {
		this.name = new Name(first,last,nick);
	}

	public Birth getBirth() {
		return birth;
	}

	public void setBirth(String year, String month, String day) {
		this.birth = new Birth(year, month, day);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(String street, String city, String state, String postal, String country) {
		this.address = new Address(street, city, state, postal, country);
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(String s) {
		this.phone = new Phone(s);
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(String s) {
		this.email = new Email(s);
	}

}

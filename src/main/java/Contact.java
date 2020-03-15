public class Contact {

    private Name name;
    private Birth birth;
    private Address address;
    private Phone phone;
    private Email email;
    private int id;
    Contact() {
    }

    public static Name toName(String s) {
        return new Name(s.split(" ")[0], s.split(" ")[1]);
    }

    public static Birth toBirth(String s) {
        return new Birth(s.split("-")[0], s.split("-")[1], s.split("-")[2]);
    }

    public static Address toAddress(String s) {
        return new Address(s);
    }

    public static Phone toPhone(String s) {
        return new Phone(s);
    }

    public static Email toEmail(String s) {
        return new Email(s);
    }

    public Name getName() {
        return name;
    }

    public void setName(Name n) {
        this.name = n;
    }

    public void setName(String first, String last) {
        this.name = new Name(first, last);
    }

    public Birth getBirth() {
        return birth;
    }

    public void setBirth(Birth b) {
        this.birth = b;
    }

    public void setBirth(String year, String month, String day) {
        this.birth = new Birth(year, month, day);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address ad) {
        this.address = ad;
    }

    public void setAddress(String text) {
        this.address = new Address(text);
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

    public void setPhone(Phone p) {
        this.phone = p;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(String s) {
        this.email = new Email(s);
    }

    public void setEmail(Email e) {
        this.email = e;
    }

    public int getId() {
        return id;
    }

    public void setId(int s) {
        this.id = s;
    }

    @Override
    public String toString() {
        return name.toString();
    }

    public static class Name {
        private String last, first;

        Name(String first, String last) {
            setFirst(first);
            setLast(last);

        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            if (last.matches(Regex.BASIC) && last.length() <= Database.Lengths.NAME) {
                this.last = last;
            } else {
                throw new IllegalArgumentException("Invalid LastName");
            }
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            if (first.matches(Regex.BASIC) && first.length() <= Database.Lengths.NAME) {
                this.first = first;
            } else {
                throw new IllegalArgumentException("Invalid FirstName");
            }
        }


        @Override
        public String toString() {
            return "".concat(this.first).concat(" ").concat(this.last);
        }

    }

    public static class Birth {
        private int day = 0, month = 0, year = 0;

        Birth(String year, String month, String day) throws IllegalArgumentException {
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

        public int getDay() {
            return this.day;
        }

        public int getMonth() {
            return this.month;
        }

        public int getYear() {
            return this.year;
        }

        public void set(int year, int month, int day) {

            if (day > 30 || day < 0 || month > 12 || month < 0 || year > 2020
                    || year <= 1900) {
                throw new IllegalArgumentException("Invalid date values");
            } else {
                this.day = day;
                this.year = year;
                this.month = month;
            }
        }

        @Override
        public String toString() {
            return "".concat(Integer.toString(this.year)).concat("-").concat(String.format("%1$02d", this.month)).concat("-")
                    .concat(String.format("%1$02d", this.day));
        }

    }

    public static class Address {
        private String street, city, state, postal, country;
        private String address;

        Address(String street, String city, String state, String postal, String country)
                throws IllegalArgumentException {
            this.set(street, city, state, postal, country);
        }

        Address(String s) throws IllegalArgumentException {
            this.set(s);
        }

        String get() {
            return this.toString();
        }

        public void set(String s) {
            if (s.length() <= Database.Lengths.ADDRESS) {
                this.address = s;
            } else {
                throw new IllegalArgumentException("Invalid Address");
            }
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
            if (this.address == null) {
                return this.street + "\n" + this.city + ", " + this.state + " " + this.postal + "\n" + this.country;
            } else {
                return this.address;
            }
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
}

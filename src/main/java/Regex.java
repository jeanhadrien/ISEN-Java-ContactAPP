public class Regex {

    public static final String PHONE = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";
    public static final String EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String BASIC = "^[a-zA-Z0-9\\ \\-\\'À-ú]+$";
    public static final String WORD = "^[a-zA-ZÀ-ú]+$";
    public static final String NUMBER_WHOLE = "^\\d+$";

    public static final String USERNAME = "^[a-z0-9_-]{3,16}$";
    public static final String IPV4 = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";
    public static final String SQL_DATE = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";


}

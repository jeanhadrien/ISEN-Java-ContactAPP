import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Sql {

    private static MysqlDataSource dataSource;
    private static int errorCode = 0;
    private static boolean connected = false;
    private static String table;

    public static String getTableName() { return table; }

    private static DataSource getDataSource() {
        return dataSource;
    }

    public static void init() {
    }

    public static void parseSQLException(SQLException e) {
        errorCode = e.getErrorCode();
        System.out.println(errorCode);
        e.printStackTrace();
    }

    public static boolean tryToConnect(SQLConfigWindow.Form form) {
        MysqlDataSource formDataSource = new MysqlDataSource();
        formDataSource.setServerName(form.serverField);
        formDataSource.setPort(Integer.parseInt(form.portField));
        formDataSource.setDatabaseName(form.databaseField);
        formDataSource.setUser(form.userField);
        formDataSource.setPassword(form.passwordField);

        try {
            formDataSource.setServerTimezone("UTC");

        } catch (SQLException e) {
            Error.create("Error setting timezone to UTC in datasource.","None",SQLConfigWindow.parent);
            parseSQLException(e);
            return false;
        }

        if (isConnectionValid(formDataSource)) {
            if (couldRetrieveDatabase(formDataSource.getDatabaseName(), formDataSource)) {
                table = formDataSource.getDatabaseName()+".contact";
                dataSource = formDataSource;
                return true;
            } else {
                if (errorCode == 1146) {
                    Error.create("Contact table wasn't created.","Create table using provided createTable.sql script and retry.",SQLConfigWindow.parent);
                    // TODO please create contact table using provided script.
                }
                return false;
            }


        } else {
            if (errorCode == 1045) {
                Error.create("Incorrect username or password.","Check your input.",SQLConfigWindow.parent);
                SQLConfigWindow.clearPassword();
                // remove pw
            } else if (errorCode == 1049) {
                Error.create("Couldn't find database schema.","Create schema using provided createSchema.sql script and retry using 'contactapp' in database name. ",SQLConfigWindow.parent);
                //CREATE SCHEMA Chains;
            } else {
                Error.create("Unhandled SQL Exception","None",SQLConfigWindow.parent);
            }
            return false;
        }


    }

    private static boolean isConnectionValid(DataSource ds) {
        try {
            Connection connection = ds.getConnection();
            if (connection.isClosed()) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            parseSQLException(e);
            return false;
        }
    }

    private static boolean couldRetrieveDatabase(String schema, DataSource ds) {
        try (Connection connection = ds.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement("SELECT 1 FROM " + schema + ".contact LIMIT 1;")) {
                //statement.setString(parameterIndex, x);

                try (ResultSet results = statement.executeQuery()) {
                    if (results != null) return true;
                }
            }


        } catch (SQLException ex) {
            parseSQLException(ex);
        }
        return false;
    }

    public static boolean connected() {
        return connected;
    }

    public static ResultSet executeLiteralStatement(String s) throws SQLException {
        try (Connection connection = Sql.getDataSource().getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(s)) {
                //statement.setString(parameterIndex, x);

                try (ResultSet results = statement.executeQuery()) {
                    return results;
                }
            }


        }

    }

}

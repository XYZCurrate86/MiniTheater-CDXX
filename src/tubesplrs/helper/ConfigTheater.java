package tubesplrs.helper;

public class ConfigTheater {

    private final String dbHost = "localhost";
    private final String dbPort = "3306";
    private final String dbUser = "root";
    private final String dbPass = "";
    private final String dbName = "theater";

    public String getDbHost() {
        return dbHost;
    }

    public String getDbPort() {
        return dbPort;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPass() {
        return dbPass;
    }

    public String getDbName() {
        return dbName;
    }
}

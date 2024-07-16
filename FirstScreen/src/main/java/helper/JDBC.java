package helper;
import javafx.scene.control.Alert;
import java.sql.*;
import java.time.*;
public abstract class JDBC {
    /**interface for convert lambda**/
    @FunctionalInterface
    public static interface LocalDateTimeConverter {
        LocalDateTime convert(LocalDateTime time);
    }
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    /**returns a string of the systems location**/
    private static String SystemLocation(){
        ZoneId zoneId = ZoneId.systemDefault();
        return zoneId.toString();
    }
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = " + SystemLocation();
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "sqlUser";
    private static String password = "Passw0rd!";
    public static Connection connection;
    /**opens connection to database**/
    public static void openConnection()
    {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
    /**closes connection to database**/
    public static void closeConnection() {
        try {
            connection.close();
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
    /**helper function I can call for error alerts**/
    public static void ErrorMessage(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    /**helper function for information alerts**/
    public static void InformationMessage(String title, String header){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
    /**helper function to convert a timestamp to a user's zone**/
    public static ZonedDateTime TimeStampToUserZone(Timestamp time){
        LocalDateTime tempTime = time.toLocalDateTime();
        ZonedDateTime utcZonedDateTime = tempTime.atZone(ZoneId.of("UTC"));
        ZoneId userZoneId = ZoneId.systemDefault();
        ZonedDateTime zonedTime = utcZonedDateTime.withZoneSameInstant(userZoneId);
        return zonedTime;
    }
    /**helper function to convert a localdatetime to UTC**/
    public static LocalDateTime convertToUTC(LocalDateTime localtime, ZoneId zoneId) {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localtime, zoneId);
        ZonedDateTime utcZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime utcLocalTime = utcZonedDateTime.toLocalDateTime();
        return utcLocalTime;
    }
    /**helper function to convert a time to EST**/
    public static LocalTime convertToEST(LocalTime localTime, ZoneId zoneId) {
        LocalDate currentDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(currentDate, localTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        ZonedDateTime estZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalTime estLocalTime = estZonedDateTime.toLocalTime();

        return estLocalTime;
    }
}
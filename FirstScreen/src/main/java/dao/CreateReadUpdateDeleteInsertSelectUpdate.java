package dao;

public class CreateReadUpdateDeleteInsertSelectUpdate {

//    public static void AddContact(Contact NewContact){
//        all
//    }
//    public static int insert(String contactName, String email) throws SQLException {
//        String sql = "INSERT INTO CONTACTS (Contact_Name, Email) VALUES (?, ?)";
//        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
//        ps.setString(1, contactName);
//        ps.setString(2, email);
//        int rowsAffected = ps.executeUpdate();
//        return rowsAffected;
//    }
//    public static int update(int contactID, String contactName) throws SQLException {
//        String sql = "UPDATE CONTACTS SET Contact_Name = ? WHERE Contact_ID = ?";
//        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
//        ps.setString(1, contactName);
//        ps.setInt(2, contactID);
//        int rowsAffected = ps.executeUpdate();
//        return rowsAffected;
//    }
//    public static int delete(int contactID) throws SQLException {
//        String sql = "DELETE FROM CONTACTS WHERE Contact_ID = ?";
//        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
//        ps.setInt(1, contactID);
//        int rowsAffected = ps.executeUpdate();
//        return rowsAffected;
//    }
//    public static void select() throws SQLException {
//        String sql = "SELECT * FROM CONTACTS";
//        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()){
//            int contactID = rs.getInt("Contact_ID");
//            String contactName = rs.getString("Contact_Name");
//            System.out.print(contactID + " | ");
//            System.out.print(contactName + "\n");
//        }
//    }
//    public static void select(String email) throws SQLException {
//        String sql = "SELECT * FROM CONTACTS";
//        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
//        ps.setString(1, email);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()){
//            int contactID = rs.getInt("Contact_ID");
//            String contactName = rs.getString("Contact_Name");
//            String emailFK = rs.getString("Email");
//            System.out.print(contactID + " | ");
//            System.out.print(contactName + " | ");
//            System.out.print(emailFK + "\n");
//        }
//    }
}

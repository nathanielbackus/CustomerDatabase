package dao;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDAO {
    public static ObservableList<Country> getAllCountries() {
        ObservableList<Country> countries = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM countries";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CountryID = rs.getInt("Country_ID");
                String CountryName = rs.getString("Country");
                Country country = new Country(CountryID, CountryName);
                countries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }
    public static String getCountryNameByDivisionID(int divisionID) throws SQLException {

            String countryName = null;
            String query = "SELECT country FROM countries WHERE country_id = (SELECT country_id FROM first_level_divisions WHERE division_id = ?)";

//        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
//        ps.setInt(1, contactID);
//        int rowsAffected = ps.executeUpdate();
//        return rowsAffected;

                 PreparedStatement preparedStatement = JDBC.connection.prepareStatement(query);
                preparedStatement.setInt(1, divisionID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        countryName = resultSet.getString("country");
                    }
                }

            return countryName;

    }
//        String sql = "SELECT country \n" +
//                "FROM countries \n" +
//                "WHERE country_id = (SELECT country_id FROM first_level_divisions WHERE division_id = ?);"; // we query the country id
//        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
//        ps.setInt(1, divisionID);//we write the text of the query
//        String rs = String.valueOf(ps.executeQuery());// we execute the query
//        return rs;

}

package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBaseHandlerImpl implements DataBaseHandler {
    private static final String URL = System.getenv("DATASOURCE_URL");
    private static final String USERNAME = System.getenv("DATASOURCE_USERNAME");
    private static final String PASSWORD = System.getenv("DATASOURCE_PASSWORD");

    public DataBaseHandlerImpl() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.execute("CREATE TABLE IF NOT EXISTS fibonacci (" +
                    "id SERIAL PRIMARY KEY, " +
                    "number INTEGER UNIQUE, " +
                    "result INTEGER[]);");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    @Override
    public void saveData(int number, List<Integer> data) {
        String query = "INSERT INTO fibonacci (number, result) VALUES (?, ?) " +
                "ON CONFLICT (number) DO UPDATE SET result = EXCLUDED.result;";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            Array sqlArray = connection.createArrayOf("INTEGER", data.toArray());
            statement.setInt(1, number);
            statement.setArray(2, sqlArray);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Integer> loadData(int number) {
        String query = "SELECT result FROM fibonacci WHERE number = ?;";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, number);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSetToList(resultSet);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<Integer> resultSetToList(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Array array = resultSet.getArray("result");
            if (array != null) {
                return new ArrayList<>(Arrays.asList((Integer[]) array.getArray()));
            }
        }
        return new ArrayList<>();
    }

}

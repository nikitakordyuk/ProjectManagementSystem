package features.db.entities.developer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeveloperDaoService {
    private final PreparedStatement createSt;
    private final PreparedStatement getByIdSt;
    private final PreparedStatement updateByIdSt;
    private final PreparedStatement deleteByIdSt;

    public DeveloperDaoService(Connection connection) throws SQLException{
        createSt = connection.prepareStatement(
                "INSERT INTO DEVELOPERS (FIRST_NAME, LAST_NAME, AGE, GENDER, SALARY) VALUES (?, ?, ?, ?, ?)"
        );

        getByIdSt = connection.prepareStatement("SELECT * FROM DEVELOPERS WHERE DEVELOPER_ID = ?");

        updateByIdSt = connection.prepareStatement(
                "UPDATE DEVELOPERS SET FIRST_NAME = ?, LAST_NAME = ?, AGE = ?, GENDER = ?, SALARY = ?" +
                        " WHERE DEVELOPER_ID = ?"
        );

        deleteByIdSt = connection.prepareStatement("DELETE FROM DEVELOPERS WHERE DEVELOPER_ID = ?");
    }

    public void create(String firstName, String lastName, int age, String gender, double salary) throws SQLException {
        createSt.setString(1, firstName);
        createSt.setString(2, lastName);
        createSt.setInt(3, age);
        createSt.setString(4, gender);
        createSt.setDouble(5, salary);

        createSt.executeUpdate();
    }

    public void getById(long id) throws SQLException {
        getByIdSt.setLong(1, id);

        getByIdSt.executeUpdate();
    }

    public void updateById(
            String firstName, String lastName, int age, String gender, double salary, long id) throws SQLException
    {
        updateByIdSt.setString(1, firstName);
        updateByIdSt.setString(2, lastName);
        updateByIdSt.setInt(3, age);
        updateByIdSt.setString(4, gender);
        updateByIdSt.setDouble(5, salary);
        updateByIdSt.setLong(6, id);

        updateByIdSt.executeUpdate();
    }

    public void deleteById(long id) throws SQLException {
        deleteByIdSt.setLong(1, id);

        deleteByIdSt.executeUpdate();
    }
}

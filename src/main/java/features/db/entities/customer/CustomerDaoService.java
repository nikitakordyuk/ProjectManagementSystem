package features.db.entities.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDaoService {
    private final PreparedStatement createSt;
    private final PreparedStatement getByIdSt;
    private final PreparedStatement updateByIdSt;
    private final PreparedStatement deleteByIdSt;

    public CustomerDaoService(Connection connection) throws SQLException {
        createSt = connection.prepareStatement("INSERT INTO CUSTOMERS (FIRST_NAME, LAST_NAME) VALUES (?, ?)");

        getByIdSt = connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE CUSTOMER_ID = ?");

        updateByIdSt = connection.prepareStatement(
                "UPDATE CUSTOMERS SET FIRST_NAME = ?, LAST_NAME = ? WHERE CUSTOMER_ID = ?");

        deleteByIdSt = connection.prepareStatement("DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = ?");
    }

    public void create(String firstName, String lastName) throws SQLException {
        createSt.setString(1, firstName);
        createSt.setString(2, lastName);

        createSt.executeUpdate();
    }

    public void getById(long id) throws SQLException {
        getByIdSt.setLong(1, id);

        getByIdSt.executeUpdate();
    }

    public void updateById(String firstName, String lastName, long id) throws SQLException {
        updateByIdSt.setString(1, firstName);
        updateByIdSt.setString(2, lastName);
        updateByIdSt.setLong(3, id);

        updateByIdSt.executeUpdate();
    }

    public void deleteById(long id) throws SQLException {
        deleteByIdSt.setLong(1, id);

        deleteByIdSt.executeUpdate();
    }
}

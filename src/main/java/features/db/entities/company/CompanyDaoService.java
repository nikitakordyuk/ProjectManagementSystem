package features.db.entities.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyDaoService {
    private final PreparedStatement createSt;
    private final PreparedStatement getByIdSt;
    private final PreparedStatement updateByIdSt;
    private final PreparedStatement deleteByIdSt;

    public CompanyDaoService(Connection connection) throws SQLException {
        createSt = connection.prepareStatement("INSERT INTO COMPANIES (COMPANY_NAME, IS_FAMOUS) VALUES (?, ?)");

        getByIdSt = connection.prepareStatement("SELECT * FROM COMPANIES WHERE COMPANY_ID = ?");

        updateByIdSt = connection.prepareStatement(
                "UPDATE COMPANIES SET COMPANY_NAME = ?, IS_FAMOUS = ? WHERE COMPANY_ID = ?");

        deleteByIdSt = connection.prepareStatement("DELETE FROM COMPANIES WHERE COMPANY_ID = ?");
    }

    public void create(String name, boolean isFamous) throws SQLException {
        createSt.setString(1, name);
        createSt.setBoolean(2, isFamous);

        createSt.executeUpdate();
    }

    public Company getById(long id) throws SQLException {
        getByIdSt.setLong(1, id);

        ResultSet resultSet = getByIdSt.executeQuery();
        Company company = new Company();

        while (resultSet.next()) {
            company.setName(resultSet.getString("company_name"));
            company.setFamous(resultSet.getBoolean("is_famous"));
        }

        return company;
    }

    public void updateById(String name, boolean isFamous, long id) throws SQLException {
        updateByIdSt.setString(1, name);
        updateByIdSt.setBoolean(2, isFamous);
        updateByIdSt.setLong(3, id);

        updateByIdSt.executeUpdate();
    }

    public void deleteById(long id) throws SQLException {
        deleteByIdSt.setLong(1, id);

        deleteByIdSt.executeUpdate();
    }
}

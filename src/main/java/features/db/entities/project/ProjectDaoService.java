package features.db.entities.project;

import features.db.entities.developer.Developer;

import java.sql.*;
import java.time.LocalDate;

public class ProjectDaoService {
    private final PreparedStatement createSt;
    private final PreparedStatement getByIdSt;
    private final PreparedStatement updateByIdSt;
    private final PreparedStatement deleteByIdSt;

    public ProjectDaoService(Connection connection) throws SQLException {
        createSt = connection.prepareStatement(
                "INSERT INTO PROJECTS (PROJECT_NAME, COMPLEXITY, COST, DATE_OF_CREATION) VALUES (?, ?, ?, ?)"
        );

        getByIdSt = connection.prepareStatement("SELECT * FROM PROJECTS WHERE PROJECT_ID = ?");

        updateByIdSt = connection.prepareStatement(
                "UPDATE PROJECTS SET PROJECT_NAME = ?, COMPLEXITY = ?, COST = ?, DATE_OF_CREATION = ? " +
                        "WHERE PROJECT_ID = ?"
        );

        deleteByIdSt = connection.prepareStatement("DELETE FROM PROJECTS WHERE PROJECT_ID = ?");
    }

    public void create(String name, int complexity, double cost, LocalDate dateOfCreation) throws SQLException {
        if (complexity > 10) throw new IllegalArgumentException("Set complexity <= 10");
        createSt.setString(1, name);
        createSt.setInt(2, complexity);
        createSt.setDouble(3, cost);
        createSt.setDate(4, Date.valueOf(dateOfCreation));

        createSt.executeUpdate();
    }

    public Project getById(long id) throws SQLException {
        getByIdSt.setLong(1, id);

        ResultSet resultSet = getByIdSt.executeQuery();
        Project project = new Project();

        while (resultSet.next()) {
            project.setName(resultSet.getString("project_name"));
            project.setComplexity(resultSet.getInt("complexity"));
            project.setCost(resultSet.getDouble("cost"));
            project.setDateOfCreation(resultSet.getDate("date_of_creation").toLocalDate());
        }

        return project;
    }

    public void updateById(
            String name, int complexity, double cost, LocalDate dateOfCreation, long id) throws SQLException
    {
        updateByIdSt.setString(1, name);
        updateByIdSt.setInt(2, complexity);
        updateByIdSt.setDouble(3, cost);
        updateByIdSt.setDate(4, Date.valueOf(dateOfCreation));
        updateByIdSt.setLong(5, id);

        updateByIdSt.executeUpdate();
    }

    public void deleteById(long id) throws SQLException {
        deleteByIdSt.setLong(1, id);

        deleteByIdSt.executeUpdate();
    }
}

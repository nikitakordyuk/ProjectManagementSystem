package features.db;

import features.storage.Storage;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DbQueriesHandler {
    private final PreparedStatement newDeveloperSt;
    private final PreparedStatement newProjectSt;
    private final PreparedStatement newCustomerSt;
    private final PreparedStatement developersOnProjectSalarySt;
    private final PreparedStatement developersOnProjectListSt;
    private final PreparedStatement javaDevelopersListSt;
    private final PreparedStatement middleDevelopersListSt;
    private final PreparedStatement listOfProjectsSt;

    public DbQueriesHandler(Storage storage) throws SQLException {
        Connection connection = storage.getConnection();

        newDeveloperSt = connection.prepareStatement(
                "INSERT INTO developers (first_name, last_name, age, gender, salary) VALUES(?, ?, ?, ?, ?)"
        );

        newProjectSt = connection.prepareStatement(
                "INSERT INTO projects (project_name, complexity, cost, date_of_creation) VALUES(?, ?, ?, ?)"
        );

        newCustomerSt = connection.prepareStatement(
                "INSERT INTO customers (first_name, last_name) VALUES(?, ?)"
        );

        developersOnProjectSalarySt = connection.prepareStatement(
                """
                        SELECT SUM(developers.salary) AS total_salary
                            FROM developers_projects
                                JOIN developers ON developers_projects.developer_id = developers.developer_id
                            WHERE project_id = ?;""");

        developersOnProjectListSt = connection.prepareStatement(
                """
                        SELECT developers.developer_id
                            FROM developers_projects
                                JOIN developers ON developers_projects.developer_id = developers.developer_id
                            WHERE project_id = ?;""");

        javaDevelopersListSt = connection.prepareStatement(
                """
                        SELECT developers.developer_id, first_name, last_name
                            FROM skills
                                JOIN developers ON developers_skills.developer_id = developers.developer_id
                                JOIN developers_skills ON skills.skill_id = developers_skills.skill_id
                            WHERE language_name = 'Java';""");

        middleDevelopersListSt = connection.prepareStatement("""
                        SELECT developers.developer_id, first_name, last_name
                            FROM skills
                                JOIN developers ON developers_skills.developer_id = developers.developer_id
                                JOIN developers_skills ON skills.skill_id = developers_skills.skill_id
                            WHERE skill_level = 'middle';""");

        listOfProjectsSt = connection.prepareStatement("""
                SELECT date_of_creation, project_name, COUNT(developers.developer_id) as developers_on_project
                    FROM developers_projects
                        JOIN developers ON developers_projects.developer_id= developers.developer_id
                        JOIN projects ON developers_projects.project_id= projects.project_id
                    GROUP BY(projects.project_id);""");
    }

    public boolean createNewDeveloper
            (String first_name, String last_name, int age, String gender, float salary) {

        try {
            newDeveloperSt.setString(1, first_name);
            newDeveloperSt.setString(2, last_name);
            newDeveloperSt.setInt(3, age);
            newDeveloperSt.setString(4, gender);
            newDeveloperSt.setFloat(5, salary);

            return newDeveloperSt.executeUpdate() == 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean createNewProject
            (String project_name, int complexity, float cost, LocalDateTime date_of_creation) {

        try {
            newProjectSt.setString(1, project_name);
            newProjectSt.setInt(2, complexity);
            newProjectSt.setFloat(3, cost);
            newProjectSt.setTimestamp(4, Timestamp.valueOf(date_of_creation));

            return newProjectSt.executeUpdate() == 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean createNewCustomer(String first_name, String last_name) {
        try {
            newCustomerSt.setString(1, first_name);
            newCustomerSt.setString(2, last_name);

            return newCustomerSt.executeUpdate() == 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public int developersOnProjectSalary(int id) {
        try {
            developersOnProjectSalarySt.setInt(1, id);
            ResultSet rs = developersOnProjectSalarySt.executeQuery();

            if (rs.next()) {
               return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public List<Integer> developersOnProjectList(int id) {
        List<Integer> list = new ArrayList<>();
        try {
            developersOnProjectListSt.setInt(1, id);
            ResultSet rs = developersOnProjectListSt.executeQuery();

            while(rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void javaDevelopersList() {
        try {
            ResultSet rs = javaDevelopersListSt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void middleDevelopersList() {
        try {
            ResultSet rs = middleDevelopersListSt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listOfProjects() {
        try {
            ResultSet rs = listOfProjectsSt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "Date of creation - " + rs.getTimestamp(1) +
                        ", project name - " + rs.getString(2) +
                        ", developers on project - " + rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

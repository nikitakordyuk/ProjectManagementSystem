package features.db.entities.skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SkillDaoService {
    private final PreparedStatement createSt;
    private final PreparedStatement getByIdSt;
    private final PreparedStatement updateByIdSt;
    private final PreparedStatement deleteByIdSt;

    public SkillDaoService(Connection connection) throws SQLException {
        createSt = connection.prepareStatement(
                "INSERT INTO SKILLS (language_name, skill_level) VALUES (?, ?)"
        );

        getByIdSt = connection.prepareStatement("SELECT * FROM SKILLS WHERE SKILL_ID = ?");

        updateByIdSt = connection.prepareStatement(
                "UPDATE SKILLS SET LANGUAGE_NAME = ?, SKILL_LEVEL = ? WHERE SKILL_ID = ?"
        );

        deleteByIdSt = connection.prepareStatement("DELETE FROM SKILLS WHERE SKILL_ID = ?");
    }

    public void create(String language_name, String skill_level) throws SQLException {
        createSt.setString(1, language_name);
        createSt.setString(2, skill_level);

        createSt.executeUpdate();
    }

    public void getById(long id) throws SQLException {
        getByIdSt.setLong(1, id);

        getByIdSt.executeUpdate();
    }

    public void updateById(String language_name, String skill_level, long id) throws SQLException {
        updateByIdSt.setString(1, language_name);
        updateByIdSt.setString(2, skill_level);
        updateByIdSt.setLong(3, id);

        updateByIdSt.executeUpdate();
    }

    public void deleteById(long id) throws SQLException {
        deleteByIdSt.setLong(1, id);

        deleteByIdSt.executeUpdate();
    }
}

package features;

import features.db.DbQueriesHandler;
import features.db.entities.company.CompanyDaoService;
import features.db.entities.customer.CustomerDaoService;
import features.db.entities.developer.DeveloperDaoService;
import features.db.entities.project.ProjectDaoService;
import features.db.entities.skill.SkillDaoService;
import features.prefs.Prefs;
import features.storage.DatabaseInitService;
import features.storage.Storage;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        Storage storage = Storage.getInstance();

        new DatabaseInitService().initDb(new Prefs().getString(Prefs.DB_JDBC_CONNECTION_URL));

        DbQueriesHandler handler = new DbQueriesHandler(storage);

//        handler.createNewDeveloper("Name7", "Surname7", 24, "female", 5834.68f);

//        System.out.println(handler.createNewProject(
//                "Project101", 8, 25004, LocalDateTime.parse("2021-04-03T00:00:00")));

//        System.out.println(handler.createNewCustomer("CustomerName 2000", "CustomerSurname 2000"));
//        System.out.println(handler.developersOnProjectSalary(5));

//        CompanyDaoService cds = new CompanyDaoService(storage.getConnection());
//        cds.deleteById(3);

//        CompanyDaoService companyDaoService = new CompanyDaoService(storage.getConnection());
//        System.out.println(companyDaoService.getById(2));
//
//        CustomerDaoService customerDaoService = new CustomerDaoService(storage.getConnection());
//        System.out.println(customerDaoService.getById(2));
//
//        DeveloperDaoService developerDaoService = new DeveloperDaoService(storage.getConnection());
//        System.out.println(developerDaoService.getById(2));
//
//        ProjectDaoService projectDaoService = new ProjectDaoService(storage.getConnection());
//        System.out.println(projectDaoService.getById(2));
//
//        SkillDaoService skillDaoService = new SkillDaoService(storage.getConnection());
//        System.out.println(skillDaoService.getById(2));

        storage.close();
    }
}

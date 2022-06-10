package features.storage;

import org.flywaydb.core.Flyway;

public class DatabaseInitService {
    public void initDb(String connectionUrl) {
        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, "sa", null)
                .load();

//        flyway.baseline();

        // Start the migration
        flyway.migrate();
    }
}
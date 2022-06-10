package features.db.entities.project;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Project {
    private String name;
    private int complexity;
    private double cost;
    private LocalDate dateOfCreation;
}

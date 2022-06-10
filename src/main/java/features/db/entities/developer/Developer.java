package features.db.entities.developer;

import lombok.Data;

@Data
public class Developer {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private double salary;
}

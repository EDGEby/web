package main.org.example.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Employee {
    private int id;
    private String name;
    private String lastName;
    private int age;
    private Office office;
    private Passport passport;
    private Timestamp updatedTs;
    private Timestamp createdTs;
}

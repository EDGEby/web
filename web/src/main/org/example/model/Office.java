package main.org.example.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Office {
    private int id;
    private String title;
    private String address;
    private String phone1;
    private String phone2;
    private int postalCode;
    private Timestamp updatedTS;
    private Timestamp createdTS;
}
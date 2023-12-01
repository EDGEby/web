package main.org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passport {

    public Passport( int id){
        this.id=id;
    }
    private int id;
    private String personalID;
    private String indID;
    private Date expTS;
    private Timestamp createdTS;
}

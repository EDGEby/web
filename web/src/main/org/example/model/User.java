package main.org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private String name;
    private String email;
    private String pwd;
    private String details;

    private  Boolean isActive; //
    private Role role;

    private Timestamp createdTs;
    private Timestamp updateTs;


}

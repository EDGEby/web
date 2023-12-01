package main.org.example.jdbs.dao.abs;

import main.org.example.model.Office;


import java.util.Set;

public interface OfficeDAO {
    boolean createOffice(Office office);
    Office findById(int id);
    boolean deleteById(int id);
    boolean updateOffice(Office office);
    Set<Office> all();
}

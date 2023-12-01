package main.org.example.jdbs.dao.abs;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.Set;

public interface AbstractDAO<T, K> {
    // CRUD operations
    boolean create(T type);
    T findById(K key);
    boolean deleteById(K key);
    boolean update(T type);
    Set<T> all();

    default T map(T t, ResultSet rs) {
        try {
            for(Field field : t.getClass().getDeclaredFields()) {
                String name = field.getName();
                String type = field.getType().getSimpleName();
                Object value = null;

                switch (type) {
                    case "String":
                        value = rs.getString(name);
                        break;
                    case "int":
                        value = rs.getInt(name);
                        break;

                    case "double":
                        value = rs.getDouble(name);
                        break;
                    //TODO - add missed types

                    default: {
                        System.out.println("No implementation for type " + type);
                        break;
                    }
                }


                for (Method method : t.getClass().getDeclaredMethods()) {
                    if (method.getName().toLowerCase().startsWith("set" + name.toLowerCase())) {
                        method.invoke(t, value);
                        break;
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }
}
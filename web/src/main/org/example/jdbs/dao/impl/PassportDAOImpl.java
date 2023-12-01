package main.org.example.jdbs.dao.impl;

import main.org.example.jdbs.dao.abs.PassportDAO;
import main.org.example.model.Passport;
import main.org.example.util.DBUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class PassportDAOImpl implements PassportDAO {
    @Override
    public boolean createPassport(Passport passport) {
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO `passport` ( `personal_id`, `ind_id`, `exp_ts`, `created_ts`) VALUES " +
                    "( '" + passport.getPersonalID() + "', '" + passport.getIndID() + "' , '" + passport.getExpTS() +
                    "', CURRENT_DATE)";
            int count = statement.executeUpdate(sql);
            return count == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int createPassport2(Passport passport) {
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO `passport` (`personal_id`, `ind_id`, `exp_ts`, `created_ts`) VALUES " +
                    "('" + passport.getPersonalID() + "', '" + passport.getIndID() + "' , '" + passport.getExpTS() +
                    "', CURRENT_DATE)";
            int count = statement.executeUpdate(sql);
            if ( count == 1){
                ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID()");
                if (rs.next()){
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public Passport findById(int id) {
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM passport where id =" + id);
            if (rs.next()) {
                Passport passport = new Passport();
                passport.setId(id);
                passport.setPersonalID(rs.getString("personal_id"));
                passport.setIndID(rs.getString("ind_id"));
                passport.setExpTS(rs.getDate("exp_ts"));
                passport.setCreatedTS(rs.getTimestamp("created_ts"));
                return passport;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM passport where id =" + id);
            if (findById(id) == null) {
                return true;
            }
            else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updatePassport(Passport passport) {
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("UPDATE passport SET personal_id = '" + passport.getPersonalID() + "', ind_id = '" + passport.getIndID() + "', exp_ts = " +
                    "'" + passport.getExpTS() + "', created_ts = '" + passport.getCreatedTS() + "'  WHERE id = " + passport.getId());
            if (findById(passport.getId()).equals(passport)) {
                return true;
            }
            else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Passport> all() {
        Set<Passport> passports = new HashSet<>();
        Connection connection = DBUtils.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM passport");
            while (rs.next()) {
                Passport passport = new Passport();
                passport.setId(rs.getInt("id"));
                passport.setPersonalID(rs.getString("personal_id"));
                passport.setIndID(rs.getString("ind_id"));
                passport.setExpTS(rs.getDate("exp_ts"));
                passport.setCreatedTS(rs.getTimestamp("created_ts"));
                passports.add(passport);
            }
            return passports;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.prokopchuk.util;

import org.prokopchuk.Company;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyParser {
    public static Company parser(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String headquarters = rs.getString("headquarters");
        int year = rs.getInt("founding_year");
        int number_of_employees = rs.getInt("number_of_employees");
        return new Company(id, name, headquarters, year, number_of_employees);
    }
}

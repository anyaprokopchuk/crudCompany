package org.prokopchuk.repo;

import lombok.extern.slf4j.Slf4j;
import org.prokopchuk.Company;
import org.prokopchuk.Connector;
import org.prokopchuk.util.CompanyParser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class CompanyRepository implements CRUD<Company> {
    private final static String INSERT = "Insert into companies (id, name, headquarters, founding_year, number_of_employees) " +
            "values (?, ?, ?, ?, ?)";
    private final static String DELETE = "Delete from companies where id = (?)";
    private final static String SELECT = "Select * from companies";
    private final static String SELECT_BY_ID = "Select * from companies where id = (?)";

    Connector connector = new Connector();

    @Override
    public int insert(Company company) {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(INSERT);
            statement.setInt(1, company.getId());
            statement.setString(2, company.getName());
            statement.setString(3, company.getHeadquarters());
            statement.setInt(4, company.getFounding_year());
            statement.setInt(5, company.getNumber_of_employees());
            int update = statement.executeUpdate();
            log.info("Row {} successfully updated", update);
            connector.closeConnection();
            return update;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert query: " + e.getMessage());
        }
    }

    @Override
    public List<Company> readAll() {
        List<Company> list = new ArrayList<>();
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(SELECT);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(CompanyParser.parser(rs));
            }
            log.info("Select successfully");
            connector.closeConnection();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Select failed" + e.getMessage());
        }
    }

    @Override
    public Optional<Company> readById(int id) {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            log.info("Selecting by id {} successfully", id);
            connector.closeConnection();
            if(rs.next()) {
                return Optional.of(CompanyParser.parser(rs));
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Select by id failed " + e.getMessage());
        }
    }

    @Override
    public int updateById(int id) {
        return 0;
    }

    @Override
    public void deleteById(int id) {
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement(DELETE);
            statement.setInt(1, id);
            statement.executeUpdate();
            log.info("Row with id " + id + " delete successfully");
            connector.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete query " + e.getMessage());
        }
    }
}

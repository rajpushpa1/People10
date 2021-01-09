package com.example.daoimpl;

import com.example.controller.CustomerController;
import com.example.dao.CustomerDao;
import com.example.pojo.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;

@Repository
public class CustomerDaoImpl extends JdbcDaoSupport implements CustomerDao {

    private static final Logger logger = LogManager.getLogger(CustomerDaoImpl.class);

    @Autowired
    private DataSource dataSource;
    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    private final String INSERT_SQL = "INSERT INTO customer(firstname, lastname, username, password, email, dob) values(?, ?, ?, ?, ? ,?)";

    private final String GETCUSTOMER_SQL = "SELECT id, firstname, lastname, username, password, email, dob FROM customer WHERE id = ?";

    @Override
    public void insertEmployee(Customer customer) {


            getJdbcTemplate().update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(INSERT_SQL);

                    ps.setString(1, customer.getFirstName());
                    ps.setString(2, customer.getLastName());
                    ps.setString(3, customer.getUserName());
                    ps.setString(4, customer.getPassword());
                    ps.setString(5, customer.getEmail());
                    ps.setDate(6, customer.getDob());
                    return ps;
                }
            });




    }

    @Override
    public Customer getEmployeeById(String empId) {
        Customer customer = new Customer();
    try {
         getJdbcTemplate().queryForObject(GETCUSTOMER_SQL, new Object[]{empId}, new RowMapper<Customer>() {
        @Override
        public Customer mapRow(ResultSet rs, int rwNumber) throws SQLException {

            customer.setId(rs.getString("id"));
            customer.setFirstName(rs.getString("firstname"));
            customer.setLastName(rs.getString("lastname"));
            customer.setUserName(rs.getString("username"));
            customer.setPassword(rs.getString("password"));
            customer.setEmail(rs.getString("email"));
            customer.setDob(rs.getDate("dob"));
            return customer;
        }
    });

        }catch (Exception e){
            logger.error("Exception ====" + e);
             return null; }
        return customer;
    }

}

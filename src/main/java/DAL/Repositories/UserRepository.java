/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Repositories;


import DAL.DataAccessObject;
import Domain.User;
import Domain.UserRole;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Repository for users
 * @author azurwular
 */
public class UserRepository
{
    
    public boolean userExists(String email) throws SQLException, ClassNotFoundException
    {
        boolean result;
        
        // Query for the user
        PreparedStatement insertStatement;
        
        ResultSet rs;
        String insertSQL = "SELECT * from user where email = ?";

        Connection connection = DataAccessObject.getConnection();

        insertStatement = connection.prepareStatement(insertSQL);
        insertStatement.setString(1, email);

        rs = insertStatement.executeQuery();

        // If the user exists, there will be one result
        result = rs.next();

        DataAccessObject.releaseConnection(connection);

        return result;
    }
    
    public boolean emailExists(int userId, String email) throws SQLException, ClassNotFoundException
    {
        boolean result;
        
        // Query for users other than the one with the given id
        // that have the given email
        PreparedStatement insertStatement;
        
        ResultSet rs;
        String insertSQL = "SELECT * from user where email = ? AND id != ?";

        Connection connection = DataAccessObject.getConnection();

        insertStatement = connection.prepareStatement(insertSQL);
        insertStatement.setString(1, email);
        insertStatement.setInt(2, userId);

        rs = insertStatement.executeQuery();

        // If there are results, return true
        result = rs.next();

        DataAccessObject.releaseConnection(connection);

        return result;
    }
    
    /**
     * Gets user from database
     * @param email
     * @param password
     * @return
     * @throws SQLException
     * @throws NullPointerException 
     */
    
    public User get(String email, String password) throws SQLException, NullPointerException, Exception 
    {
        // Hash the password
        String hashedSaltedPassword = PasswordUtils.getSaltedHash(password);
        
        // Query for the user
        PreparedStatement insertStatement;
        ResultSet rs;
        User user = null;
        String insertSQL = "SELECT * from user where email = ? AND password = ?";
        
        Connection connection = DataAccessObject.getConnection();
        
        insertStatement = connection.prepareStatement(insertSQL);
        insertStatement.setString(1, email);
        insertStatement.setString(2, hashedSaltedPassword);

        rs = insertStatement.executeQuery();

        if (rs.next())
        {
            String storedPasswordHash = rs.getString("password");
            if (!hashedSaltedPassword.equals(storedPasswordHash))
            {
                user = null;
            }
            else
            {
                user = new User(
                    rs.getInt("id"),
                    UserRole.valueOf(rs.getString("role")),
                    "",
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("zipcode"),
                    rs.getString("city"),
                    rs.getString("country"));
            }
        }
        
        DataAccessObject.releaseConnection(connection);
        
        return user;
    }
    
    /**
     * Gets user from database
     * @param id
     * @return
     * @throws SQLException
     * @throws NullPointerException 
     */
    
    public User get(int id) throws SQLException, NullPointerException, Exception 
    {
        // Query for the user
        PreparedStatement selectStatement;
        ResultSet rs;
        User user = null;
        String insertSQL = "SELECT * from user where id = ?";
        
        Connection connection = DataAccessObject.getConnection();
        
        selectStatement = connection.prepareStatement(insertSQL);
        selectStatement.setInt(1, id);

        rs = selectStatement.executeQuery();

        if (rs.next())
        {
            user = new User(
                rs.getInt("id"),
                UserRole.valueOf(rs.getString("role")),
                "",
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("address"),
                rs.getString("zipcode"),
                rs.getString("city"),
                rs.getString("country"));
        }
        
        DataAccessObject.releaseConnection(connection);
        
        return user;
    }
    
    //
    public int create(User newUser) throws SQLException, Exception
    {
        // Hash password before storing
        String passwordHash = PasswordUtils.getSaltedHash(newUser.getPassword());
        
        PreparedStatement insertStatement;
        
        try
        {
            Connection connection = DataAccessObject.getConnection();
        
            // Execute insert
            String insertSQL = "insert into user(first_name, last_name, email, password, role) values(?,?,?,?,?)";
            insertStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, newUser.getFirstName());
            insertStatement.setString(2, newUser.getLastName());
            insertStatement.setString(3, newUser.getEmail());
            insertStatement.setString(4, passwordHash);
            insertStatement.setString(5, newUser.getRole().toString());

            insertStatement.executeUpdate();
            
            // Get autogenerated id of new user
            ResultSet rs = insertStatement.getGeneratedKeys();
            rs.next();
            int newUserId = rs.getInt(1);
            
            DataAccessObject.releaseConnection(connection);
            
            return newUserId;
        }
        catch(SQLException ex)
        {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public void update (User user) throws SQLException, ClassNotFoundException
    {
        PreparedStatement insertStatement;
        
        try
        {
            Connection connection = DataAccessObject.getConnection();
        
            // Execute insert
            String insertSQL = "update user set first_name = ?, last_name = ?, email = ?, phone = ?, address = ?, zipcode = ?, "
                    + "city = ?, country = ? where id = ?";
            insertStatement = connection.prepareStatement(insertSQL);
            insertStatement.setString(1, user.getFirstName());
            insertStatement.setString(2, user.getLastName());
            insertStatement.setString(3, user.getEmail());
            insertStatement.setString(4, user.getPhone());
            insertStatement.setString(5, user.getAddress());
            insertStatement.setString(6, user.getZipcode());
            insertStatement.setString(7, user.getCity());
            insertStatement.setString(8, user.getCountry());
            insertStatement.setInt(9,user.getId());
            

            insertStatement.executeUpdate();
            
            DataAccessObject.releaseConnection(connection);
        }
        catch(SQLException ex)
        {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
}

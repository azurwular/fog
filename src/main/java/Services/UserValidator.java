/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAL.Repositories.UserRepository;
import Domain.User;
import Web.DTO.ValidationResult;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validation methods for user
 *
 * @author azurwular
 */
public class UserValidator
{
    private final UserRepository userRepository;
    
    public UserValidator()
    {
        this.userRepository = new UserRepository();
    }
    
    /**
     * Validates a user signup
     * @param email
     * @param password
     * @param passwordConfirmation
     * @return 
     * @throws java.sql.SQLException 
     * @throws java.lang.ClassNotFoundException 
     */
    public ValidationResult validateSignup(String email, String password, String passwordConfirmation) throws SQLException, ClassNotFoundException
    {
        ValidationResult result = new ValidationResult();
        
        validateEmail(result, email);
        
        try
        {
            // Check if another user with this email already exists
            if (this.userRepository.userExists(email))
            {
                result.errors.put("email", "The user already exists");
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(UserValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (password == null || password.isEmpty())
        {
            result.errors.put("password", "Password can't be empty");
        }
        
        if (passwordConfirmation == null || passwordConfirmation.isEmpty())
        {
            result.errors.put("passwordConfirmation", "Password confirmation can't be empty");
        }
        
        if (password != null && passwordConfirmation !=null && !password.equals(passwordConfirmation))
        {
            result.errors.put("password", "Password doesn't match password confirmation");
        }
        
        if (result.errors.isEmpty())
        {
            result.isValid = true;
        }
        
        return result;
    }
    
    /**
     * Validates a user login
     * @param email
     * @param password
     * @return 
     */
    public ValidationResult validateLogin(String email, String password) throws SQLException, ClassNotFoundException
    {
        ValidationResult result = new ValidationResult();
        
        if (email == null || email.isEmpty())
        {
            result.errors.put("email", "Email can't be empty");
        }
        
        if (password == null || password.isEmpty())
        {
            result.errors.put("password", "Password can't be empty");
        }
        
        if (result.errors.isEmpty())
        {
            result.isValid = true;
        }
        
        return result;
    }
    
    
    public ValidationResult validateEdit(User user)
    {
        ValidationResult result = new ValidationResult();
        
        validateEmail(result, user.getEmail());
        
        try
        {
            // Check if another user with this email already exists
            if (this.userRepository.emailExists(user.getId(), user.getEmail()))
            {
                result.errors.put("email", "This email is already used");
            }
        } catch (SQLException | ClassNotFoundException ex)
        {
            Logger.getLogger(UserValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (result.errors.isEmpty())
        {
            result.isValid = true;
        }
        
        return result;
    }
    
    private void validateEmail(ValidationResult result, String email)
    {
        if (email == null || email.isEmpty())
        {
            result.errors.put("email", "Email can't be empty");
        }
        else
        {
            Pattern pattern = Pattern.compile("^.+@.+\\..+$");
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches())
            {
                result.errors.put("email", "Email is not valid");
            }
        }
    }
}
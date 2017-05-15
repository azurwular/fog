/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAL.Repositories.UserRepository;
import Domain.User;
import Domain.UserRole;
import Services.UserValidator;
import Web.DTO.SessionKeys;
import Web.DTO.UserSessionDto;
import Web.DTO.ValidationResult;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author azurwular
 */

@WebServlet(name = "signup", urlPatterns = {"/signup"})
public class SignUpServlet extends BaseServlet
{
    private UserValidator userValidator;
    private UserRepository userRepository;
    
    public SignUpServlet()
    {
        this.userValidator = new UserValidator();
        this.userRepository = new UserRepository();
    }
    
    /**
     * Returns the sign up form
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        super.forward("/views/user/signup.jsp", request, response);
    }

    /**
     * Registers a user
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            // Get parameters from signup
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String passwordConfirmation = request.getParameter("passwordConfirmation");
            
            // Validate request
            ValidationResult validationResult = this.userValidator.validateSignup(email, password, passwordConfirmation);
            
            if (!validationResult.isValid)
            {
                request.setAttribute("errors", validationResult.errors);
                super.forward("/views/user/signup.jsp", request, response);
                return;
            }
            
            // Store the new user
            User newUser = new User(firstName, lastName, email, password, UserRole.Registered);
            
            try
            {
                newUser.setId(this.userRepository.create(newUser));
            } catch (Exception ex)
            {
                Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Login the user
            HttpSession session = request.getSession();
            UserSessionDto sessionUser = new UserSessionDto(newUser);
            session.setAttribute(SessionKeys.user, sessionUser);
            
            // Redirect to home
            response.sendRedirect("/");
        } catch (SQLException ex)
        {
            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

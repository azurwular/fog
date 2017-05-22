/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAL.Repositories.UserRepository;
import Domain.User;
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

@WebServlet(name = "editprofile", urlPatterns = {"/user/profile"})
public class EditProfileServlet extends BaseServlet
{
    private final UserValidator userValidator;
    private final UserRepository userRepository;
    
    public EditProfileServlet()
    {
        this.userValidator = new UserValidator();
        this.userRepository = new UserRepository();
    }
    
    /**
     * Returns the edit profile form
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
        super.forward("/views/user/edit.jsp", request, response);
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
        // Get parameters from edit
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String zipcode = request.getParameter("zipcode");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        
        // Map the new user data to a new User instance
        HttpSession session = request.getSession();
        UserSessionDto sessionUser = (UserSessionDto) session.getAttribute(SessionKeys.user);
        User updatedUser = new User(sessionUser.getId(), sessionUser.getRole(), password, firstName, lastName, email, phone, address, zipcode, city, country);
        
        // Validate request
        ValidationResult validationResult = this.userValidator.validateEdit(updatedUser);
        if (!validationResult.isValid())
        {
            request.setAttribute("errors", validationResult.getErrors());
            super.forward("/views/user/edit.jsp", request, response);
            return;
        }
        
        // Update the user and the session
        try
        {
            userRepository.update(updatedUser);
            sessionUser = new UserSessionDto(updatedUser);
            session.setAttribute(SessionKeys.user, sessionUser);
        } catch (SQLException | ClassNotFoundException ex)
        {
            Logger.getLogger(EditProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        // Redirect to home
        response.sendRedirect("/");
    }
}

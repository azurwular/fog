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
@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends BaseServlet
{
    private final UserValidator userValidator;
    private final UserRepository userRepository;
    
    public LoginServlet()
    {
        this.userValidator = new UserValidator();
        this.userRepository = new UserRepository();
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
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
        super.forward("/views/user/login.jsp", request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
            // Get username and password from request
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            // Validate request
            ValidationResult validationResult = this.userValidator.validateLogin(email, password);
            
            if (!validationResult.isValid)
            {
                // TODO: Find a way to send errors back
                request.setAttribute("errors", validationResult.errors);
                super.forward("/views/user/login.jsp", request, response);
                //response.sendRedirect("/login");
                return;
            }
            
            // Get user from repository
            User user = null;
            
            try
            {
                // Check if the user exists in the repository
                user = this.userRepository.get(email, password);
            } catch (Exception ex)
            {
                response.sendRedirect("/error");
                return;
            }
            
            if (user == null)
            {
                
                response.sendRedirect("/login");
                return;
            }
            
            // At this point the user exists so we store him to the session
            HttpSession session = request.getSession();
            UserSessionDto sessionUser = new UserSessionDto(user);
            session.setAttribute(SessionKeys.user, sessionUser);
            
            // Redirect to front page
            response.sendRedirect("/");
        } catch (SQLException | ClassNotFoundException ex)
        {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
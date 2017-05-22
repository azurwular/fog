/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAL.Repositories.OrderRepository;
import Domain.Order;
import Services.ShoppingCartService;
import Web.DTO.SessionKeys;
import Web.DTO.ShoppingCartDto;
import Web.DTO.UserSessionDto;
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
@WebServlet(name = "shoppingCart", urlPatterns = {"/shoppingCart"})
public class ShoppingCartServlet extends BaseServlet
{
    private final ShoppingCartService shoppingCartService;
    private final OrderRepository orderRepository;
    
    public ShoppingCartServlet()
    {
        this.shoppingCartService = new ShoppingCartService();
        this.orderRepository = new OrderRepository();
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        super.forward("/views/shopping/cart.jsp", request, response);
    }

    /**
     * Submits an order for the logged in user
     *
     * @param request servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        
        // Get logged in user; we will save the order under her
        UserSessionDto sessionUser = (UserSessionDto) session.getAttribute(SessionKeys.user);
        ShoppingCartDto shoppingCart = (ShoppingCartDto) session.getAttribute(SessionKeys.shoppingCart);
        
        if (sessionUser == null || shoppingCart == null || shoppingCart.getCarports().isEmpty())
        {
            super.forward("/views/shopping/cart.jsp", request, response);
        }
        
        // Create an order for the current user
        Order newOrder = this.shoppingCartService.createOrder(sessionUser.getId(), shoppingCart);
        
        if (newOrder == null)
        {
            return;
        }
        
        try {
            // Save the order
            this.orderRepository.create(newOrder);
            
            // Reset the shopping cart
            session.setAttribute(SessionKeys.shoppingCart, null);
            
            // Set a success message
            request.setAttribute("success", "Your order was placed!");
        } catch (SQLException | NullPointerException | ClassNotFoundException ex) {
            Logger.getLogger(ShoppingCartServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "Oops, something wrong happened, please try again");
        }
        super.forward("/index.jsp", request, response);
    }
}
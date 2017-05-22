/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAL.Repositories.ProductPartRepository;
import Domain.ProductPart;
import Domain.RoofType;
import Services.CarportValidator;
import Services.ShoppingCartService;
import Web.DTO.ProductPartDto;
import Web.DTO.SessionKeys;
import Web.DTO.ShoppingCartDto;
import Web.DTO.ValidationResult;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "carportBuild", urlPatterns = {"/carport/build"})
public class BuildCarportServlet extends BaseServlet
{
    private final CarportValidator carportValidator;
    private final ProductPartRepository productPartRepository;
    private final ShoppingCartService shoppingCartService;
    
    public BuildCarportServlet()
    {
        this.productPartRepository = new ProductPartRepository();
        this.carportValidator = new CarportValidator();
        this.shoppingCartService = new ShoppingCartService();
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
        prepareView(request);
        super.forward("/views/carport/build.jsp", request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Get parameters from build carport form
        RoofType roofType = RoofType.values()[Integer.parseInt(request.getParameter("roofType"))]; //cast to int, then use as enum index
        request.setAttribute("selectedRoofType", roofType.toString());
        int woodId = 0;
        int roofSkeletonId = 0;
        int fittingsScrewsId = 0;
        int woodRoofingId = 0;
        int woodQuantity = 0;
        int fittingsScrewsQuantity = 0;
        int woodRoofingQuantity = 0;
        
        if (request.getParameter("woodId") != null)
        {
            woodId = Integer.parseInt(request.getParameter("woodId"));
        }
        if (request.getParameter("roofSkeletonId") != null)
        {
            roofSkeletonId = Integer.parseInt(request.getParameter("roofSkeletonId"));
        }
        if (request.getParameter("fittingsScrewsId") != null)
        {
            fittingsScrewsId = Integer.parseInt(request.getParameter("fittingsScrewsId"));
        }
        if (request.getParameter("woodRoofingId") != null)
        {
            woodRoofingId = Integer.parseInt(request.getParameter("woodRoofingId"));
        }
        if (request.getParameter("woodQuantity") != null)
        {
            woodQuantity = Integer.parseInt(request.getParameter("woodQuantity"));
        }
        if (request.getParameter("fittingsScrewsQuantity") != null)
        {
            fittingsScrewsQuantity = Integer.parseInt(request.getParameter("fittingsScrewsQuantity"));
        }
        if (request.getParameter("woodRoofingQuantity") != null)
        {
            woodRoofingQuantity = Integer.parseInt(request.getParameter("woodRoofingQuantity"));
        }
        
        try {
            ValidationResult validationResult = this.carportValidator.validateAddToCart(
                    roofType,
                    woodId,
                    roofSkeletonId,
                    fittingsScrewsId,
                    woodRoofingId,
                    woodQuantity,
                    fittingsScrewsQuantity,
                    woodRoofingQuantity);
            
            if (!validationResult.isValid())
            {
                request.setAttribute("errors", validationResult.getErrors());
                
                // Prepopulate user's selected options
                request.setAttribute("selectedWood", woodId);
                request.setAttribute("selectedWoodRoofing", woodRoofingId);
                request.setAttribute("selectedRoofSkeleton", roofSkeletonId);
                request.setAttribute("selectedFittingsScrews", fittingsScrewsId);
                
                request.setAttribute("selectedWoodQuantity", woodQuantity);
                request.setAttribute("selectedWoodRoofingQuantity", woodRoofingQuantity);
                request.setAttribute("selectedFittingsScrewsQuantity", fittingsScrewsQuantity);
                
                prepareView(request);
                super.forward("/views/carport/build.jsp", request, response);
                return;
            }
            
            // At this point, the submission is valid so we get/create the cart and add the item in it
            HttpSession session = request.getSession();
            ShoppingCartDto shoppingCart = (ShoppingCartDto) session.getAttribute(SessionKeys.shoppingCart);
            
            shoppingCart = this.shoppingCartService.AddToCart(
                    shoppingCart,
                    roofType,
                    woodId,
                    roofSkeletonId,
                    fittingsScrewsId,
                    woodRoofingId,
                    woodQuantity,
                    fittingsScrewsQuantity,
                    woodRoofingQuantity);
            
            session.setAttribute(SessionKeys.shoppingCart, shoppingCart);
            response.sendRedirect("/shoppingCart");
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BuildCarportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void prepareView(HttpServletRequest request)
    {
        try {
            // Get all available products
            List<ProductPart> availableProductParts = this.productPartRepository.getAll();
            
            // Store the products on the response as attribute
            List<ProductPartDto> woodDtos = new ArrayList<>();
            List<ProductPartDto> woodRoofingDtos = new ArrayList<>();
            List<ProductPartDto> roofSkeletonDtos = new ArrayList<>();
            List<ProductPartDto> fittingsScrewsDtos = new ArrayList<>();
            
            for(ProductPart productPart : availableProductParts)
            {
                switch (productPart.getType())
                {
                    case Wood:
                        woodDtos.add(new ProductPartDto(productPart));
                        break;
                    case WoodRoofing:
                        woodRoofingDtos.add(new ProductPartDto(productPart));
                        break;
                    case RoofSkeleton:
                        roofSkeletonDtos.add(new ProductPartDto(productPart));
                        break;
                    case FittingsScrews:
                        fittingsScrewsDtos.add(new ProductPartDto(productPart));
                        break;
                    default:
                        break;
                }
            }
            
            // Store all dtos in request atributes
            request.setAttribute("woods", woodDtos);
            request.setAttribute("woodRoofings", woodRoofingDtos);
            request.setAttribute("roofSkeletons", roofSkeletonDtos);
            request.setAttribute("fittingsScrews", fittingsScrewsDtos);
            request.setAttribute("roofTypes", RoofType.values());
            
        } catch (SQLException | NullPointerException | ClassNotFoundException ex) {
            Logger.getLogger(BuildCarportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
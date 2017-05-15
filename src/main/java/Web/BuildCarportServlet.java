/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAL.Repositories.ProductPartRepository;
import Domain.ProductPart;
import Domain.ProductPartType;
import Domain.RoofType;
import Web.DTO.ProductPartDto;
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

/**
 *
 * @author azurwular
 */
@WebServlet(name = "carportBuild", urlPatterns = {"/carport/build"})
public class BuildCarportServlet extends BaseServlet
{
    private final ProductPartRepository productPartRepository;
    
    public BuildCarportServlet()
    {
        this.productPartRepository = new ProductPartRepository();
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
        try {
            // Get all available products
            List<ProductPart> availableProductParts = this.productPartRepository.getAll();
            
            // Store the products on the response as attribute
            List<ProductPartDto> availableProductPartsDtos = new ArrayList<>();
            for(ProductPart productPart : availableProductParts)
            {
                availableProductPartsDtos.add(new ProductPartDto(productPart));
            }
            
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
            
            super.forward("/views/carport/build.jsp", request, response);
            
        } catch (SQLException | NullPointerException | ClassNotFoundException ex) {
            Logger.getLogger(BuildCarportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {
    }
}

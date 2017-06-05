/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAL.Repositories.ProductPartRepository;
import Domain.ProductPart;
import Domain.RoofType;
import Web.DTO.ValidationResult;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azurwular
 */
public class CarportValidator
{
    private final ProductPartRepository productPartRepository;
    
    public CarportValidator()
    {
        this.productPartRepository = new ProductPartRepository();
    }
    
    /**
    * Validates the carport add to cart request
    * 
    * @param roofType
    * @param woodId
    * @param roofSkeletonId
    * @param fittingsScrewsId
    * @param woodRoofingId
    * @param woodQuantity
    * @param fittingsScrewsQuantity
    * @param woodRoofingQuantity
    * @return
    * @throws SQLException
    * @throws ClassNotFoundException 
    */
    public ValidationResult validateAddToCart(
            RoofType roofType,
            int woodId,
            int roofSkeletonId,
            int fittingsScrewsId,
            int woodRoofingId,
            int woodQuantity,
            int fittingsScrewsQuantity,
            int woodRoofingQuantity) throws SQLException, ClassNotFoundException
    {
        ValidationResult result;
        result = new ValidationResult();
        
        // Ids that are 0 means that the frontend hasn't sent any values for them
        switch (roofType)
        {
            case Flat:
                validateFlat(result, woodRoofingId, woodRoofingQuantity);
                break;
            case Triangle:
                validateTriangle(result, woodId, woodQuantity, roofSkeletonId);
                break;
        }
        //get the fittingsScrews from the DB
        ProductPart fittingsScrews = this.productPartRepository.get(fittingsScrewsId);
        
        if (fittingsScrews == null)
        {
            result.getErrors().put("fittingsScrewsId", "Fittings/screws provided doesn't exist");
        }
        else
        {
            if (fittingsScrewsQuantity > fittingsScrews.getStock())
            {
                result.getErrors().put("fittingsScrewsQuantity", "Not enough stock, please order less fittings/screws");
            }
        }
        
        if (result.getErrors().isEmpty())
        {
            result.setIsValid(true);
        }
        
        return result;
    }
    
    private void validateFlat(ValidationResult result, int woodRoofingId, int woodRoofingQuantity)
    {
        if (woodRoofingId == 0)
        {
            result.getErrors().put("woodRoofingId", "Wood roofing is required for a flat roof type.");
        }
        if (woodRoofingQuantity <= 0)
        {
            result.getErrors().put("woodRoofingQuantity", "You need at least one wood roofing for a flat roof type.");
        }
        //get the woodRoofing from the DB
        ProductPart woodRoofing = this.productPartRepository.get(woodRoofingId);
        
        if (woodRoofing == null)
        {
            result.getErrors().put("woodRoofingId", "Wood roofing provided doesn't exist");
        }
        else
        {
            if (woodRoofingQuantity > woodRoofing.getStock())
            {
                result.getErrors().put("woodRoofingQuantity", "Not enough stock, please order less wood roofings");
            }
        }
    }
    
    private void validateTriangle(ValidationResult result, int woodId, int woodQuantity, int roofSkeletonId)
    {
        if (woodId == 0)
        {
            result.getErrors().put("woodId", "Wood is required for a triangle roof type.");
        }
        if (roofSkeletonId == 0)
        {
            result.getErrors().put("roofSkeletonId", "A roof skeleton is required for a triangle roof type.");
        }
        if (woodQuantity <= 0)
        {
            result.getErrors().put("woodQuantity", "You need at least one wood for a triangle roof type.");
        }
        //Get the wood and roofSkeleton from the DB
        ProductPart wood = this.productPartRepository.get(woodId);
        ProductPart roofSkeleton = this.productPartRepository.get(roofSkeletonId);
        
        if (wood == null)
        {
            result.getErrors().put("woodId", "Wood provided doesn't exist");
        }
        else
        {
            if (woodQuantity > wood.getStock())
            {
                result.getErrors().put("woodQuantity", "Not enough stock, please order less wood");
            }
        }
        
        if (roofSkeleton == null)
        {
            result.getErrors().put("roofSkeletonId", "Roof skeleton provided doesn't exist");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web.DTO;

import Domain.RoofType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author azurwular
 */
public class ShoppingCartCarportDto 
{
    private RoofType roofType;
    private List<ShoppingCartCarportItemDto> productParts;
    private float price;
    
    public ShoppingCartCarportDto(
            RoofType roofType,
            int woodId,
            int roofSkeletonId,
            int firrintsScrewsId,
            int woodRoofingId,
            int woodQuantity,
            int fittingsScrewsQuantity,
            int woodRoofingQuantity)
    {
        this.roofType = roofType;
        
        
    }

    public ShoppingCartCarportDto()
    {
        this.productParts = new ArrayList<>();
    }

    public RoofType getRoofType() {
        return roofType;
    }

    public void setRoofType(RoofType roofType) {
        this.roofType = roofType;
    }

    public List<ShoppingCartCarportItemDto> getProductParts() {
        return productParts;
    }

    public void setProductParts(List<ShoppingCartCarportItemDto> productParts) {
        this.productParts = productParts;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

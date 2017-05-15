/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web.DTO;

import Domain.ProductPart;
import Domain.ProductPartType;
import Domain.StockUnit;

public class ProductPartDto
{
    private int id;
    private ProductPartType type;
    private String name;
    private String description;
    private StockUnit stockUnit;
    private float price;
    
    /**
     *
     * @param productPart
     */
    public ProductPartDto(ProductPart productPart)
    {
        this.id = productPart.getId();
        this.type = productPart.getType();
        
        if (productPart.getLength() != 0)
        {
            this.name = productPart.getName() + " - " + productPart.getLength();
        }
        else
        {
            this.name = productPart.getName();
        }
        
        this.description = productPart.getDescription();
        this.stockUnit = productPart.getStockUnit();
        this.price = productPart.getPrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductPartType getType() {
        return type;
    }

    public void setType(ProductPartType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StockUnit getStockUnit() {
        return stockUnit;
    }

    public void setStockUnit(StockUnit stockUnit) {
        this.stockUnit = stockUnit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author azurwular
 */
public class ProductPart
{
    private int id;
    private ProductPartType type;
    private String name;
    private String description;
    private int stock;
    private StockUnit stockUnit;
    private float price;
    private Integer length;

    public ProductPart(
            int id,
            ProductPartType type,
            String name,
            String description, 
            int stock,
            StockUnit stockUnit,
            float price,
            Integer length)
    {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.stockUnit = stockUnit;
        this.price = price;
        this.length = length;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public void setPrice(long price) {
        this.price = price;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
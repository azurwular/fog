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
public class OrderProductPart
{
    private int id;
    private OrderCarport orderCarport;
    private ProductPart productPart;
    private int quantity;
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderCarport getOrderCarport() {
        return orderCarport;
    }

    public void setOrderCarport(OrderCarport orderCarport) {
        this.orderCarport = orderCarport;
    }

    public ProductPart getProductPart() {
        return productPart;
    }

    public void setProductPart(ProductPart productPart) {
        this.productPart = productPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
}
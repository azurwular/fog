/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.List;

/**
 *
 * @author azurwular
 */
public class OrderCarport
{
    private int id;
    private Order order;
    private List<OrderProductPart> productParts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderProductPart> getProductParts() {
        return productParts;
    }

    public void setProductParts(List<OrderProductPart> productParts) {
        this.productParts = productParts;
    }
}

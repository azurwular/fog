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
public class Order
{
    private int id;
    private User customer;
    private List<OrderCarport> Products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<OrderCarport> getProducts() {
        return Products;
    }

    public void setProducts(List<OrderCarport> Products) {
        this.Products = Products;
    }
}

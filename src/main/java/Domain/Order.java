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
    private List<OrderProduct> Products;
}

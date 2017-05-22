/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author azurwular
 */
public class ShoppingCartDto
{
    private List<ShoppingCartCarportDto> Carports;
    private float totalPrice;
    
    public ShoppingCartDto()
    {
        this.Carports = new ArrayList<>();
        this.totalPrice = 0;
    }

    public List<ShoppingCartCarportDto> getCarports() {
        return Carports;
    }

    public void setCarports(List<ShoppingCartCarportDto> Carports) {
        this.Carports = Carports;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}

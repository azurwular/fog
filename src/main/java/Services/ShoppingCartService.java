/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAL.Repositories.ProductPartRepository;
import DAL.Repositories.UserRepository;
import Domain.Order;
import Domain.OrderCarport;
import Domain.OrderProductPart;
import Domain.ProductPart;
import Domain.RoofType;
import Domain.User;
import Web.DTO.ShoppingCartCarportDto;
import Web.DTO.ShoppingCartCarportItemDto;
import Web.DTO.ShoppingCartDto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azurwular
 */
public class ShoppingCartService
{
    private final ProductPartRepository productPartRepository;
    private final UserRepository userRepository;
    
    public ShoppingCartService()
    {
        this.productPartRepository = new ProductPartRepository();
        this.userRepository = new UserRepository();
    }
    
    public ShoppingCartDto AddToCart(
        ShoppingCartDto shoppingCart,
        RoofType roofType,
        int woodId,
        int roofSkeletonId,
        int fittingsScrewsId,
        int woodRoofingId,
        int woodQuantity,
        int fittingsScrewsQuantity,
        int woodRoofingQuantity)
    {
        // Instantiate a new cart if there is none in the session
        if (shoppingCart == null)
        {
            shoppingCart = new ShoppingCartDto();
        }
        
        // Add a dto version of the requested carport
        shoppingCart.getCarports().add(MapToShoppingCartCarport(
                roofType,
                woodId,
                roofSkeletonId,
                fittingsScrewsId,
                woodRoofingId,
                woodQuantity,
                fittingsScrewsQuantity,
                woodRoofingQuantity));
        
        // Re-calculate the total price of the cart
        float totalPrice = 0;
        for (ShoppingCartCarportDto carport : shoppingCart.getCarports())
        {
            totalPrice += carport.getPrice();
        }
        
        shoppingCart.setTotalPrice(totalPrice);
        
        return shoppingCart;
    }
    
    public Order createOrder(int userId, ShoppingCartDto shoppingCart) 
    {
        Order order = new Order();
        User user = null;
        //get the user from the db
        try {
            user = this.userRepository.get(userId);
            
            if (user == null || shoppingCart == null || shoppingCart.getCarports().isEmpty())
            {
                return null;
            }
            
        } catch (NullPointerException ex) {
            Logger.getLogger(ShoppingCartService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ShoppingCartService.class.getName()).log(Level.SEVERE, null, ex);
        }
        //set the user in the order
        order.setCustomer(user);
          //Now we need a list of orderCarpots because an order can contain many carports 
        List<OrderCarport> orderCarports = new ArrayList<>();
          //go through all the carports in the cart and we put them in the orderCarports arraylist 
        for (ShoppingCartCarportDto carport : shoppingCart.getCarports())
        {
            OrderCarport orderCarport = CreateOrderCarport(carport);
            orderCarports.add(orderCarport);
        }
        
        order.setProducts(orderCarports);
        
        return order;
    }
    
    private OrderCarport CreateOrderCarport(ShoppingCartCarportDto shoppingCartCarport)
    {
        OrderCarport orderCarport = new OrderCarport();
        //each carport is made out of a list of product parts 
        List<OrderProductPart> orderProductparts = new ArrayList<>();
        
        for (ShoppingCartCarportItemDto carportPart : shoppingCartCarport.getProductParts())
        {
            OrderProductPart orderProductPart = CreateOrderProductPart(carportPart);
            orderProductPart.setOrderCarport(orderCarport);
            orderProductparts.add(orderProductPart);
        }
        
        orderCarport.setProductParts(orderProductparts);
        
        return orderCarport;
    }
    
    private OrderProductPart CreateOrderProductPart(ShoppingCartCarportItemDto shoppingCartCarportItem)
    {
        OrderProductPart orderProductPart = new OrderProductPart();
        
        ProductPart productPart = this.productPartRepository.get(shoppingCartCarportItem.getId());
        
        orderProductPart.setProductPart(productPart);
        orderProductPart.setPrice(productPart.getPrice() * shoppingCartCarportItem.getQuantity());
        orderProductPart.setQuantity(shoppingCartCarportItem.getQuantity());
        
        return orderProductPart;
    }
    
    private ShoppingCartCarportDto MapToShoppingCartCarport(
        RoofType roofType,
        int woodId,
        int roofSkeletonId,
        int fittingsScrewsId,
        int woodRoofingId,
        int woodQuantity,
        int fittingsScrewsQuantity,
        int woodRoofingQuantity)
    {
        ShoppingCartCarportDto shoppingCartCarport = new ShoppingCartCarportDto();
        
        shoppingCartCarport.setRoofType(roofType);
        
        switch(roofType)
        {
            case Flat:
                ProductPart woodRoofing = this.productPartRepository.get(woodRoofingId);
                //Add a dto version of the woodRoofing
                shoppingCartCarport.getProductParts().add(MapToShoppingCartCarportItem(woodRoofing, woodRoofingQuantity));
                break;
            case Triangle:
                ProductPart wood = this.productPartRepository.get(woodId);
                shoppingCartCarport.getProductParts().add(MapToShoppingCartCarportItem(wood, woodQuantity));
                ProductPart roofSkeleton = this.productPartRepository.get(roofSkeletonId);
                shoppingCartCarport.getProductParts().add(MapToShoppingCartCarportItem(roofSkeleton, 1));
        }
        
        ProductPart fittingsScrews = this.productPartRepository.get(fittingsScrewsId);
        shoppingCartCarport.getProductParts().add(MapToShoppingCartCarportItem(fittingsScrews, fittingsScrewsQuantity));
        
        // Calculate total price for carport
        float totalPrice = 0;
        
        for (ShoppingCartCarportItemDto item : shoppingCartCarport.getProductParts())
        {
            totalPrice += item.getPrice();
        }
        
        shoppingCartCarport.setPrice(totalPrice);
        
        return shoppingCartCarport;
    }
    
    private ShoppingCartCarportItemDto MapToShoppingCartCarportItem(ProductPart productPart, int quantity)
    {
        ShoppingCartCarportItemDto shoppingCartCarportItem = new ShoppingCartCarportItemDto();
        
        String name = productPart.getName();
        
        if (productPart.getLength() != null && productPart.getLength() != 0)
        {
            name += " - " + productPart.getLength();
        }
        
        shoppingCartCarportItem.setId(productPart.getId());
        shoppingCartCarportItem.setName(name);
        shoppingCartCarportItem.setStockUnit(productPart.getStockUnit());
        shoppingCartCarportItem.setQuantity(quantity);
        shoppingCartCarportItem.setPrice(quantity * productPart.getPrice());
        
        return shoppingCartCarportItem;
    }
}
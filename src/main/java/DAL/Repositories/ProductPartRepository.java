/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Repositories;

import DAL.DataAccessObject;
import Domain.ProductPart;
import Domain.ProductPartType;
import Domain.RoofType;
import Domain.StockUnit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azurwular
 */
public class ProductPartRepository
{
    /**
     * Get all the product parts from database
     * @return
     * @throws SQLException
     * @throws NullPointerException 
     */
    
    public List<ProductPart> getAll() throws SQLException, NullPointerException, ClassNotFoundException 
    {
        PreparedStatement insertStatement;
        ResultSet resultSet;
        
        // Fetch only in-stock product parts
        String insertSQL = "SELECT * from product_parts where stock > 0";
        
        Connection connection = DataAccessObject.getConnection();
        
        insertStatement = connection.prepareStatement(insertSQL);

        resultSet = insertStatement.executeQuery();
        
        List<ProductPart> results = new ArrayList<>();

        while(resultSet.next())
        {
            int id = resultSet.getInt("id");
            ProductPartType type = ProductPartType.valueOf(resultSet.getString("part_type"));
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            int stock =  resultSet.getInt("stock");
            StockUnit stockUnit = StockUnit.valueOf(resultSet.getString("stock_unit"));
            float price = resultSet.getFloat("price");
            Integer length = resultSet.getInt("length");

            ProductPart productPart = new ProductPart(id, type, name, description, stock, stockUnit, price, length);

            results.add(productPart);
        }
        
        DataAccessObject.releaseConnection(connection);
        
        return results;
    }
    
    public ProductPart get(int id)
    {
        PreparedStatement selectStatement;
        ResultSet resultSet;
        
        // Fetch only in-stock product parts
        String getProductpartQuery = "SELECT * from product_parts where id = ?";
        
        Connection connection;
        
        ProductPart result = null;
        
        try {
            connection = DataAccessObject.getConnection();
            
            selectStatement = connection.prepareStatement(getProductpartQuery);
            selectStatement.setInt(1, id);

            resultSet = selectStatement.executeQuery();
            
            if (resultSet.next())
            {
                result = new ProductPart(
                    resultSet.getInt("id"),
                    ProductPartType.valueOf(resultSet.getString("part_type")),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getInt("stock"),
                    StockUnit.valueOf(resultSet.getString("stock_unit")),
                    resultSet.getFloat("price"),
                    resultSet.getInt("length"));
            }

            DataAccessObject.releaseConnection(connection);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductPartRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
}

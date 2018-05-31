/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.model;

import demo.entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author admin
 */
public class ProductModel {
    ObservableList<Product> list = FXCollections.observableArrayList();
    public boolean save(Product product){
        try {
            String sqlString = "insert into 'product'('name','image','price') value (?,?,?)";
            PreparedStatement ps = ConnectionHandle.connectionHandle().getConnection().prepareStatement(sqlString);
            ps.setString(1, product.getName());
            ps.setString(2, product.getImage());
            ps.setInt(3, product.getPrice());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
        
    }
    
    public boolean show(Product product1){
        try {
            String sqlString = "select * from user where name = ? and image = ? and price = ?";
            PreparedStatement ps = ConnectionHandle.connectionHandle().getConnection().prepareStatement(sqlString);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
               String strName = rs.getString("name");
               String strPassword = rs.getString("image");
               int price1 = rs.getInt("price");
               Product product = new Product(strName, strName, price1);
               list.add(product);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}

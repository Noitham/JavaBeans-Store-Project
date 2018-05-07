/*
 * To change this license header, choose License Headers in Project Properties.
 * To changeF this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans.model.persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javabeans.model.Category;
import javabeans.model.Product;

/**
 *
 * @author Daniel Morales
 */
public class ProductDAO {

    //DB declaration
    private final DBConnect dbConnect;

    public ProductDAO(DBConnect dbConnect) {
        this.dbConnect = dbConnect;
    }

    /**
     * Method that will return a list with the products from the db.
     *
     * @return a list containing the products from db.
     */
    public List<Product> listAll() {

        List<Product> result = new ArrayList<>();
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from product;");
                while (rs.next()) {
                    Product p = fromResultSet(rs);
                    if (p != null) {
                        result.add(p);
                    }
                }
            } else {
                result = new ArrayList<>();
            }
        } catch (SQLException e) {
            result = new ArrayList<>();
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    /**
     * Find product by id
     *
     * @param product to find
     * @return product found
     *
     */
    public Product findById(Product product) {
        Product result = null;
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement("select * from product where id=?;");
                st.setLong(1, product.getId());
                ResultSet rs = st.executeQuery();
                rs.next();
                result = fromResultSet(rs);
            } else {
                result = null;
            }
        } catch (SQLException e) {
            result = null;
        } catch (Exception e) {
            result = null;
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Retrieve data from current row.
     *
     * @param rs result set from which we retirieve data
     * @return a object or null in case of error.
     *
     */
    private Product fromResultSet(ResultSet rs) {
        Product product = null;
        try {
            long id = rs.getInt("id");
            String code = rs.getString("code");
            String description = rs.getString("description");
            double cost = rs.getDouble("cost_price");
            double stock = rs.getDouble("stock");
            double min = rs.getDouble("min_stock");
            String category = rs.getString("category");
            product = new Product(id, code, description, cost, stock, min, category);
        } catch (SQLException ex) {
            product = null;
            System.out.println("Error: " + ex.getMessage());
        }
        return product;
    }

    /**
     * Return products by given category.
     *
     * @param category to search
     * @return list of products
     */
    public List<Product> listByCategory(Category category) {
        List<Product> result = new ArrayList<>();
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement("select * from product where category=?;");
                st.setString(1, category.getCode());
                System.out.println(category.getCode());
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Product p = fromResultSet(rs);
                    if (p != null) {
                        result.add(p);
                    }
                }
            } else {
                result = new ArrayList<>();
            }
        } catch (SQLException e) {
            result = new ArrayList<>();
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    /**
     * Return products by given price.
     *
     * @param product to filter
     * @return list of products
     */
    public List<Product> listByPrice(Product product) {
        List<Product> result = new ArrayList<>();
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement("select * from product where cost_price<=?;");
                st.setDouble(1, product.getCostPrice());
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Product p = fromResultSet(rs);
                    if (p != null) {
                        result.add(p);
                    }
                }
            } else {
                result = new ArrayList<>();
            }
        } catch (SQLException e) {
            result = new ArrayList<>();
            System.out.println("FindAll:" + e.getMessage());
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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

/**
 *
 * @author Daniel Morales
 */
public class CategoryDAO {

    private final DBConnect dbConnect;

    public CategoryDAO(DBConnect dbConnect) {
        this.dbConnect = dbConnect;
    }

    /**
     * Get all students from the db.
     *
     * @return a list containing the data from db. in cas of not finding, it
     * will return null. In case of no connection with db it will show error
     * message.
     *
     */
    public List<Category> listAll() {

        List<Category> result = new ArrayList<>();
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from category;");
                while (rs.next()) {
                    Category c = fromResultSet(rs);
                    if (c != null) {
                        result.add(c);
                    }
                }
            } else {
                result = null;
            }
        } catch (SQLException e) {
            result = null;
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    /**
     * Search category by code
     *
     * @param category to find
     * @return category found
     *
     */
    public Category findByName(Category category) {
        Category result = null;
        try {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement("select * from category where code=?;");
                st.setString(1, category.getCode());
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
     */
    private Category fromResultSet(ResultSet rs) {
        Category category = null;
        try {
            long id = rs.getInt("id");
            String code = rs.getString("code");
            String description = rs.getString("description");

            category = new Category(id, code, description);
        } catch (SQLException ex) {
            category = null;
            System.out.println("Error fromresultset," + ex.getMessage());
        }
        return category;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans.model;

import java.util.List;
import javabeans.model.persist.CategoryDAO;
import javabeans.model.persist.DBConnect;
import javabeans.model.persist.ProductDAO;

/**
 *
 * @author DanielMoralesGonzale
 */
public class Model {

    private final DBConnect dbConnect;
    private final ProductDAO daoProduct;
    private final CategoryDAO daoCategory;

    public Model() throws ClassNotFoundException {
        this.dbConnect = new DBConnect();
        this.daoProduct = new ProductDAO(dbConnect);
        this.daoCategory = new CategoryDAO(dbConnect);
    }

    /**
     * Method for finding all products.
     *
     * @return product list found in db.
     */
    public List<Product> findAllProducts() {
        List<Product> products;
        products = daoProduct.listAll();
        return products;
    }

    /**
     * Method for finding all the categories
     *
     * @return categories from db.
     */
    public List<Category> findAllCategories() {
        List<Category> categories;
        categories = daoCategory.listAll();
        return categories;
    }

    /**
     * Method for finding Category by name.
     *
     *
     * @param category
     * @return category by input name
     */
    public Category findCategorybyName(Category category) {
        return daoCategory.findByName(category);
    }

    /**
     * Method for finding product by category.
     *
     * @param category
     * @return products
     */
    public List<Product> findByCategory(Category category) {
        List<Product> products;
        products = daoProduct.listByCategory(category);
        return products;
    }

    /**
     * Method for finding product by price.
     *
     * @param product
     * @return products
     */
    public List<Product> findByPrice(Product product) {
        List<Product> products;
        products = daoProduct.listByPrice(product);
        return products;
    }

}

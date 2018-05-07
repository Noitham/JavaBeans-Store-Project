package javabeans.model;

import java.beans.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Product implements Serializable {

    private PropertyChangeSupport propertySupport;
    private VetoableChangeSupport vetoableSupport;

    private long id;
    private String code;
    private String description;
    private double costPrice;
    private double stock;
    private double minStock;
    private String category;

    public Product() {
        propertySupport = new PropertyChangeSupport(this);
        vetoableSupport = new VetoableChangeSupport(this);
    }

    public Product(long id, String code, String description, double costPrice,
            double stock, double minStock, String category) {
        propertySupport = new PropertyChangeSupport(this);
        vetoableSupport = new VetoableChangeSupport(this);
        this.id = id;
        this.code = code;
        this.description = description;
        this.costPrice = costPrice;
        this.stock = stock;
        this.minStock = minStock;
        this.category = category;
    }

    public Product(double price) {
        this.costPrice = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws PropertyVetoException {
        String previous = getCode();
        vetoableSupport.fireVetoableChange("code", previous, code);
        this.code = code;
        propertySupport.firePropertyChange("code", previous, code);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) throws PropertyVetoException {
        double previous = getCostPrice();
        vetoableSupport.fireVetoableChange("costPrice", previous, costPrice);
        this.costPrice = costPrice;
        propertySupport.firePropertyChange("costPrice", previous, costPrice);
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) throws PropertyVetoException {
        double previous = getStock();
        vetoableSupport.fireVetoableChange("stock", previous, stock);
        this.stock = stock;
        propertySupport.firePropertyChange("stock", previous, stock);
    }

    public double getMinStock() {
        return minStock;
    }

    public void setMinStock(double minStock) {
        this.minStock = minStock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableSupport.addVetoableChangeListener(listener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetoableSupport.removeVetoableChangeListener(listener);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        Class classObj = getClass();
        String className = classObj.getSimpleName();
        sb.append(className).append(" {");
        Field[] fields = classObj.getDeclaredFields();
        for (Field f : fields) {
            String fieldName = f.getName();
            try {
                Object fieldValue = f.get(this);
                if (fieldValue != null) {
                    sb.append("[");
                    sb.append(fieldName).append("=");
                    sb.append(fieldValue.toString());
                    sb.append("]");
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sb.append("}");
        return sb.toString();
    }

}

package javabeans.model;

import java.beans.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Category implements Serializable {

    private PropertyChangeSupport propertySupport;
    private VetoableChangeSupport vetoableSupport;

    private long id;
    private String code;
    private String description;

    public Category() {
        propertySupport = new PropertyChangeSupport(this);
        vetoableSupport = new VetoableChangeSupport(this);
    }

    public Category(long id, String code, String description) {
        propertySupport = new PropertyChangeSupport(this);
        vetoableSupport = new VetoableChangeSupport(this);
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public Category(String code) {
        this.code = code;
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
        String oldValue = this.code;
        vetoableSupport.fireVetoableChange("code", oldValue, this.code);
        this.code = code;
        propertySupport.firePropertyChange("code", oldValue, this.code);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
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
                Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        sb.append("}");
        return sb.toString();
    }

}

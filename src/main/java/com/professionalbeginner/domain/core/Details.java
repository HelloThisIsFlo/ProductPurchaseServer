package com.professionalbeginner.domain.core;

import com.google.common.base.MoreObjects;

import java.util.Objects;

import static com.google.common.base.Strings.nullToEmpty;

/**
 * Domain object representing purchase details.
 */
public class Details {

    @SuppressWarnings("WeakerAccess")
    public final static Details NULL;

    static {
        NULL = new Details();
        NULL.setId(0);
        NULL.setValue(0);
        NULL.setQuantity(0);
        NULL.setDescription("");
    }


    private long id;
    private String description;
    private int quantity;
    private double value;

    Details() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Details details = (Details) o;
        return id == details.id &&
                quantity == details.quantity &&
                Double.compare(details.value, value) == 0 &&
                Objects.equals(description, details.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, quantity, value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("description", description)
                .add("quantity", quantity)
                .add("value", value)
                .toString();
    }
}

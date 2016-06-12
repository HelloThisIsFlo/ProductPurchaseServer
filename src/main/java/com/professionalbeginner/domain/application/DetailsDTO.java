package com.professionalbeginner.domain.application;

import com.google.common.base.MoreObjects;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

public class DetailsDTO {

    public final static DetailsDTO NULL = new DetailsDTO(0, "", 0, 0);

    public final long id;
    public final String description;
    public final int quantity;
    public final double value;

    public DetailsDTO(long id, String description, int quantity, double value) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailsDTO that = (DetailsDTO) o;
        return id == that.id &&
                quantity == that.quantity &&
                Double.compare(that.value, value) == 0 &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, quantity, value);
    }

    @Override
    public String toString() {
        if (this.equals(NULL)) {
            return "NULL";
        }
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("description", description)
                .add("quantity", quantity)
                .add("value", value)
                .toString();
    }
}

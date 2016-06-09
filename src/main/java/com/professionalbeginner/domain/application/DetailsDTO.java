package com.professionalbeginner.domain.application;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

public class DetailsDTO {

    public final long id;
    public final String description;
    public final int quantity;
    public final double value;

    public DetailsDTO(long id, String description, int quantity, double value) {
        this.id = id;
        this.description = checkNotNull(description);
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
}

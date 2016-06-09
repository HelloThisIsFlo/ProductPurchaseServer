package com.professionalbeginner.domain.core;

public class DetailsFactory {

    public Details make(long id, String description, int quantity, double value) {
        Details details = new Details();

        details.setId(id);
        details.setDescription(description);
        details.setQuantity(quantity);
        details.setValue(value);

        return details;
    }

}

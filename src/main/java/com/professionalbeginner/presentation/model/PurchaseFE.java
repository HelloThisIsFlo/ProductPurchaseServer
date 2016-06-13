package com.professionalbeginner.presentation.model;

import com.google.common.base.MoreObjects;

/**
 * Created by Florian on 13/06/16.
 */
public class PurchaseFE {
    /*
    <td class="text-capitalize text-primary">Type</td>
                <td class="text-capitalize text-primary">Expires</td>
                <td class="text-capitalize text-primary">Description</td>
                <td class="text-capitalize text-primary">Quantity</td>
                <td class="text-capitalize text-primary">Value</td>
     */
    private String type;
    private String expires;
    private String description;
    private String quantity;
    private String value;

    public PurchaseFE(String type, String expires, String description, String quantity, String value) {
        this.type = type;
        this.expires = expires;
        this.description = description;
        this.quantity = quantity;
        this.value = value;
    }

    public PurchaseFE() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type", type)
                .add("expires", expires)
                .add("description", description)
                .add("quantity", quantity)
                .add("value", value)
                .toString();
    }
}

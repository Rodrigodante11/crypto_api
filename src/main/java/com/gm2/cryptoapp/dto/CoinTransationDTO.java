package com.gm2.cryptoapp.dto;

import java.math.BigDecimal;

public class CoinTransationDTO {

    private String name;
    private BigDecimal quantity;

    public CoinTransationDTO(String name, BigDecimal quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

}

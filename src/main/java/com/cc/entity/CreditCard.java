package com.cc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.cc.component.AppUtility;
import com.cc.component.LuhnAlgorithm;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Entity(name = "credit_cards")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @JsonIgnore
    private Integer id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column
    private String name;

    @Column(name = "card_limit")
    @JsonProperty("limit")
    private Integer cardLimit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(Integer cardLimit) {
        this.cardLimit = cardLimit;
    }

    public boolean validateCreditCardNumber() {
        return !StringUtils.isEmpty(this.cardNumber) && this.cardNumber.length() <= 19 &&
                LuhnAlgorithm.validateCreditCardNumber(this.getCardNumber()) && AppUtility.stringContainsDigits(this.cardNumber);
    }
}

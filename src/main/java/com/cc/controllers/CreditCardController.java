package com.cc.controllers;

import com.cc.constants.ApplicationConstants;
import com.cc.entity.CreditCard;
import com.cc.model.Status;
import com.cc.service.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = ApplicationConstants.BASE_PATH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CreditCardController {

    private final static Logger LOG = LoggerFactory.getLogger(CreditCardController.class);

    private final CreditCardService cardService;

    public CreditCardController(final CreditCardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<CreditCard> getAllCreditCardDetails() {
        LOG.info("Getting the card details");
        return cardService.getAllCardDetails();
    }


    @PostMapping(value = "/add")
    @ExceptionHandler
    public ResponseEntity<Status> addCreditCard(@RequestBody final CreditCard creditCard) {

        boolean isValid = creditCard.validateCreditCardNumber();
        try {
            if (isValid && creditCard.getCardLimit() == 0) {
                cardService.addCreditCard(creditCard);
                Status status = new Status("Created", "Credit Cards Details are stored successfully.", 201);
                return ResponseEntity.of(Optional.of(status));
            } else {
                LOG.error("Card Validation Failed for the card number {}", creditCard.getCardNumber());
                Status status = new Status("Bad Request", "The Credit Card number you're truing to add is mot valid. Please check it and try again..", 400);
                return ResponseEntity.of(Optional.of(status));
            }
        } catch (Exception e) {

        }
        return null;
    }
}

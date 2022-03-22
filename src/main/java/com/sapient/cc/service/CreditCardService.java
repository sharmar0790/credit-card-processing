package com.sapient.cc.service;

import com.sapient.cc.entity.CreditCard;
import com.sapient.cc.repository.CreditCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService {

    private final static Logger LOG = LoggerFactory.getLogger(CreditCardService.class);

    private final CreditCardRepository cardRepository;

    public CreditCardService(final CreditCardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    public List<CreditCard> getAllCardDetails() {
        LOG.info("Card Details are {}", cardRepository.findAll());
        return (List<CreditCard>) cardRepository.findAll();
    }

    public void addCreditCard(CreditCard creditCard) {
        cardRepository.save(creditCard);
    }
}

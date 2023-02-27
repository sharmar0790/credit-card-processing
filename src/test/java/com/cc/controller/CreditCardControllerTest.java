package com.cc.controller;

import com.cc.constants.ApplicationConstants;
import com.cc.controllers.CreditCardController;
import com.cc.entity.CreditCard;
import com.cc.service.CreditCardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CreditCardController.class)
@AutoConfigureMockMvc
public class CreditCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditCardService cardService;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void getAllCreditCardDetailsTest() throws Exception {
        final String URI = ApplicationConstants.BASE_PATH;

        final List<CreditCard> cardsList = new ArrayList<>();
        final CreditCard cc1 = new CreditCard();
        cc1.setCardLimit(0);
        cc1.setCardNumber("1232112344333");
        cc1.setId(1);
        cc1.setName("Ravi");

        final CreditCard cc2 = new CreditCard();
        cc2.setCardLimit(0);
        cc2.setCardNumber("12321123432333");
        cc2.setId(2);
        cc2.setName("Abby");

        cardsList.add(cc1);
        cardsList.add(cc2);

        Mockito.when(cardService.getAllCardDetails()).thenReturn(cardsList);

        mockMvc.perform(MockMvcRequestBuilders.get(URI).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void addCardDetails_whenCorrectDetailsProvided() throws Exception {
        final String URI = ApplicationConstants.BASE_PATH+"/add";

        final CreditCard cc = new CreditCard();
        cc.setCardLimit(0);
        cc.setCardNumber("012850003580200");
        cc.setId(1);
        cc.setName("Ravi");

        doNothing().when(cardService).addCreditCard(cc);

        mockMvc.perform(MockMvcRequestBuilders.post(URI).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsBytes(cc)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code",is(201)));
    }

    @Test
    public void givenWrongCardDetails_whenAddCardDetails_thenResponseSentWithErrorCode() throws Exception {
        final String URI = ApplicationConstants.BASE_PATH+"/add";

        final CreditCard cc = new CreditCard();
        cc.setCardLimit(0);
        cc.setCardNumber("012850080200");
        cc.setId(1);
        cc.setName("Ravi");

        doNothing().when(cardService).addCreditCard(cc);

        mockMvc.perform(MockMvcRequestBuilders.post(URI).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsBytes(cc)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code",is(400)));
    }

}

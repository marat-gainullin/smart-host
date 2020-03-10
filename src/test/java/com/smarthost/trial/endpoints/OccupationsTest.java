package com.smarthost.trial.endpoints;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OccupationsTest {

    @Autowired
    private MockMvc restMock;

    @Test
    public void aParameterMissing() throws Exception {
        restMock.perform(get("/occupations/optimal"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void aParameterOfWrongType() throws Exception {
        restMock.perform(get("/occupations/optimal?availableEconomy=uiim&availablePremium=rtim"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("NumberFormat")));
    }

    @Test
    public void aParameterOutOfRange() throws Exception {
        restMock.perform(get("/occupations/optimal?availableEconomy=7777777777777777777777777777777&availablePremium=2222222222222222222222222222222222"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("NumberFormat")));
    }
}

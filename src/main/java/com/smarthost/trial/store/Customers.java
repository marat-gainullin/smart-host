package com.smarthost.trial.store;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Customers {

    private static final List<Integer> CLIENTS_OFFERS = List.of(23, 45, 155, 374, 22, 99, 100, 101, 115, 209);

    public List<Integer> getOffers() {
        return CLIENTS_OFFERS;
    }
}

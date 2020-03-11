package com.smarthost.trial.store;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CustomersStorageTest {

    private CustomersStorage customers = CustomersStorage.of(List.of());

    @Test
    public void duplicatedElements() {
        customers.getPremiumOffers().add(100);
        customers.getPremiumOffers().add(100);
        customers.getEconomyOffers().add(10);
        customers.getEconomyOffers().add(10);
        customers.getEconomyOffers().add(10);
        Assertions.assertEquals(2, customers.getPremiumOffers().size());
        Assertions.assertEquals(3, customers.getEconomyOffers().size());
    }
}

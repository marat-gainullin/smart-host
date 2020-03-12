package com.smarthost.trial.planning;

import com.smarthost.trial.model.Occupation;
import com.smarthost.trial.store.CustomersStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DuplicatedPricesPlannerTest {

    private static CustomersStorage customers = CustomersStorage.of(List.of(23, 45, 23, 155, 374, 45, 22, 155, 99, 0, 100, 101, -1, 115, 100, 209));
    private static final Planner planner = new Planner(customers.getPremiumOffers(), customers.getEconomyOffers());

    @Test
    public void premium10Economy4Upgrades2() {
        Occupation planned = planner.optimal(10, 4);
        Assertions.assertEquals(10, planned.getPremiumRooms());
        Assertions.assertEquals(1453, planned.getPremiumRoomsSum());
        Assertions.assertEquals(4, planned.getEconomyRooms());
        Assertions.assertEquals(113, planned.getEconomyRoomsSum());
    }

    @Test
    public void premium10Economy6NoUpgrades() {
        Occupation planned = planner.optimal(10, 6);
        Assertions.assertEquals(8, planned.getPremiumRooms());
        Assertions.assertEquals(1309, planned.getPremiumRoomsSum());
        Assertions.assertEquals(6, planned.getEconomyRooms());
        Assertions.assertEquals(257, planned.getEconomyRoomsSum());
    }
}

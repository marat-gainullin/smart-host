package com.smarthost.trial.planning;

import com.smarthost.trial.model.Occupation;
import com.smarthost.trial.store.CustomersStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlannerTest {

    private static final CustomersStorage customers = CustomersStorage.of(List.of(23, 45, 155, 374, 22, 99, 100, 101, 115, 209));
    private static final Planner planner = new Planner(customers.getPremiumOffers(), customers.getEconomyOffers());

    @Test
    public void negativeAvailablePremium() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                planner.optimal(-3, 3)
        );
    }

    @Test
    public void negativeAvailableEconomy() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                planner.optimal(3, -3)
        );
    }

    @Test
    public void premium3Economy3() {
        Occupation planned = planner.optimal(3, 3);
        Assertions.assertEquals(3, planned.getPremiumRooms());
        Assertions.assertEquals(738, planned.getPremiumRoomsSum());
        Assertions.assertEquals(3, planned.getEconomyRooms());
        Assertions.assertEquals(167, planned.getEconomyRoomsSum());
    }

    @Test
    public void premium7Economy5() {
        Occupation planned = planner.optimal(7, 5);
        Assertions.assertEquals(6, planned.getPremiumRooms());
        Assertions.assertEquals(1054, planned.getPremiumRoomsSum());
        Assertions.assertEquals(4, planned.getEconomyRooms());
        Assertions.assertEquals(189, planned.getEconomyRoomsSum());
    }

    @Test
    public void premium2Economy7() {
        Occupation planned = planner.optimal(2, 7);
        Assertions.assertEquals(2, planned.getPremiumRooms());
        Assertions.assertEquals(583, planned.getPremiumRoomsSum());
        Assertions.assertEquals(4, planned.getEconomyRooms());
        Assertions.assertEquals(189, planned.getEconomyRoomsSum());
    }

    @Test
    public void premium7Economy1() {
        Occupation planned = planner.optimal(7, 1);
        Assertions.assertEquals(7, planned.getPremiumRooms());
        Assertions.assertEquals(1153, planned.getPremiumRoomsSum());
        Assertions.assertEquals(1, planned.getEconomyRooms());
        Assertions.assertEquals(45, planned.getEconomyRoomsSum());
    }

    @Test
    public void premiumUnavailableEconomy1() {
        Occupation planned = planner.optimal(0, 1);
        Assertions.assertEquals(0, planned.getPremiumRooms());
        Assertions.assertEquals(0, planned.getPremiumRoomsSum());
        Assertions.assertEquals(1, planned.getEconomyRooms());
        Assertions.assertEquals(99, planned.getEconomyRoomsSum());
    }

    @Test
    public void premiumUnavailableEconomyUnavailable() {
        Occupation planned = planner.optimal(0, 0);
        Assertions.assertEquals(0, planned.getPremiumRooms());
        Assertions.assertEquals(0, planned.getPremiumRoomsSum());
        Assertions.assertEquals(0, planned.getEconomyRooms());
        Assertions.assertEquals(0, planned.getEconomyRoomsSum());
    }

    @Test
    public void premium1EconomyUnavailable() {
        Occupation planned = planner.optimal(1, 0);
        Assertions.assertEquals(1, planned.getPremiumRooms());
        Assertions.assertEquals(374, planned.getPremiumRoomsSum());
        Assertions.assertEquals(0, planned.getEconomyRooms());
        Assertions.assertEquals(0, planned.getEconomyRoomsSum());
    }

    @Test
    public void perfectFit() {
        Occupation planned = planner.optimal(6, 4);
        Assertions.assertEquals(6, planned.getPremiumRooms());
        Assertions.assertEquals(1054, planned.getPremiumRoomsSum());
        Assertions.assertEquals(4, planned.getEconomyRooms());
        Assertions.assertEquals(189, planned.getEconomyRoomsSum());
    }

    @Test
    public void fullPremiumWithUpgrades() {
        Occupation planned = planner.optimal(8, 2);
        Assertions.assertEquals(8, planned.getPremiumRooms());
        Assertions.assertEquals(1198, planned.getPremiumRoomsSum());
        Assertions.assertEquals(2, planned.getEconomyRooms());
        Assertions.assertEquals(45, planned.getEconomyRoomsSum());
    }

    @Test
    public void someUpgrades() {
        Occupation planned = planner.optimal(8, 3);
        Assertions.assertEquals(7, planned.getPremiumRooms());
        Assertions.assertEquals(1153, planned.getPremiumRoomsSum());
        Assertions.assertEquals(3, planned.getEconomyRooms());
        Assertions.assertEquals(90, planned.getEconomyRoomsSum());
    }
}

package com.smarthost.trial.planning;

import com.smarthost.trial.model.Occupation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlannerTest {

    private static final List<Integer> OFFERS = List.of(23, 45, 155, 374, 22, 99, 100, 101, 115, 209);

    @Test
    public void premium3Economy3() {
        Occupation planned = Planner.optimal(3, 3, OFFERS);
        Assertions.assertEquals(3, planned.getPremiumRooms());
        Assertions.assertEquals(738, planned.getPremiumRoomsSum());
        Assertions.assertEquals(3, planned.getEconomyRooms());
        Assertions.assertEquals(167, planned.getEconomyRoomsSum());
    }

    @Test
    public void premium7Economy5() {
        Occupation planned = Planner.optimal(7, 5, OFFERS);
        Assertions.assertEquals(6, planned.getPremiumRooms());
        Assertions.assertEquals(1054, planned.getPremiumRoomsSum());
        Assertions.assertEquals(4, planned.getEconomyRooms());
        Assertions.assertEquals(189, planned.getEconomyRoomsSum());
    }

    @Test
    public void premium2Economy7() {
        Occupation planned = Planner.optimal(2, 7, OFFERS);
        Assertions.assertEquals(2, planned.getPremiumRooms());
        Assertions.assertEquals(583, planned.getPremiumRooms());
        Assertions.assertEquals(4, planned.getEconomyRooms());
        Assertions.assertEquals(189, planned.getEconomyRooms());
    }

    @Test
    public void premium7Economy1() {
        Occupation planned = Planner.optimal(7, 1, OFFERS);
        Assertions.assertEquals(7, planned.getPremiumRooms());
        Assertions.assertEquals(1153, planned.getPremiumRoomsSum());
        Assertions.assertEquals(1, planned.getEconomyRooms());
        Assertions.assertEquals(45, planned.getEconomyRoomsSum());
    }

    @Test
    public void premiumUnavailableEconomy1() {
        Occupation planned = Planner.optimal(0, 1, OFFERS);
        Assertions.assertEquals(0, planned.getPremiumRooms());
        Assertions.assertEquals(0, planned.getPremiumRoomsSum());
        Assertions.assertEquals(1, planned.getEconomyRooms());
        Assertions.assertEquals(99, planned.getEconomyRoomsSum());
    }

    @Test
    public void premiumUnavailableEconomyUnavailable() {
        Occupation planned = Planner.optimal(0, 0, OFFERS);
        Assertions.assertEquals(0, planned.getPremiumRooms());
        Assertions.assertEquals(0, planned.getPremiumRoomsSum());
        Assertions.assertEquals(0, planned.getEconomyRooms());
        Assertions.assertEquals(0, planned.getEconomyRoomsSum());
    }

    @Test
    public void premium1EconomyUnavailable() {
        Occupation planned = Planner.optimal(1, 0, OFFERS);
        Assertions.assertEquals(1, planned.getPremiumRooms());
        Assertions.assertEquals(374, planned.getPremiumRoomsSum());
        Assertions.assertEquals(0, planned.getEconomyRooms());
        Assertions.assertEquals(0, planned.getEconomyRoomsSum());
    }

    @Test
    public void perfectFit() {
        Occupation planned = Planner.optimal(6, 4, OFFERS);
        Assertions.assertEquals(6, planned.getPremiumRooms());
        Assertions.assertEquals(1054, planned.getPremiumRoomsSum());
        Assertions.assertEquals(4, planned.getEconomyRooms());
        Assertions.assertEquals(189, planned.getEconomyRoomsSum());
    }

    @Test
    public void fullPremiumWithUpgrades() {
        Occupation planned = Planner.optimal(8, 2, OFFERS);
        Assertions.assertEquals(8, planned.getPremiumRooms());
        Assertions.assertEquals(1198, planned.getPremiumRoomsSum());
        Assertions.assertEquals(2, planned.getEconomyRooms());
        Assertions.assertEquals(45, planned.getEconomyRoomsSum());
    }

    @Test
    public void someUpgrades() {
        Occupation planned = Planner.optimal(8, 3, OFFERS);
        Assertions.assertEquals(7, planned.getPremiumRooms());
        Assertions.assertEquals(1153, planned.getPremiumRoomsSum());
        Assertions.assertEquals(3, planned.getEconomyRooms());
        Assertions.assertEquals(90, planned.getEconomyRoomsSum());
    }
}

package com.smarthost.trial.services;

import com.smarthost.trial.model.Occupation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OccupationPlannerTest {

    @Autowired
    private OccupationsPlanner plannerService;

    @Test
    public void fullPremiumWithUpgrades() {
        Occupation planned = plannerService.optimal(8, 2);
        Assertions.assertEquals(8, planned.getPremiumRooms());
        Assertions.assertEquals(1198, planned.getPremiumRoomsSum());
        Assertions.assertEquals(2, planned.getEconomyRooms());
        Assertions.assertEquals(45, planned.getEconomyRoomsSum());
    }

    @Test
    public void negativePremium() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                plannerService.optimal(-8, 2)
        );
    }

    @Test
    public void negativeEconomy() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                plannerService.optimal(8, -2)
        );
    }

}

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
        Assertions.assertEquals(3, planned.getEconomyRooms());
    }

    @Test
    public void premium7Economy5() {
        Occupation planned = Planner.optimal(7, 5, OFFERS);
        Assertions.assertEquals(6, planned.getPremiumRooms());
        Assertions.assertEquals(4, planned.getEconomyRooms());
    }

    @Test
    public void premium2Economy7() {
        Occupation planned = Planner.optimal(2, 7, OFFERS);
        Assertions.assertEquals(2, planned.getPremiumRooms());
        Assertions.assertEquals(4, planned.getEconomyRooms());
    }

    @Test
    public void premium7Economy1() {
        Occupation planned = Planner.optimal(7, 1, OFFERS);
        Assertions.assertEquals(7, planned.getPremiumRooms());
        Assertions.assertEquals(1, planned.getEconomyRooms());
    }
}

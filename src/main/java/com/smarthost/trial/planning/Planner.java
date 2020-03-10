package com.smarthost.trial.planning;

import com.smarthost.trial.model.Occupation;

import java.util.List;

public class Planner {

    public static Occupation optimal(int availablePremiumRooms, int availableEconomyRooms, List<Integer> offers) {
        return new Occupation(availablePremiumRooms, availableEconomyRooms);
    }

}

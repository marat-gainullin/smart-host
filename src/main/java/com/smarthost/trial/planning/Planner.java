package com.smarthost.trial.planning;

import com.smarthost.trial.model.Occupation;

import java.util.List;

public class Planner {

    public static Occupation optimal(int availablePremiumRooms, int availableEconomyRooms, List<Integer> offers) {
        var economyGuests = 0;
        var premiumGuests = 0;
        for (var offer : offers) {
            if (offer < 100) {
                economyGuests++;
            } else {
                premiumGuests++;
            }
        }
        var plannedEconomyRooms = Math.min(availableEconomyRooms, economyGuests);
        var plannedPremiumRooms = Math.min(availablePremiumRooms, premiumGuests);
        var restPremiumRooms = availablePremiumRooms - plannedPremiumRooms;
        var restEconomyGuests = economyGuests - plannedEconomyRooms;
        var upgradedPremiumRooms = Math.min(restPremiumRooms, restEconomyGuests);
        return new Occupation(plannedEconomyRooms, plannedPremiumRooms + upgradedPremiumRooms);
    }

}

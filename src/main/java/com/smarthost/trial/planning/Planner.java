package com.smarthost.trial.planning;

import com.smarthost.trial.model.Occupation;

public class Planner {

    public static Occupation optimal(int availablePremiumRooms, int availableEconomyRooms, Iterable<Integer> offers) {
        var plannedPremiumRooms = 0;
        var plannedEconomyRooms = 0;
        var economyGuests = 0;
        var economyRoomsSum = 0;
        var premiumGuests = 0;
        var premiumRoomsSum = 0;
        var upgradedRoomsSum = 0;
        for (var offer : offers) {
            if (offer >= 100) {
                premiumGuests++;
                if (plannedPremiumRooms < availablePremiumRooms) {
                    plannedPremiumRooms++;
                    premiumRoomsSum += offer;
                }
            } else {
                economyGuests++;
                if (plannedEconomyRooms < availableEconomyRooms) {
                    plannedEconomyRooms++;
                    economyRoomsSum += offer;
                } else if (plannedPremiumRooms < availablePremiumRooms) {
                    plannedPremiumRooms++;
                    upgradedRoomsSum += offer;
                }
            }
        }
        return new Occupation(plannedPremiumRooms, premiumRoomsSum, plannedEconomyRooms, economyRoomsSum);
    }

}

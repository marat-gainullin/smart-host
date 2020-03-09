package com.smarthost.trial.model;

/**
 * POJO occupation plan container.
 */
public class Occupation {
    private final int economyRooms;
    private final int premiumRooms;

    /**
     * Constructs an occupation plan.
     * @param aEconomyRooms The number of occupied economy rooms.
     * @param aPremiumRooms The number of occupied premium rooms.
     */
    public Occupation(final int aEconomyRooms, final int aPremiumRooms) {
        economyRooms = aEconomyRooms;
        premiumRooms = aPremiumRooms;
    }

    public int getEconomyRooms() {
        return economyRooms;
    }

    public int getPremiumRooms() {
        return premiumRooms;
    }
}

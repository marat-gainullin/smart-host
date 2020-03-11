package com.smarthost.trial.model;

/**
 * POJO occupation plan container.
 */
public class Occupation {
    private final int premiumRooms;
    private final int premiumRoomsSum;
    private final int economyRooms;
    private final int economyRoomsSum;

    /**
     * Constructs an occupation plan.
     *
     * @param aPremiumRooms    The number of occupied premium rooms.
     * @param aPremiumRoomsSum The amount of money for premium rooms.
     * @param aEconomyRooms    The number of occupied economy rooms.
     * @param aEconomyRoomsSum The amount of money for economy rooms.
     */
    public Occupation(final int aPremiumRooms, final int aPremiumRoomsSum, final int aEconomyRooms, final int aEconomyRoomsSum) {
        premiumRooms = aPremiumRooms;
        premiumRoomsSum = aPremiumRoomsSum;
        economyRooms = aEconomyRooms;
        economyRoomsSum = aEconomyRoomsSum;
    }

    public int getPremiumRooms() {
        return premiumRooms;
    }

    public int getPremiumRoomsSum() {
        return premiumRoomsSum;
    }

    public int getEconomyRooms() {
        return economyRooms;
    }

    public int getEconomyRoomsSum() {
        return economyRoomsSum;
    }
}

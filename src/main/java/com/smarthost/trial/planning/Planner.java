package com.smarthost.trial.planning;

import com.smarthost.trial.model.Occupation;
import com.smarthost.trial.model.SortedBag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Planner implementation.
 * Hotel clients have two different categories of rooms: Premium and Economy.
 * Hotels want their customers to be satisfied: they will not book a customer willing to pay EUR 100 or more for the night into an Economy room.
 * But they will book lower paying customers into Premium rooms if these rooms would be empty and all Economy rooms will be filled by low paying customers.
 * Highest paying customers below EUR 100 will get preference for the “upgrade”.
 * Customers always only have one specific price they are willing to pay for the night.
 *
 * However it is possible to have several offers with same prices from different customers.
 */
public final class Planner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Planner.class);

    private final SortedBag<Integer> premiumOffers;
    private final SortedBag<Integer> economyOffers;

    /**
     * Planner constructor, accepting two ordered collections of offers.
     *
     * @param aPremiumOffers {@link SortedBag} of premium offers from customers.
     * @param aEconomyOffers {@link SortedBag} of economy offers from customers.
     */
    public Planner(SortedBag<Integer> aPremiumOffers, SortedBag<Integer> aEconomyOffers) {
        premiumOffers = aPremiumOffers;
        economyOffers = aEconomyOffers;
    }

    /**
     * Plans optimal use of premium and economy rooms. Takes into account, that there are might be same prices for different customers.
     *
     * @param availablePremiumRooms The number of available premium rooms.
     * @param availableEconomyRooms The number of available economy rooms.
     * @return {@link Occupation} instance, describing planned occupation in terms of rooms and money.
     */
    public Occupation optimal(int availablePremiumRooms, int availableEconomyRooms) {
        if (availablePremiumRooms < 0) {
            throw new IllegalArgumentException("'availablePremiumRooms' can't be less than zero");
        }
        if (availableEconomyRooms < 0) {
            throw new IllegalArgumentException("'availableEconomyRooms' can't be less than zero");
        }
        var plannedPremiumRooms = 0;
        var plannedEconomyRooms = 0;
        var plannedEconomyRoomsSum = 0;
        var plannedPremiumRoomsSum = 0;
        for (var price : premiumOffers) {
            if (plannedPremiumRooms < availablePremiumRooms) {
                plannedPremiumRooms++;
                plannedPremiumRoomsSum += price;
                LOGGER.info("Premium room planned for {}", price);
            }
        }
        int missingEconomyRooms = economyOffers.size() - availableEconomyRooms;
        for (var price : economyOffers) {
            if (missingEconomyRooms-- > 0 && plannedPremiumRooms < availablePremiumRooms) {
                plannedPremiumRooms++;
                plannedPremiumRoomsSum += price;
                LOGGER.info("Upgrade room planned for {}", price);
            } else if (plannedEconomyRooms < availableEconomyRooms) {
                plannedEconomyRooms++;
                plannedEconomyRoomsSum += price;
                LOGGER.info("Economy room planned for {}", price);
            }
        }
        return new Occupation(plannedPremiumRooms, plannedPremiumRoomsSum, plannedEconomyRooms, plannedEconomyRoomsSum);
    }

}

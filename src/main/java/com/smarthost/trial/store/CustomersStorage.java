package com.smarthost.trial.store;

import com.smarthost.trial.model.SortedBag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Comparator;

/**
 * Stores customers offers.
 * Splits the offers into premium and economy classes to avoid unnecessary iterations while planning in
 * the situation when there are free premium rooms after planning and a hotel has enough economy rooms.
 */
public final class CustomersStorage {

    private static final int PREMIUM_THRESHOLD = Integer.getInteger("smarthost.premium.threshold", 100);
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomersStorage.class);

    private final SortedBag<Integer> premiumOffers = new SortedBag<>(Comparator.<Integer>reverseOrder());
    private final SortedBag<Integer> economyOffers = new SortedBag<>(Comparator.<Integer>reverseOrder());

    private CustomersStorage() {
    }

    public static CustomersStorage of(Iterable<Integer> prices) {
        CustomersStorage storage = new CustomersStorage();
        for (var price : prices) {
            if (price != null) {
                if (price > 0) {
                    if (price >= PREMIUM_THRESHOLD) {
                        storage.getPremiumOffers().add(price);
                    } else {
                        storage.getEconomyOffers().add(price);
                    }
                } else {
                    LOGGER.info("Zero price value has been skipped");
                }
            } else {
                LOGGER.info("Null price value has been skipped");
            }
        }
        return storage;
    }

    public SortedBag<Integer> getPremiumOffers() {
        return premiumOffers;
    }

    public SortedBag<Integer> getEconomyOffers() {
        return economyOffers;
    }
}

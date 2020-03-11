package com.smarthost.trial.services;

import com.smarthost.trial.model.Occupation;
import com.smarthost.trial.planning.Planner;
import com.smarthost.trial.store.CustomersStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Occupation planner. Plans optimal occupation of economy and premium rooms.
 */
@Service
public class OccupationsPlanner {

    @Autowired
    private CustomersStorage customersStorage;

    public Occupation optimal(int availablePremiumRooms, int availableEconomyRooms) {
        return Planner.optimal(availablePremiumRooms, availableEconomyRooms, customersStorage.getPremiumOffers(), customersStorage.getEconomyOffers());
    }
}

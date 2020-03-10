package com.smarthost.trial.services;

import com.smarthost.trial.model.Occupation;
import com.smarthost.trial.planning.Planner;
import com.smarthost.trial.store.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Occupation planner. Plans optimal occupation of economy and premium rooms.
 */
@Service
public class OccupationsPlanner {

    @Autowired
    private Customers customers;

    public Occupation optimal(int availablePremiumRooms, int availableEconomyRooms) {
        return Planner.optimal(availablePremiumRooms, availableEconomyRooms, customers.getOffers());
    }
}

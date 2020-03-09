package com.smarthost.trial.services;

import com.smarthost.trial.model.Occupation;
import org.springframework.stereotype.Service;

/**
 * Occupation planner. Plans optimal occupation of economy and premium rooms.
 */
@Service
public class OccupationsPlanner {

    public Occupation optimal(int freeEconomyRooms, int freePremiumRooms){
        return new Occupation(freeEconomyRooms, freePremiumRooms);
    }
}

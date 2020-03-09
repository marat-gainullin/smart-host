package com.smarthost.trial.endpoints;

import com.smarthost.trial.services.OccupationsPlanner;
import com.smarthost.trial.model.Occupation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * RESTful endpoint for rooms occupations planning.
 */
@RestController
public class Occupations {

    @Autowired
    private OccupationsPlanner planner;

    @GetMapping(path = "/occupations/optimal")
    public Occupation findAnOptimalOccupation(@RequestParam(name = "availableEconomy") int availableEconomy, @RequestParam(name = "availablePremium") int availablePremium) {
        return planner.optimal(availableEconomy, availablePremium);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorDescription onBadRequest(ServletRequestBindingException ex) {
        return ErrorDescription.of(HttpStatus.BAD_REQUEST, 1, ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorDescription onBadRequest(MethodArgumentTypeMismatchException ex) {
        return ErrorDescription.of(HttpStatus.BAD_REQUEST, 2, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorDescription onOtherErrors() {
        return ErrorDescription.ofInternal();
    }
}

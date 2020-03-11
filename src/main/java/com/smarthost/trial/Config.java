package com.smarthost.trial;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarthost.trial.store.CustomersStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Configuration
public class Config {

    private static final File CUSTOMERS_FILE = new File(System.getProperty("smarthost.customers.file", "customers.json"));
    private static final ObjectMapper JSON = new ObjectMapper();

    @Bean
    public CustomersStorage createCustomersStorage() throws IOException {
        return CustomersStorage.of(JSON.readValue(CUSTOMERS_FILE, List.class));
    }
}

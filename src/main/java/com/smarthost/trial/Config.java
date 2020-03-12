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
    /**
     * The {@link ObjectMapper} instances are thread safe according to the JavaDoc, so we have here only one instance of the {@link ObjectMapper} at all.
     * @see ObjectMapper
     */
    private static final ObjectMapper JSON = new ObjectMapper();

    /**
     * This factory method is implemented to avoid exception prone work, e.g. reading from file in CustomersStorage's constructor.
     * @return A {@link CustomersStorage} instance, initialized with prices offered by the customers.
     * @throws IOException When problems while the file reading occur.
     */
    @Bean
    public CustomersStorage createCustomersStorage() throws IOException {
        return CustomersStorage.of(JSON.readValue(CUSTOMERS_FILE, List.class));
    }
}

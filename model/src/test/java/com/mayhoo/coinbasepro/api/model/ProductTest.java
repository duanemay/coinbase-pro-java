package com.mayhoo.coinbasepro.api.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class ProductTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void deserialize() throws IOException {
        String json = new String(getClass().getClassLoader().getResourceAsStream("get_products_200.json").readAllBytes());
        Product[] orders = mapper.readValue(json, Product[].class);
        assertThat(orders).hasSize(1);
        assertThat(orders[0])
                .hasFieldOrPropertyWithValue("id", "BTC-USD")
                .hasFieldOrPropertyWithValue("baseCurrency", "BTC")
                .hasFieldOrPropertyWithValue("quoteCurrency", "USD")
                .hasFieldOrPropertyWithValue("baseMinSize", 0.00100000d)
                .hasFieldOrPropertyWithValue("baseMaxSize", 280.00000000d)
                .hasFieldOrPropertyWithValue("quoteIncrement", 0.01000000d);
    }
}
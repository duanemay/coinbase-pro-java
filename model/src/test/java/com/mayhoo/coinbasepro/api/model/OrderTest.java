package com.mayhoo.coinbasepro.api.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class OrderTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void deserialize() throws IOException {
        String json = new String(getClass().getClassLoader().getResourceAsStream("get_orders_200.json").readAllBytes());
        Order[] orders = mapper.readValue(json, Order[].class);
        assertThat(orders).hasSize(1);
        assertThat(orders[0])
                .hasFieldOrPropertyWithValue("id", "a9625b04-fc66-4999-a876-543c3684d702")
                .hasFieldOrPropertyWithValue("price", "10.00000000")
                .hasFieldOrPropertyWithValue("size", "1.00000000")
                .hasFieldOrPropertyWithValue("productId", "BTC-USD")
                .hasFieldOrPropertyWithValue("side", "buy")
                .hasFieldOrPropertyWithValue("type", "limit")
                .hasFieldOrPropertyWithValue("timeInForce", "GTC")
                .hasFieldOrPropertyWithValue("postOnly", "true")
                .hasFieldOrPropertyWithValue("createdAt", "2020-03-11T20:48:46.622052Z")
                .hasFieldOrPropertyWithValue("fillFees", "0.0000000000000000")
                .hasFieldOrPropertyWithValue("filledSize", "0.00000000")
                .hasFieldOrPropertyWithValue("executedValue", "0.0000000000000000")
                .hasFieldOrPropertyWithValue("status", "open")
                .hasFieldOrPropertyWithValue("settled", false);
    }
}
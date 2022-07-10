package com.mayhoo.coinbasepro.api.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class OrderItemTest {

    @Test
    void testConstructor() {
        var strings = List.of("3.45", "12.2", "323232323232", "");
        assertThat(new OrderItem(strings))
                .hasFieldOrPropertyWithValue("price", BigDecimal.valueOf(3.45))
                .hasFieldOrPropertyWithValue("size", BigDecimal.valueOf(12.2))
                .hasFieldOrPropertyWithValue("orderId", "323232323232")
                .hasFieldOrPropertyWithValue("num", BigDecimal.ONE);
    }
}
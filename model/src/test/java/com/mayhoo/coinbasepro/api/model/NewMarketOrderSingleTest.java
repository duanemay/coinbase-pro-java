package com.mayhoo.coinbasepro.api.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class NewMarketOrderSingleTest {
    @Test
    void constructorSetsLimit() {
        NewMarketOrderSingle order = NewMarketOrderSingle.builder()
                .size(BigDecimal.TEN)
                .side("side")
                .stp("stp")
                .funds("funds")
                .productId("productId")
                .clientOid("clientOid")
                .build();

        assertThat(order)
                .hasFieldOrPropertyWithValue("size", BigDecimal.TEN)
                .hasFieldOrPropertyWithValue("side", "side")
                .hasFieldOrPropertyWithValue("stp", "stp")
                .hasFieldOrPropertyWithValue("funds", "funds")
                .hasFieldOrPropertyWithValue("productId", "productId")
                .hasFieldOrPropertyWithValue("clientOid", "clientOid")
                .hasFieldOrPropertyWithValue("type", "market");
    }
}
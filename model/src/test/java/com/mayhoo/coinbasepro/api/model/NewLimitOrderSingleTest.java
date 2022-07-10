package com.mayhoo.coinbasepro.api.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class NewLimitOrderSingleTest {
    @Test
    void constructorSetsLimit() {
        NewLimitOrderSingle order = NewLimitOrderSingle.builder()
                .price("price")
                .size("size")
                .side("side")
                .stp("stp")
                .funds("funds")
                .postOnly(true)
                .productId("productId")
                .clientOid("clientOid")
                .build();

        assertThat(order)
                .hasFieldOrPropertyWithValue("price", "price")
                .hasFieldOrPropertyWithValue("size", "size")
                .hasFieldOrPropertyWithValue("side", "side")
                .hasFieldOrPropertyWithValue("stp", "stp")
                .hasFieldOrPropertyWithValue("funds", "funds")
                .hasFieldOrPropertyWithValue("postOnly", true)
                .hasFieldOrPropertyWithValue("productId", "productId")
                .hasFieldOrPropertyWithValue("clientOid", "clientOid")
                .hasFieldOrPropertyWithValue("type", "limit");

    }
}
package com.mayhoo.coinbasepro.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class SignatureImplTest {
    @Test
    void generate() {
        SignatureImpl signature = new SignatureImpl("shhh");
        String generated = signature.generate("request", "method", "body", "timestamp");
        assertThat(generated).endsWith("=");
    }
}
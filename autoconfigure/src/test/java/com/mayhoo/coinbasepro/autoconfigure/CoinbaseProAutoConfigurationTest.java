package com.mayhoo.coinbasepro.autoconfigure;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.exchange.Signature;
import com.mayhoo.coinbasepro.api.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CoinbaseProAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    @Test
    void properties() {
        this.contextRunner
                .withPropertyValues("coinbasepro.key=key",
                        "coinbasepro.passphrase=passphrase",
                        "coinbasepro.baseUrl=http://baseUrl",
                        "coinbasepro.secret=secret")
                .withUserConfiguration(CoinbaseProAutoConfiguration.class)
                .run(context -> {
                    assertThat(context)
                            .getBean(CoinbaseProProperties.class)
                            .hasFieldOrPropertyWithValue("key", "key")
                            .hasFieldOrPropertyWithValue("passphrase", "passphrase")
                            .hasFieldOrPropertyWithValue("baseUrl", "http://baseUrl")
                            .hasFieldOrPropertyWithValue("secret", "secret");
                });
    }

    @Test
    void configuresCoinbase_WithSignature() {
        this.contextRunner
                .withUserConfiguration(CoinbaseProAutoConfiguration.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(Signature.class);
                });
    }

    @Test
    void configuresCoinbase_WithCoinbaseProExchange() {
        this.contextRunner
                .withUserConfiguration(CoinbaseProAutoConfiguration.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(CoinbaseProExchange.class);
                });
    }

    @Test
    void configuresCoinbase_WithServices() {
        this.contextRunner
                .withUserConfiguration(CoinbaseProAutoConfiguration.class)
                .run(context -> {
                    assertThat(context)
                            .hasSingleBean(AccountService.class)
                            .hasSingleBean(DepositService.class)
                            .hasSingleBean(MarketDataService.class)
                            .hasSingleBean(OrderService.class)
                            .hasSingleBean(PaymentService.class)
                            .hasSingleBean(ProductService.class)
                            .hasSingleBean(ReportService.class)
                            .hasSingleBean(TransferService.class)
                            .hasSingleBean(UserAccountService.class)
                            .hasSingleBean(WithdrawalsService.class);
                });
    }

    @Test
    void configuresCoinbase_WithRestTemplate() {
        this.contextRunner
                .withUserConfiguration(RestTemplateConfiguration.class, CoinbaseProAutoConfiguration.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(RestTemplate.class);
                });
    }

    @Configuration
    static class RestTemplateConfiguration {
        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }
}
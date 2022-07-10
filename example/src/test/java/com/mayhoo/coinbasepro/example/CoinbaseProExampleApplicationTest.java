package com.mayhoo.coinbasepro.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class CoinbaseProExampleApplicationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    @Test
    void findsCoinbaseProExampleApplicationRunner() {
        this.contextRunner
                .withUserConfiguration(CoinbaseProExampleApplication.class)
                .run(context -> {
                    CommandLineRunner runner = context.getBean(CommandLineRunner.class);
                    assertThat(runner).isNotNull();
                });
    }

    @Test
    void testMain() {
        final PrintStream original = System.out;
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        try {
            CoinbaseProExampleApplication.main(new String[]{});
            assertThat(outputStreamCaptor.toString())
                    .contains("coinbaseProExchange: com.mayhoo.coinbasepro.impl.CoinbaseProExchangeImpl")
                    .contains("accountService: com.mayhoo.coinbasepro.impl.AccountServiceImpl")
                    .contains("depositService: com.mayhoo.coinbasepro.impl.DepositServiceImpl")
                    .contains("marketDataService: com.mayhoo.coinbasepro.impl.MarketDataServiceImpl")
                    .contains("orderService: com.mayhoo.coinbasepro.impl.OrderServiceImpl")
                    .contains("paymentService: com.mayhoo.coinbasepro.impl.PaymentServiceImpl")
                    .contains("productService: com.mayhoo.coinbasepro.impl.ProductServiceImpl")
                    .contains("reportService: com.mayhoo.coinbasepro.impl.ReportServiceImpl")
                    .contains("transferService: com.mayhoo.coinbasepro.impl.TransferServiceImpl")
                    .contains("userAccountService: com.mayhoo.coinbasepro.impl.UserAccountServiceImpl")
                    .contains("withdrawalsService: com.mayhoo.coinbasepro.impl.WithdrawalsServiceImpl")
                    .contains("com.mayhoo.coinbasepro.autoconfigure.CoinbaseProProperties");
        } finally {
            System.setOut(original);
        }
    }
}
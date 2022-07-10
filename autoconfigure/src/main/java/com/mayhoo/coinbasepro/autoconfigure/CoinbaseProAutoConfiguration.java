package com.mayhoo.coinbasepro.autoconfigure;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.exchange.Signature;
import com.mayhoo.coinbasepro.api.service.*;
import com.mayhoo.coinbasepro.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConditionalOnClass(CoinbaseProExchangeImpl.class)
@EnableConfigurationProperties(CoinbaseProProperties.class)
public class CoinbaseProAutoConfiguration {

    @Autowired
    private CoinbaseProProperties coinbaseProProperties() {
        return new CoinbaseProProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @ConditionalOnMissingBean
    public Signature signature(CoinbaseProProperties properties) {
        return new SignatureImpl(properties.getSecret());
    }

    @Bean
    @Autowired
    @ConditionalOnMissingBean
    public CoinbaseProExchange coinbaseProExchange(CoinbaseProProperties properties,
                                                   Signature signature,
                                                   RestTemplate restTemplate) {

        return new CoinbaseProExchangeImpl(properties.getKey(), properties.getPassphrase(), properties.getBaseUrl(), signature, restTemplate);
    }

    @Bean
    @Autowired
    AccountService accountService(CoinbaseProExchange coinbaseProExchange) {
        return new AccountServiceImpl(coinbaseProExchange);
    }

    @Bean
    @Autowired
    DepositService depositService(CoinbaseProExchange coinbaseProExchange) {
        return new DepositServiceImpl(coinbaseProExchange);
    }

    @Bean
    @Autowired
    MarketDataService marketDataService(CoinbaseProExchange coinbaseProExchange) {
        return new MarketDataServiceImpl(coinbaseProExchange);
    }

    @Bean
    @Autowired
    OrderService orderService(CoinbaseProExchange coinbaseProExchange) {
        return new OrderServiceImpl(coinbaseProExchange);
    }

    @Bean
    @Autowired
    PaymentService paymentService(CoinbaseProExchange coinbaseProExchange) {
        return new PaymentServiceImpl(coinbaseProExchange);
    }

    @Bean
    @Autowired
    ProductService productService(CoinbaseProExchange coinbaseProExchange) {
        return new ProductServiceImpl(coinbaseProExchange);
    }

    @Bean
    @Autowired
    ReportService reportService(CoinbaseProExchange coinbaseProExchange) {
        return new ReportServiceImpl(coinbaseProExchange);
    }

    @Bean
    @Autowired
    TransferService transferService(CoinbaseProExchange coinbaseProExchange) {
        return new TransferServiceImpl(coinbaseProExchange);
    }

    @Bean
    @Autowired
    UserAccountService userAccountService(CoinbaseProExchange coinbaseProExchange) {
        return new UserAccountServiceImpl(coinbaseProExchange);
    }

    @Bean
    @Autowired
    WithdrawalsService withdrawalsService(CoinbaseProExchange coinbaseProExchange) {
        return new WithdrawalsServiceImpl(coinbaseProExchange);
    }
}
package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.exchange.Signature;
import com.mayhoo.coinbasepro.api.model.Account;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import static com.mayhoo.coinbasepro.assertj.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

class CoinbaseProExchangeImplTest {
    private MockRestServiceServer mockServer;
    private CoinbaseProExchange coinbaseProExchange;

    @BeforeEach
    public void setUp() {
        RestTemplate restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);
        Signature signature = new SignatureImpl("secret");
        coinbaseProExchange = new CoinbaseProExchangeImpl("key",
                "pass", "http://localhost:12345", signature, restTemplate);
    }

    @Test
    void getters() {
        assertThat(coinbaseProExchange)
                .hasBaseUrl("http://localhost:12345")
                .hasFieldOrPropertyWithValue("publicKey", "key")
                .hasFieldOrPropertyWithValue("passphrase", "pass")
                .hasFieldOrProperty("signature")
                .hasFieldOrProperty("restTemplate");
    }

    @Test
    void toStringHidesSecuredFields() {
        assertThat(coinbaseProExchange)
                .hasToString("CoinbaseProExchangeImpl(baseUrl=http://localhost:12345)");
    }

    @Test
    void getArray() throws URISyntaxException, IOException {
        String json = new String(getClass().getClassLoader().getResourceAsStream("get_accounts_200.json").readAllBytes());

        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:12345/account")))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header("CB-ACCESS-KEY", "key"))
                .andExpect(header("CB-ACCESS-PASSPHRASE", "pass"))
                .andExpect(header("CB-ACCESS-SIGN", Matchers.anything()))
                .andExpect(header("CB-ACCESS-TIMESTAMP", Matchers.anything()))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(json)
                );

        Account[] accounts = coinbaseProExchange.get("/account", Account[].class);
        assertThat(accounts).hasSize(2);
        assertThat(accounts[0])
                .hasId("8058d771-2d88-4f0f-ab6e-299c153d4300")
                .hasCurrency("USD")
                .hasBalance(new BigDecimal("120.0000000000000000"))
                .hasAvailable(new BigDecimal("100.0000000000000000"))
                .hasHold(new BigDecimal("20.0000000000000000"))
                .hasProfileId("7fd0abc0-e5ad-4cbb-8d54-f2b3f43364d0");
        assertThat(accounts[1])
                .hasId("7fd0abc0-e5ad-4cbb-8d54-f2b3f43364d1")
                .hasCurrency("USD")
                .hasBalance(new BigDecimal("12.0000000000000000"))
                .hasAvailable(new BigDecimal("10.0000000000000000"))
                .hasHold(new BigDecimal("2.0000000000000000"))
                .hasProfileId("8058d771-2d88-4f0f-ab6e-299c153d4301");
    }

    @Test
    void getSingle() throws URISyntaxException, IOException {
        String json = new String(getClass().getClassLoader().getResourceAsStream("get_account_200.json").readAllBytes());

        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:12345/account/7fd0abc0-e5ad-4cbb-8d54-f2b3f43364da")))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header("CB-ACCESS-KEY", "key"))
                .andExpect(header("CB-ACCESS-PASSPHRASE", "pass"))
                .andExpect(header("CB-ACCESS-SIGN", Matchers.anything()))
                .andExpect(header("CB-ACCESS-TIMESTAMP", Matchers.anything()))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(json)
                );

        Account account = coinbaseProExchange.get("/account/7fd0abc0-e5ad-4cbb-8d54-f2b3f43364da", Account.class);
        assertThat(account)
                .hasId("7fd0abc0-e5ad-4cbb-8d54-f2b3f43364da")
                .hasCurrency("USD")
                .hasBalance(new BigDecimal("12.0000000000000000"))
                .hasAvailable(new BigDecimal("10.0000000000000000"))
                .hasHold(new BigDecimal("2.0000000000000000"))
                .hasProfileId("8058d771-2d88-4f0f-ab6e-299c153d4308");
    }


    @Test
    void get_throwsError() throws URISyntaxException, IOException {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:12345/account/7fd0abc0-e5ad-4cbb-8d54-f2b3f43364da")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.UNAUTHORIZED));

        Account account = coinbaseProExchange.get("/account/7fd0abc0-e5ad-4cbb-8d54-f2b3f43364da", Account.class);
        assertThat(account).isNull();
    }

    @Test
    void pagedGet() throws URISyntaxException, IOException {
        String json = new String(getClass().getClassLoader().getResourceAsStream("get_accounts_200.json").readAllBytes());

        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:12345/account?before=12&limit=50")))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header("CB-ACCESS-KEY", "key"))
                .andExpect(header("CB-ACCESS-PASSPHRASE", "pass"))
                .andExpect(header("CB-ACCESS-SIGN", Matchers.anything()))
                .andExpect(header("CB-ACCESS-TIMESTAMP", Matchers.anything()))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(json)
                );

        Account[] accounts = coinbaseProExchange.pagedGet("/account", Account[].class, "before", 12, 50);
        assertThat(accounts).hasSize(2);
    }

    @Test
    void delete() throws URISyntaxException, IOException {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:12345/account/7fd0abc0-e5ad-4cbb-8d54-f2b3f43364da")))
                .andExpect(method(HttpMethod.DELETE))
                .andExpect(header("CB-ACCESS-KEY", "key"))
                .andExpect(header("CB-ACCESS-PASSPHRASE", "pass"))
                .andExpect(header("CB-ACCESS-SIGN", Matchers.anything()))
                .andExpect(header("CB-ACCESS-TIMESTAMP", Matchers.anything()))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body("7fd0abc0-e5ad-4cbb-8d54-f2b3f43364da")
                );

        String accountId = coinbaseProExchange.delete("/account/7fd0abc0-e5ad-4cbb-8d54-f2b3f43364da", String.class);
        assertThat(accountId)
                .isEqualTo("7fd0abc0-e5ad-4cbb-8d54-f2b3f43364da");
    }

    @Test
    void delete_throwsError() throws URISyntaxException, IOException {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:12345/account/7fd0abc0-e5ad-4cbb-8d54-f2b3f43364da")))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.UNAUTHORIZED));

        String accountId = coinbaseProExchange.delete("/account/7fd0abc0-e5ad-4cbb-8d54-f2b3f43364da", String.class);
        assertThat(accountId).isNull();
    }

    @Test
    void post() throws URISyntaxException, IOException {
        String json = new String(getClass().getClassLoader().getResourceAsStream("get_account_200.json").readAllBytes());
        Account postAccount = Account.builder().currency("ETH").build();

        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:12345/account")))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header("CB-ACCESS-KEY", "key"))
                .andExpect(header("CB-ACCESS-PASSPHRASE", "pass"))
                .andExpect(header("CB-ACCESS-SIGN", Matchers.anything()))
                .andExpect(header("CB-ACCESS-TIMESTAMP", Matchers.anything()))
                .andExpect(content().json("{\"currency\": \"ETH\"}"))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(json)
                );

        Account responseAccount = coinbaseProExchange.post("/account", Account.class, postAccount);
        assertThat(responseAccount)
                .hasId("7fd0abc0-e5ad-4cbb-8d54-f2b3f43364da")
                .hasCurrency("USD")
                .hasBalance(new BigDecimal("12.0000000000000000"))
                .hasAvailable(new BigDecimal("10.0000000000000000"))
                .hasHold(new BigDecimal("2.0000000000000000"))
                .hasProfileId("8058d771-2d88-4f0f-ab6e-299c153d4308");
    }

    @Test
    void post_throwsError() throws URISyntaxException, IOException {
        Account postAccount = Account.builder().currency("ETH").build();

        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:12345/account")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.UNAUTHORIZED));

        Account responseAccount = coinbaseProExchange.post("/account", Account.class, postAccount);
        assertThat(responseAccount).isNull();
    }
}
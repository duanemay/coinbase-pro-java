# Coinbase Pro Java

Java based services to call the [Coinbase Pro API](https://docs.pro.coinbase.com/) that follows the development style
similar to [coinbase-java](https://github.com/coinbase/coinbase-java)

## Notes:
> Coinbase Exchange [data centers](https://docs.cloud.coinbase.com/exchange/docs/data-centers) are in the Amazon US East N. Virginia (us-east-1) region. To minimize latency for API access, we recommend making requests from servers located near this data center.
> This codebase is maintained independently of Coinbase. I am not in any way affiliated with coinbase or coinbase pro.

The Services and Data objects returned should match the interface specified in the Coinbase Pro api: [https://docs.pro.coinbase.com/#api](https://docs.pro.coinbase.com/#api).

## Prerequisites

You will need to have an account on the [Coinbase Pro API](https://docs.pro.coinbase.com/) and obtain a valid API key for your account at [API Settings](https://pro.coinbase.com/profile/api).

## Usage
To make use of this library, in a spring boot application, just add a dependency to the autoconfigure module,

```xml
<dependency>
    <groupId>com.mayhoo</groupId>
    <artifactId>coinbasepro-autoconfigure</artifactId>
    <version>${coinbasepro.version}</version>
</dependency>
```

You will need to add properties with your own coinbase pro api key, secret, and passphrase. (Testing can be done with a [sandbox key](https://docs.cloud.coinbase.com/exchange/docs/sandbox).)

```properties
#coinbasepro.baseUrl=https://api-public.sandbox.pro.coinbase.com/
coinbasepro.baseUrl=https://api.pro.coinbase.com/
coinbasepro.key=KEY
coinbasepro.secret=SECRET
coinbasepro.passphrase=PASSPHRASE
```

You will be able to autowire and use the services that you want.

```java
@Bean
@Autowired
public MyClassToSubmitOrders myClassToSubmitOrders(OrderService orderService) {
        return new MyClassToSubmitOrdersImpl(orderService);
}
```

In your method you can carry out any of the public api operations such
as `orderService().createOrder(order);` - this creates a limit order. 

In non-Spring Boot applications, you can similarly use the classes directly, without the autowiring by adding a dependency on the `coinbasepro-impl` module.

## API
This library mimics the [Coinbase API](https://docs.cloud.coinbase.com/exchange/reference/exchangerestapi_getaccounts).  

These are the main services that are available:

### **TransferService**, **DepositService**, and **WithdrawalsService**
  are used to transfer money into and out of your Coinbase Pro account. It can be between a bank or Coinbase account and a Coinbase Pro account.

### **OrderService** 
  Is used to create, cancel, and get info on orders.

### **ProductService**
  Gets a list of available currency pairs for trading.

### **MarketDataService**
  Is used to access market data. (this service is public)

### **PaymentService**
  Gets a list of the user's linked payment methods. Also gets all the user's available Coinbase wallets (These are the wallets/accounts that are used for buying and selling on www.coinbase.com)

### **ReportService**
  Reports are either for past account history or past fills on either all accounts or one product's account.

### **AccountService** and **UserAccountService**
  Gets the user's account information.

### *CoinbaseProExchangeImpl* and *SignatureImpl* 
  These classes are used internally to handle all the authentication, signing, and JSON Marshalling/Unmarshalling aspects of the api. It is used by the above services to make calls to the api, it can be used to add additional calls that are not yet supported by this library.

## Credits

This library was originally forked from [irufus/gdax-java](https://github.com/irufus/gdax-java). It has many changes and adds a high level of test coverage.

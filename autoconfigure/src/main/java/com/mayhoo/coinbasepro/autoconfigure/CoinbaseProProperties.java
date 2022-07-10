package com.mayhoo.coinbasepro.autoconfigure;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "coinbasepro")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"key", "passphrase", "secret"})
public class CoinbaseProProperties {
    String key;
    String passphrase;
    String baseUrl;
    String secret;
}
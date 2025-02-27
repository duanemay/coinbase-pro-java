package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.Signature;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.management.RuntimeErrorException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
@Value
@RequiredArgsConstructor
@ToString(exclude = {"secretKey"})
public class SignatureImpl implements Signature {
    private static final Mac SHARED_MAC;

    static {
        Mac macValue = null;
        try {
            macValue = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException nsaEx) {
            log.error("Error creating HmacSHA256 Mac", nsaEx);
        }
        SHARED_MAC = macValue;
    }

    String secretKey;

    /**
     * The CB-ACCESS-SIGN header is generated by creating a sha256 HMAC using
     * the base64-decoded secret key on the prehash string for:
     * timestamp + method + requestPath + body (where + represents string concatenation)
     * and base64-encode the output.
     * The timestamp value is the same as the CB-ACCESS-TIMESTAMP header.
     */
    @Override
    public String generate(String requestPath, String method, String body, String timestamp) {
        try {
            String prehash = timestamp + method.toUpperCase() + requestPath + body;
            byte[] secretDecoded = Base64.getDecoder().decode(secretKey);
            SecretKeySpec keyspec = new SecretKeySpec(secretDecoded, "HmacSHA256");
            Mac sha256 = (Mac) SHARED_MAC.clone();
            sha256.init(keyspec);
            return Base64.getEncoder().encodeToString(sha256.doFinal(prehash.getBytes()));
        } catch (CloneNotSupportedException | InvalidKeyException e) {
            log.error("Error in signature of authentication headers", e);
            throw new RuntimeErrorException(new Error("Cannot set up authentication headers."));
        }
    }
}
package com.mayhoo.coinbasepro.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewLimitOrderSingle extends NewOrderSingle {
    String size;
    String price;
    @JsonProperty("post_only")
    Boolean postOnly;

    @SuppressWarnings("java:S1144") // Used by Builder
    private NewLimitOrderSingle(final NewLimitOrderSingleBuilder<?, ?> b) {
        // need to set type to limit
        super(b.type("limit"));
        this.size = b.size;
        this.price = b.price;
        this.postOnly = b.postOnly;
    }
}
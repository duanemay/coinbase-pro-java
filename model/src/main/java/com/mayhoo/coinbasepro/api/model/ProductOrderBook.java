package com.mayhoo.coinbasepro.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductOrderBook {
    Integer sequence;
    List<List<String>> bids;
    List<List<String>> asks;
}
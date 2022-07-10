package com.mayhoo.coinbasepro.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
@RequiredArgsConstructor
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItem implements Comparable<OrderItem> {
    BigDecimal price;
    BigDecimal size;
    String orderId;
    BigDecimal num;

    @JsonCreator
    public OrderItem(List<String> limitOrders) {
        if (CollectionUtils.isEmpty(limitOrders) || limitOrders.size() < 3) {
            throw new IllegalArgumentException("LimitOrders was empty - check connection to the exchange");
        }
        price = new BigDecimal(limitOrders.get(0));
        size = new BigDecimal(limitOrders.get(1));
        if (isString(limitOrders.get(2))) {
            orderId = limitOrders.get(2);
        } else {
            orderId = null;
        }
        num = new BigDecimal(1);
    }

    private boolean isString(String value) {
        boolean isDecimalSeparatorFound = false;

        for (char c : value.substring(1).toCharArray()) {
            if (!Character.isDigit(c)) {
                if (c == '.' && !isDecimalSeparatorFound) {
                    isDecimalSeparatorFound = true;
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(OrderItem o) {
        return this.getPrice().compareTo((o).getPrice()) * -1;
    }
}
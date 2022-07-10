package com.mayhoo.coinbasepro.api.service;

import com.mayhoo.coinbasepro.api.model.CoinbaseAccounts;
import com.mayhoo.coinbasepro.api.model.PaymentTypes;

public interface PaymentService {
    PaymentTypes[] getPaymentTypes();

    CoinbaseAccounts[] getCoinbaseAccounts();
}
package com.mayhoo.coinbasepro.api.service;

import com.mayhoo.coinbasepro.api.model.Product;

public interface ProductService {
    // no paged products necessary
    Product[] getProducts();
}
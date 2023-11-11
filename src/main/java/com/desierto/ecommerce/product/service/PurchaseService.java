package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.product.request.PurchaseRequest;
import com.desierto.ecommerce.product.response.PurchaseResponse;

public interface PurchaseService {

    PurchaseResponse purchaseProduct(PurchaseRequest purchaseRequest);
}

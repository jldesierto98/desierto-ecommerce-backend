package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.product.request.PurchaseRequest;
import com.desierto.ecommerce.product.response.PurchaseResponse;

public interface PurchaseService {

    /**
     *
     * @param purchaseRequest
     * @return PurchaseResponse
     *
     * This method is responsible for the PURCHASE function of the app.
     * "Purchase" Button.
     */
    PurchaseResponse purchaseProduct(PurchaseRequest purchaseRequest);
}

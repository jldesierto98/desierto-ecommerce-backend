package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.product.entity.Country;

import java.util.List;

public interface CountryService {

    /**
     *
     * @return List<Country>
     * Gets all country and its states.
     */
    List<Country> getAllCountry();

    List<Country> getAllCountryByCountryCode(String code);

}

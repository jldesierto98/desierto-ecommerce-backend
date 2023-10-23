package com.desierto.ecommerce.product.service;


import com.desierto.ecommerce.product.entity.State;

import java.util.List;

public interface StateService {

    /**
     *
     * @return State
     * Gets all state available in the database.
     */
    List<State> getAllStates();

    /**
     *
     * @param countryId
     * @return List<State>
     * Get list of state by country ID.
     */
    List<State> getByCountryCode(Long countryId);
}

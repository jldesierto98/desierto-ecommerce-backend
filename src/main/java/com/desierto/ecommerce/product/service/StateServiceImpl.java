package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.product.entity.State;
import com.desierto.ecommerce.product.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;


    @Override
    public List<State> getAllStates() {
        return stateRepository.findAll();
    }

    @Override
    public List<State> getByCountryCode(Long countryId) {
        log.info("======_____ COUNTRY ID _____======= : " + countryId);
        return stateRepository.findByCountryId(countryId);
    }
}


package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.product.entity.Country;
import com.desierto.ecommerce.product.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CountryServiceImpl implements CountryService{

    private final CountryRepository countryRepository;

    @Override
    public List<Country> getAllCountry() {
        return countryRepository.findAll();
    }

    @Override
    public List<Country> getAllCountryByCountryCode(String code) {
        return countryRepository.findByCode(code);
    }
}

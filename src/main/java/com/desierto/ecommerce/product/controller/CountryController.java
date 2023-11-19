package com.desierto.ecommerce.product.controller;

import com.desierto.ecommerce.product.entity.Country;
import com.desierto.ecommerce.product.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/all")
    public ResponseEntity<List<Country>> getAllCountries(){
        return new ResponseEntity<>(countryService.getAllCountry(), HttpStatus.OK);
    }

    @PostMapping("/{countryCode}")
    public ResponseEntity<List<Country>> getCountryByCountryCode(@PathVariable("countryCode") String countryCode){
        return new ResponseEntity<>(countryService.getAllCountryByCountryCode(countryCode), HttpStatus.OK);
    }
}

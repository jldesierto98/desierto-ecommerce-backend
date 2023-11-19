package com.desierto.ecommerce.product.controller;

import com.desierto.ecommerce.product.entity.State;
import com.desierto.ecommerce.product.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/state")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class StateController {

    private final StateService stateService;

    @GetMapping("/all")
    public ResponseEntity<List<State>> getAllState(){
        return new ResponseEntity<>(stateService.getAllStates(), HttpStatus.OK);
    }

    @PostMapping("/{countryId}")
    public ResponseEntity<List<State>> getByCountryCode(@PathVariable("countryId") Long countryId){
        return new ResponseEntity<>(stateService.getByCountryCode(countryId), HttpStatus.OK);
    }



}

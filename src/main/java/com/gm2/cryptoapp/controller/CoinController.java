package com.gm2.cryptoapp.controller;

import com.gm2.cryptoapp.entity.Coin;
import com.gm2.cryptoapp.repository.CoinRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("/coin")
public class CoinController {
    
    private CoinRepository coinRepository;

    public CoinController(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody Coin coin){
        try {
            coin.setDatetime(new Timestamp(System.currentTimeMillis()));
            return new ResponseEntity<>(coinRepository.insert(coin), HttpStatus.CREATED);
        }catch (Exception error){
            return new ResponseEntity<>(error.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

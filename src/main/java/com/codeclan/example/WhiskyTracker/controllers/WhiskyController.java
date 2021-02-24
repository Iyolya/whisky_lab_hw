package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value = "/whiskies/year/{year}")
    public ResponseEntity<List<Whisky>> getAllWhiskiesOfYear(@PathVariable int year) {
        return new ResponseEntity<>(whiskyRepository.findByYearEquals(year), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/{distilleryName}/{whiskyAge}")
    public ResponseEntity<List<Whisky>> getAllWhiskiesByDistilleryAndAge(
            @PathVariable String distilleryName,
            @PathVariable int whiskyAge
    ) {
        List<Distillery> foundDistilleries = distilleryRepository.findDistilleryByName(distilleryName);
        if (foundDistilleries.size() == 1) {
            return new ResponseEntity<>(whiskyRepository.findByDistilleryAndAge(foundDistilleries.get(0), whiskyAge), HttpStatus.OK);}
        if (foundDistilleries.size() > 1) {
            return new ResponseEntity("Too many distillery results",HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity(distilleryName + " Not found", HttpStatus.NOT_FOUND);
        }
    }


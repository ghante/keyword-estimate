package com.estimater.keywordestimate.controllers;

import com.estimater.keywordestimate.service.EstimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class EstimateController {

    private EstimateService estimateService;

    @Autowired
    public EstimateController(EstimateService estimateService) {
        this.estimateService = estimateService;
    }

    @GetMapping("/estimate")
    public Estimate estimate(@RequestParam String keyword) throws IOException {
        return estimateService.estimateFor(keyword);
    }
}

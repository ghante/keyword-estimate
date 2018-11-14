package com.estimater.keywordestimate.controllers;

import com.estimater.keywordestimate.service.EstimateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EstimateControllerTest {

    private EstimateController controller;

    @Mock
    private EstimateService estimateService;

    @Before
    public void setUp() {
        controller = new EstimateController(estimateService);
    }

    @Test
    public void shouldEstimate() throws IOException {
        Estimate expectedEstimate = new Estimate(0);
        when(estimateService.estimateFor("galaxy s9")).thenReturn(expectedEstimate);

        Estimate estimate = controller.estimate("galaxy s9");

        assertThat(estimate, is(expectedEstimate));
    }

}
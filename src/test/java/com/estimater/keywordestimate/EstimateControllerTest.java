package com.estimater.keywordestimate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EstimateControllerTest {

    EstimateController controller;

    @Mock
    private EstimateService estimateService;

    @Mock
    private AutoCompleteClient autoCompleteClient;

    @Before
    public void setUp() {
        controller = new EstimateController(estimateService);
    }

    @Test
    public void shouldEstimate() throws IOException {
        Estimate expectedEstimate = new Estimate(0);
        List<String> keywords = Arrays.asList("galaxy s9 case",
                "galaxy s9 plus case",
                "galaxy s9 screen protector",
                "galaxy s9 plus screen protector",
                "galaxy s9 charger",
                "galaxy s9",
                "galaxy s9 charging cable",
                "galaxy s9 plus tempered glass",
                "galaxy s9 tempered glass screen protector",
                "galaxy s9 case otter box");
        when(autoCompleteClient.list("galaxy s9")).thenReturn(keywords);
        when(estimateService.estimateFor(new Keyword("galaxy s9"))).thenReturn(expectedEstimate);

        Estimate estimate = controller.estimate("galaxy s9");

        assertThat(estimate, is(expectedEstimate));
    }

}
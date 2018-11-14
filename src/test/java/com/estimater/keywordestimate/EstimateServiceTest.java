package com.estimater.keywordestimate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EstimateServiceTest {

    private EstimateService estimateService;

    @Mock
    private AutoCompleteClient autoCompleteClient;

    @Before
    public void setUp() throws IOException {
        estimateService = new EstimateService(autoCompleteClient);
        setUpSearchList();
    }

    @Test
    public void shouldScore100() throws IOException {
        Estimate estimate = estimateService.estimateFor("sd card");
        assertThat(estimate.getScore(), is(100));
    }

    @Test
    public void shouldScore99() throws IOException {
        Estimate estimate = estimateService.estimateFor("space heater");
        assertThat(estimate.getScore(), is(99));
    }

    @Test
    public void shouldScore91() throws IOException {
        Estimate estimate = estimateService.estimateFor("spray bottle");
        assertThat(estimate.getScore(), is(91));
    }

    @Test
    public void shouldScore63() throws IOException {
        Estimate estimate = estimateService.estimateFor("samsung note 9");
        assertThat(estimate.getScore(), is(53));
    }

    @Test
    public void shouldScore50() throws IOException {
        Estimate estimate = estimateService.estimateFor("samsung note 7");
        assertThat(estimate.getScore(), is(50));
    }

    @Test
    public void shouldScore34() throws IOException {
        Estimate estimate = estimateService.estimateFor("samsung");
        assertThat(estimate.getScore(), is(34));
    }

    @Test
    public void shouldScore0() throws IOException {
        Estimate estimate = estimateService.estimateFor("asdlk");
        assertThat(estimate.getScore(), is(0));
    }

    private void setUpSearchList() throws IOException {
        when(autoCompleteClient.list("s")).thenReturn(
                Arrays.asList("sd card",
                        "space heater",
                        "shower curtain liner mildew resistant",
                        "shower curtain",
                        "shoe rack",
                        "slime",
                        "sweaters for women",
                        "shower head",
                        "sonicare brush heads",
                        "spray bottle")
        );
        when(autoCompleteClient.list("sa")).thenReturn(
                Arrays.asList("samsung galaxy s8 charger",
                        "samsung galaxy s8 case",
                        "samsung galaxy s9 case",
                        "samsung galaxy s8 plus case",
                        "safety glasses",
                        "samsung galaxy s9 plus case",
                        "safety pins",
                        "salt lamp",
                        "satin pillowcase",
                        "salad spinner")
        );
        when(autoCompleteClient.list("sam")).thenReturn(
                Arrays.asList("samsung galaxy s8 charger",
                        "samsung galaxy s8 case",
                        "samsung galaxy s9 case",
                        "samsung galaxy s8 plus case",
                        "samsung galaxy s9 plus case",
                        "samsung galaxy note 8 case",
                        "samsung note 9 case",
                        "samsung galaxy s7 case",
                        "samsung galaxy s8 screen protector",
                        "samsung galaxy s9 screen protector")
        );
        when(autoCompleteClient.list("samsung n")).thenReturn(
                Arrays.asList("samsung note 9 case",
                        "samsung note 8 case",
                        "samsung note 9 screen protector",
                        "samsung note 8 screen protector",
                        "samsung note 9 charger",
                        "samsung note 8 charger",
                        "samsung note 4 battery",
                        "samsung note 9",
                        "samsung note 5 case",
                        "samsung note 8 stylus replacement")
        );
        when(autoCompleteClient.list("samsung note 7")).thenReturn(
                Arrays.asList("samsung note 7",
                        "samsung note 7 unlocked",
                        "samsung note 7 charger",
                        "samsung note 7 case",
                        "samsung note 7 phone",
                        "samsung note 7 battery",
                        "samsung note 7 screen protector",
                        "samsung note 7 fe",
                        "samsung note 7charger",
                        "samsung note 7 unlocked phone")
        );

        when(autoCompleteClient.list("a")).thenReturn(
                Arrays.asList("aaa batteries",
                        "aa batteries",
                        "amazon gift cards",
                        "apple watch band 38mm",
                        "apple watch band 42mm",
                        "airpods",
                        "apple earbuds",
                        "airpods case",
                        "apple watch charger",
                        "alarm clock")
        );
        when(autoCompleteClient.list("as")).thenReturn(
                Arrays.asList("ashwagandha",
                        "ashwagandha capsules",
                        "astroglide",
                        "assassins creed odyssey",
                        "asics running shoes women",
                        "asics running shoes mens",
                        "astronaut costume for kids",
                        "ashwagandha powder",
                        "astaxanthin",
                        "astrophysics for people in a hurry")
        );
        when(autoCompleteClient.list("asd")).thenReturn(
                Arrays.asList("asdm",
                        "asdm beverly hills",
                        "asdm beverly hills glycolic acid",
                        "asdam art",
                        "asd",
                        "asd vitamin",
                        "asd toys",
                        "asdf",
                        "asdm beverly hills tepezcohuite cream",
                        "asd visual schedules")
        );
        when(autoCompleteClient.list("asdl")).thenReturn(
                Arrays.asList("asdl modem router",
                        "asdl2 modem",
                        "asdl",
                        "asdl modem")
        );
        when(autoCompleteClient.list("asdlk")).thenReturn(
                Collections.emptyList()
        );
    }
}
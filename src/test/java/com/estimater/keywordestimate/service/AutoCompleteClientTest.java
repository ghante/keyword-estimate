package com.estimater.keywordestimate.service;

import com.estimater.keywordestimate.service.AutoCompleteClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoCompleteClientTest {

    @Autowired
    private AutoCompleteClient autoCompleteClient;

    @Test
    public void shouldCallAutoCompleteApi() throws IOException {
        List<String> expectedKeywords = Arrays.asList("galaxy s9 case",
                "galaxy s9 plus case",
                "galaxy s9 screen protector",
                "galaxy s9 plus screen protector",
                "galaxy s9 charger",
                "galaxy s9",
                "galaxy s9 charging cable",
                "galaxy s9 plus tempered glass",
                "galaxy s9 tempered glass screen protector",
                "galaxy s9 case otter box");

        List<String> keywords = autoCompleteClient.list("galaxy s9");

        assertThat(keywords, is(expectedKeywords));
    }
}
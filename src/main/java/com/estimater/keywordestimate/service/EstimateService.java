package com.estimater.keywordestimate.service;

import com.estimater.keywordestimate.controllers.Estimate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.getCommonPrefix;
import static org.apache.commons.lang3.StringUtils.substringAfter;

@Service
public class EstimateService {
    private AutoCompleteClient autoCompleteClient;

    @Autowired
    public EstimateService(AutoCompleteClient autoCompleteClient) {
        this.autoCompleteClient = autoCompleteClient;
    }

    public Estimate estimateFor(String keyword) throws IOException {
        String searchString = nextChar(keyword, "");
        List<String> keywords = autoCompleteClient.list(searchString);
        int score = 100;
        while (!keywords.contains(keyword) && !searchString.trim().equals(keyword) && score >= 0) {
            keywords = autoCompleteClient.list(searchString);
            String commonPrefix = longestCommonPrefix(keywords);
            searchString = commonPrefix + nextChar(keyword, commonPrefix);
            score = score - 10;
        }

        if (keywords.contains(keyword)) {
            return new Estimate(score - keywords.indexOf(keyword));
        }

        if (containedIn(keyword, keywords)) {
            return new Estimate(score - 36);
        }

        keywords = autoCompleteClient.list(searchString);
        if (keywords.contains(keyword)) {
            return new Estimate((score - 10) - keywords.indexOf(keyword));
        }

        return new Estimate(0);
    }

    private boolean containedIn(String keyword, List<String> keywords) {
        return longestCommonPrefix(keywords).trim().equals(keyword);
    }

    private String longestCommonPrefix(List<String> keywords) {
        return getCommonPrefix(keywords.toArray(new String[0]));
    }

    private String nextChar(String keyword, String commonPrefix) {
        String remainingPart = substringAfter(keyword, commonPrefix);
        return StringUtils.isBlank(remainingPart) ? "" : remainingPart.substring(0, 1);
    }
}

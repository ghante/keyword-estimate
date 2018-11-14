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
        int score = 100;
        String commonPrefix = "";
        String nextChar = nextChar(keyword, commonPrefix);
        List<String> results = autoCompleteClient.search(commonPrefix + nextChar);

        while (!results.contains(keyword)
                && !nextChar.isEmpty()
                && score >= 0) {
            results = autoCompleteClient.search(commonPrefix + nextChar);
            commonPrefix = longestCommonPrefix(results);
            nextChar = nextChar(keyword, commonPrefix);
            score = score - 10;
        }

        if (results.contains(keyword)) {
            return new Estimate(score - results.indexOf(keyword));
        }

        if (containedIn(keyword, results)) {
            return new Estimate(score - 36);
        }

        return new Estimate(0);
    }

    private boolean containedIn(String keyword, List<String> results) {
        return longestCommonPrefix(results).trim().equals(keyword);
    }

    private String longestCommonPrefix(List<String> keywords) {
        return getCommonPrefix(keywords.toArray(new String[0]));
    }

    private String nextChar(String keyword, String commonPrefix) {
        String remainingPart = substringAfter(keyword, commonPrefix);
        return StringUtils.isBlank(remainingPart) ? "" : remainingPart.substring(0, 1);
    }
}

package com.estimater.keywordestimate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class EstimateService {
    private AutoCompleteClient autoCompleteClient;

    @Autowired
    public EstimateService(AutoCompleteClient autoCompleteClient) {
        this.autoCompleteClient = autoCompleteClient;
    }

    public Estimate estimateFor(Keyword keyword) throws IOException {
        String keywordString = keyword.next("");
        List<String> keywords = autoCompleteClient.list(keywordString);
        int score = 100;
        while (!keyword.foundIn(keywords) && !keyword.sameAs(keywordString)) {
            score = score - 10;
            keywordString = keyword.next(StringUtils.getCommonPrefix(keywords.toArray(new String[0])));
            keywords = autoCompleteClient.list(keywordString);
        }

        if (keywords.isEmpty()) {
            return new Estimate(0);
        }

        if (keyword.foundIn(keywords)) {
            return new Estimate(score - keyword.indexIn(keywords));
        }

        return new Estimate(score - 36);
    }

}

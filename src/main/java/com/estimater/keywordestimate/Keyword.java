package com.estimater.keywordestimate;


import java.util.List;

import static org.apache.commons.lang3.StringUtils.substringAfter;

public class Keyword {
    private static final String SPACE = " ";
    private String keyword;

    public Keyword(String keyword) {
        this.keyword = keyword;
    }

    public String next(String prefix) {
        if (prefix.trim().equals(keyword)) {
            return keyword;
        }

        String remaining = substringAfter(keyword, prefix);
        if (remaining.startsWith(SPACE)) {
            return prefix + remaining.substring(0, 2);
        }
        return prefix + remaining.charAt(0);
    }


    public boolean foundIn(List<String> strings) {
        return strings.contains(keyword);
    }

    public int indexIn(List<String> keywords) {
        return keywords.indexOf(keyword);
    }

    public boolean sameAs(String keywordString) {
        return keyword.equals(keywordString);
    }
}

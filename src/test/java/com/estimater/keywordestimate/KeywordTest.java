package com.estimater.keywordestimate;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KeywordTest {

    @Test
    public void shouldGiveFirstCharacterIfPrefixIsEmpty() {
        Keyword keyword = new Keyword("samsung galaxy note 9");
        assertThat(keyword.next(""), is("s"));
    }

    @Test
    public void shouldGiveNextCharacterAfterPrefix() {
        Keyword keyword = new Keyword("samsung galaxy note 9");
        assertThat(keyword.next("sam"), is("sams"));
    }

    @Test
    public void shouldGiveNextTwoCharactersAfterPrefixIfSpace() {
        Keyword keyword = new Keyword("samsung galaxy note 9");
        assertThat(keyword.next("samsung"), is("samsung g"));
    }

    @Test
    public void shouldRepeatIfSameAsPrefix() {
        Keyword keyword = new Keyword("samsung galaxy note 9");
        assertThat(keyword.next("samsung galaxy note 9"), is("samsung galaxy note 9"));
    }

    @Test
    public void shouldRepeatIfSameAsPrefix1() {
        Keyword keyword = new Keyword("samsung");
        assertThat(keyword.next("samsung "), is("samsung"));
    }

    @Test
    public void shouldBeFound() {
        Keyword keyword = new Keyword("samsung galaxy note 9");
        assertThat(keyword.foundIn(Arrays.asList("samsung galaxy note 9", "samsung")), is(true));
    }

    @Test
    public void shouldNotBeFound() {
        Keyword keyword = new Keyword("samsung galaxy note 9");
        assertThat(keyword.foundIn(Arrays.asList("samsung galaxy note", "samsung")), is(false));
        assertThat(keyword.foundIn(Arrays.asList("samsung galaxy note 9 case", "samsung")), is(false));
        assertThat(keyword.foundIn(Arrays.asList("samsung galaxy note 9.1.0", "samsung")), is(false));

        assertThat(new Keyword("samsung").foundIn(Arrays.asList("samsung galaxy note 9.1.0", "samsung note 10")), is(false));
    }
}
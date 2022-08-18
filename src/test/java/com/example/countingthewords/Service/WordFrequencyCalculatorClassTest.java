package com.example.countingthewords.Service;

import com.example.countingthewords.Model.WordFrequency;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class WordFrequencyCalculatorClassTest {

    private static WordFrequencyCalculatorClass calculator;

    @BeforeAll
    public static void setup() {
        calculator = new WordFrequencyCalculatorClass();
        ArrayList<WordFrequency> words = new ArrayList<>();
    }

    @Test
    void getHighestWordTest() {

        String text = "The sun shines over the lake";
        int frequency = calculator.CalculateHighestFrequency(text);
        Assert.assertEquals(2,frequency);
    }

    @Test
    void getHighestWordTestEmptyString() {

        String text = "";
        int frequency = calculator.CalculateHighestFrequency(text);
        Assert.assertEquals(0,frequency);
    }

    @Test
    void getHighestWordTestOneWord() {

        String text = "lake";
        int frequency = calculator.CalculateHighestFrequency(text);
        Assert.assertEquals(1,frequency);
    }

    @Test
    void getHighestWordTestCaseSensitive() {

        String text = "the sun shines over the lake";
        int frequency = calculator.CalculateHighestFrequency(text);
        Assert.assertEquals(2,frequency);
    }

    @Test
    void getHighestWordTestWithSymbols() {

        String text = "The -sun .shines ,over ;the lake";
        int frequency = calculator.CalculateHighestFrequency(text);
        Assert.assertEquals(2,frequency);
    }

    @Test
    void getHighestWordTestHighFrequency() {

        String text = "The The The The The The The The The sun shines over the lake";
        int frequency = calculator.CalculateHighestFrequency(text);
        Assert.assertEquals(10,frequency);
    }

    @Test
    void getWordFreqencyTest() {

        String text = "The sun shines over the lake";
        int frequency = calculator.CalculateFrequencyForWord(text, "the");
        Assert.assertEquals(2,frequency);
    }

    @Test
    void getWordFrequencyTestEmptyString() {

        String text = "";
        int frequency = calculator.CalculateFrequencyForWord(text,"the");
        Assert.assertEquals(0,frequency);
    }

    @Test
    void getWordFrequencyTestOneWord() {

        String text = "lake";
        int frequency = calculator.CalculateFrequencyForWord(text,"lake");
        Assert.assertEquals(1,frequency);
    }

    @Test
    void getWordFrequencyTestCaseSensitive() {

        String text = "the sun shines over the lake";
        int frequency = calculator.CalculateFrequencyForWord(text, "The");
        Assert.assertEquals(2,frequency);
    }

    @Test
    void getWordFrequencyTestWithSymbols() {

        String text = "The -sun .shines ,over ;the lake";
        int frequency = calculator.CalculateFrequencyForWord(text,"the");
        Assert.assertEquals(2,frequency);
    }

    @Test
    void getWordFrequencyTestHighFrequency() {

        String text = "The The The The The The The The The sun shines over the lake";
        int frequency = calculator.CalculateFrequencyForWord(text,"the");
        Assert.assertEquals(10,frequency);
    }

    @Test
    void getWordFrequencyTestInvalidWord() {

        String text = "the sun shines over the lake";
        int frequency = calculator.CalculateFrequencyForWord(text, "Then");
        Assert.assertEquals(0,frequency);
    }

    @Test
    void getWordFrequencyTestEmptyWord() {

        String text = "the sun shines over the lake";
        int frequency = calculator.CalculateFrequencyForWord(text, "");
        Assert.assertEquals(0, frequency);
    }

    @Test
    void getNWordsTestTest() {

        String text = "The sun shines over the lake";
        WordFrequency[] words = calculator.CalculateMostFrequentNWords(text, 2);
        Assert.assertEquals(5, words.length);
        Assert.assertEquals("the", words[0].getWord());
        Assert.assertEquals(2, words[0].getFrequency());
        Assert.assertEquals("sun", words[words.length -1].getWord());
        Assert.assertEquals(1, words[words.length -1].getFrequency());
    }

    @Test
    void getNWordsTestEmptyString() {

        String text = "";
        WordFrequency[] words = calculator.CalculateMostFrequentNWords(text, 2);
        Assert.assertEquals(0, words.length);

    }

    @Test
    void getNWordsTestOneWord() {

        String text = "lake";
        WordFrequency[] words = calculator.CalculateMostFrequentNWords(text, 2);
        Assert.assertEquals(1, words.length);
        Assert.assertEquals("lake", words[0].getWord());
        Assert.assertEquals(1, words[0].getFrequency());
    }

    @Test
    void getNWordsTestCaseSensitive() {

        String text = "the sun shines over the lake";
        WordFrequency[] words = calculator.CalculateMostFrequentNWords(text, 2);
        Assert.assertEquals(5, words.length);
        Assert.assertEquals("the", words[0].getWord());
        Assert.assertEquals(2, words[0].getFrequency());
        Assert.assertEquals("sun", words[words.length -1].getWord());
        Assert.assertEquals(1, words[words.length -1].getFrequency());
    }

    @Test
    void getNWordsTestWithSymbols() {

        String text = "The -sun .shines ,over ;the lake";
        WordFrequency[] words = calculator.CalculateMostFrequentNWords(text, 2);
        Assert.assertEquals(5, words.length);
        Assert.assertEquals("the", words[0].getWord());
        Assert.assertEquals(2, words[0].getFrequency());
        Assert.assertEquals("sun", words[words.length -1].getWord());
        Assert.assertEquals(1, words[words.length -1].getFrequency());
    }

    @Test
    void getNWordsTestHighFrequency() {

        String text = "The The The The The The The The The sun shines over the lake";
        WordFrequency[] words = calculator.CalculateMostFrequentNWords(text, 100);
        Assert.assertEquals(5, words.length);
        Assert.assertEquals("the", words[0].getWord());
        Assert.assertEquals(10, words[0].getFrequency());
        Assert.assertEquals("sun", words[words.length -1].getWord());
        Assert.assertEquals(1, words[words.length -1].getFrequency());
    }

    @Test
    void getNWordsTestExcludes() {

        String text = "This word is a word word, this is great";
        WordFrequency[] words = calculator.CalculateMostFrequentNWords(text, 2);
        Assert.assertEquals(4, words.length);
        Assert.assertEquals("is", words[0].getWord());
        Assert.assertEquals(2, words[0].getFrequency());
        Assert.assertEquals("great", words[words.length -1].getWord());
        Assert.assertEquals(1, words[words.length -1].getFrequency());
    }

    @Test
    void getNWordsTestSorting() {

        String text = "This word is a word word, this is great";
        WordFrequency[] words = calculator.CalculateMostFrequentNWords(text, 3);
        Assert.assertEquals(5, words.length);
        Assert.assertEquals("word", words[0].getWord());
        Assert.assertEquals(3, words[0].getFrequency());
        Assert.assertEquals("is", words[1].getWord());
        Assert.assertEquals(2, words[1].getFrequency());
        Assert.assertEquals("this", words[2].getWord());
        Assert.assertEquals(2, words[2].getFrequency());
        Assert.assertEquals("a", words[3].getWord());
        Assert.assertEquals(1, words[3].getFrequency());
        Assert.assertEquals("great", words[4].getWord());
        Assert.assertEquals(1, words[4].getFrequency());
    }


}

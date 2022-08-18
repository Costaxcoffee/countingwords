package com.example.countingthewords.Model;

public class WordFrequencyClass implements WordFrequency {

    private final int frequency;

    private final String word;

    public WordFrequencyClass() {
        this.frequency = 0;
        this.word = "";
    }

    public WordFrequencyClass(int frequency, String word) {
        this.frequency = frequency;
        this.word = word;
    }


    @Override
    public int getFrequency() {
        return this.frequency;
    }

    @Override
    public String getWord() {
        return this.word;
    }
}

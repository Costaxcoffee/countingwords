package com.example.countingthewords.Service;

import com.example.countingthewords.Model.WordFrequency;

public interface WordFrequencyCalculator {
    int CalculateHighestFrequency(String text);

    int CalculateFrequencyForWord(String text, String word);

    WordFrequency[] CalculateMostFrequentNWords(String text, int n);
}

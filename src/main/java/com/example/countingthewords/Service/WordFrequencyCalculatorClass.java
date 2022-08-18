package com.example.countingthewords.Service;

import com.example.countingthewords.Model.WordFrequency;
import com.example.countingthewords.Model.WordFrequencyClass;

import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyCalculatorClass implements WordFrequencyCalculator {

    public WordFrequencyCalculatorClass() {
    }

    @Override
    public int CalculateHighestFrequency(String text) {
        Map<String, Integer> wordMap = this.generateList(text);
        Integer[] values = sortFrequencies(wordMap).toArray(new Integer[0]);
        return values.length > 0 ? Arrays.stream(values).findFirst().get() : 0;
    }

    @Override
    public int CalculateFrequencyForWord(String text, String word) {
        Map<String, Integer> wordMap = this.generateList(text);
        return wordMap.containsKey(word.toLowerCase()) ? wordMap.get(word.toLowerCase()).intValue() : 0;
    }

    @Override
    public WordFrequency[] CalculateMostFrequentNWords(String text, int n) {
        ArrayList<WordFrequency> wordFrequencies = new ArrayList<>();

        //filter everything lower than
        Map<String, Integer> wordMap = (this.generateList(text).entrySet().stream().filter(word -> word.getValue().intValue() <= n).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        ArrayList frequencies = sortFrequencies(wordMap);

        frequencies.forEach(frequency -> {
            ArrayList<String> sorted = new ArrayList<>(wordMap.entrySet().stream().filter(word -> word.getValue().equals(frequency)).map(Map.Entry::getKey).collect(Collectors.toSet()));


            Collections.sort(sorted);

            for (String sortedWord : sorted) {

                wordFrequencies.add(new WordFrequencyClass((Integer) frequency, sortedWord));
            }
        });


        return wordFrequencies.toArray(new WordFrequency[0]);
    }

    private Map<String, Integer> generateList(String text) {

        String lowercaseText = text.toLowerCase().replaceAll("[^a-zA-Z]", " ");
        HashMap<String, Integer> words = new HashMap<String, Integer>();
        Set<String> uniqueWords = new HashSet<String>(Arrays.asList(lowercaseText.split(" ")));

        for (String word : uniqueWords) {
            if (word.length() > 0) {
                int frequency = 0;

                for (String searchWords : lowercaseText.split(" ")) {
                    if (searchWords.equals(word)) {
                        frequency++;
                    }
                }

                words.put(word, frequency);
            }
        }
        return words;
    }

    private ArrayList<Integer> sortFrequencies(Map<String, Integer> map) {
        ArrayList<Integer> values = new ArrayList<>(map.values());
        ArrayList<Integer> uniqueValues = new ArrayList(new HashSet(values));
        Collections.sort(uniqueValues, Collections.reverseOrder());
        return uniqueValues;
    }
}

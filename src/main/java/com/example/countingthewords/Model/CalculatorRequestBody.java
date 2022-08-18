package com.example.countingthewords.Model;

import javax.validation.constraints.NotEmpty;

public class  CalculatorRequestBody {

    private String text;
    private int frequency;
    private String word;



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}

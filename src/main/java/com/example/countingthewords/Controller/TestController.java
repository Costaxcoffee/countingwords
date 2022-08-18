package com.example.countingthewords.Controller;

import com.example.countingthewords.Model.CalculatorRequestBody;
import com.example.countingthewords.Model.WordFrequency;
import com.example.countingthewords.Service.WordFrequencyCalculatorClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    WordFrequencyCalculatorClass calculator;

    public TestController() {
        calculator = new WordFrequencyCalculatorClass();
    }

    @PostMapping("/words/highestfreqency")
    public ResponseEntity<Object> CalculateHighestFrequency(@RequestBody CalculatorRequestBody request) {
        try {
            if (request.getText() == null) {
                return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
            }
            int frequency = this.calculator.CalculateHighestFrequency(request.getText());
            return new ResponseEntity<Object>(frequency, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>("Error calculating Highest frequency", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/words/frequncy")
    public ResponseEntity<Object> CalculateFrequencyForWord(@RequestBody CalculatorRequestBody request) {
        try {
            if (request.getText() == null || request.getWord() == null) {
                return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
            }

            int frequency = this.calculator.CalculateFrequencyForWord(request.getText(), request.getWord());
            return new ResponseEntity<Object>(frequency, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>("Error calculating frequency for word", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/words/frequentWords")
    public ResponseEntity<Object> CalculateMostFrequentNWords(@RequestBody CalculatorRequestBody request) {
        try {
            if (request.getText() == null) {
                return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
            }

            WordFrequency[] words = this.calculator.CalculateMostFrequentNWords(request.getText(), request.getFrequency());
            return new ResponseEntity<Object>(words, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>("Error calculating frequency for word", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

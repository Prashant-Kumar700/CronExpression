package com.example.cron.Model;

import java.util.ArrayList;
import java.util.List;

public class ParserParams {
    int step = 1;
    int start;
    int end;

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public List<Integer> getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(List<Integer> occurrences) {
        this.occurrences = occurrences;
    }

    List<Integer> occurrences = new ArrayList<>();

}

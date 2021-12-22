package System;

import java.util.ArrayList;

public class Step {

    private ArrayList<String> steps;
    private int sid;
    private static int stepCnt = 1;

    public Step() {
        steps = new ArrayList<String>();
        this.sid = stepCnt;
        stepCnt++;
    }

    public void addstep(String step) {
        steps.add(step);
    }

    public int getSid() {
        return sid;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }
}

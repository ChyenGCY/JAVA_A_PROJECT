package System;

import java.awt.Container;
import java.util.ArrayList;

public class Step {// store the steps

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

    public boolean removeLastStep() {
        if (steps.size() > 0) {
            this.steps.remove(steps.size() - 1);
            return true;
        }
        return false;
    }

    public void setSteps(ArrayList<String> sp) {
        this.steps = sp;
    }
}

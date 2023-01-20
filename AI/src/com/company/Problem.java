package com.company;

import java.util.Random;

public abstract class Problem{
    public Random r = new Random();
    public double[] upperBound;
    public double[] lowerBound;
    int numberOfDimensions;
    String name;

    public Problem(int numberOfDimensions, String name) {
        this.numberOfDimensions = numberOfDimensions;
        this.name = name;
        upperBound = new double[numberOfDimensions];
        lowerBound = new double[numberOfDimensions];
    }

    public Solution generateSolution() {

        double[] x = new double[numberOfDimensions];
        for (int i = 0; i < numberOfDimensions; i++) {
            x[i] = lowerBound[i] +  r.nextDouble() * (upperBound[i] - lowerBound[i]);
        }

        double fitness = evaluate(x);
        return new Solution(x, fitness);
    }

    abstract double evaluate(double[] x);

    public void setFeasible(Solution s) {
        for (int i = 0; i < numberOfDimensions; i++) {
            if (s.x[i] < lowerBound[i])
                s.x[i] = lowerBound[i];
            if (s.x[i] > upperBound[i])
                s.x[i] = upperBound[i];
        }
    }

    public void setFeasible(double[] x) {
        for (int i = 0; i < numberOfDimensions; i++) {
            if (x[i] < lowerBound[i])
                x[i] = lowerBound[i];
            if (x[i] > upperBound[i])
                x[i] = upperBound[i];
        }
    }
}

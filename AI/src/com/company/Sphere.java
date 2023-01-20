package com.company;

public class Sphere extends Problem {

    public Sphere(int numberOfDimensions) {
        super(numberOfDimensions, "Sphere");

        for (int i = 0; i < numberOfDimensions; i++) {
            lowerBound[i] = -5.12;
            upperBound[i] = 5.12;
        }
    }

    @Override
    double evaluate(double[] x) {

        double fitness = 0;
        for (int i = 0; i < numberOfDimensions; i++) {
            fitness += Math.pow(x[i],2);
        }
        return fitness;
    }
}
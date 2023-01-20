package com.company;

public class Solution{

    public double[] x;

    double fitness;


    public Solution(double[] x, double fitness) {
        this.x = x;
        this.fitness = fitness;
    }

    public Solution(Solution solution) {
        fitness = solution.fitness;
        x = new double[solution.x.length];
        System.arraycopy(solution.x, 0, x, 0, solution.x.length);
    }
}

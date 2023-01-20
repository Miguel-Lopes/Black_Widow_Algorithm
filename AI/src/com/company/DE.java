package com.company;
// Questions -> How to set up upper and lower limit properly and

import java.util.ArrayList;
import java.util.Random;

public class                                                                                                                  DE extends Algorithm {

    int NP = 30; // population size
    double F = 0.8;
    double CR = 0.9;
    double bestCurrentPopFitness;

    ArrayList<Solution> population = new ArrayList();
    Solution bestSolution;


    public Random random = new Random();
    int A;
    int B;
    int C;
    double r;
    int maxGen = 1000;
    int currentGen = 1;


    public Solution execute(Problem problem) {

        Solution solution = problem.generateSolution();
        bestSolution = new Solution(solution);
        population.add(solution);
        for (int i = 1; i < NP; i++) {
            population.add(problem.generateSolution());
            problem.setFeasible(solution);
            if (population.get(i).fitness < bestSolution.fitness) {
                bestSolution = new Solution(population.get(i));
            }
        }

        while (currentGen < maxGen) {

            for (int I = 0; I < NP; I++) {

                do {
                    A = random.nextInt(NP);
                } while (A == I);
                do {
                    B = random.nextInt(NP);
                } while (B == I && B != A);
                do {
                    C = random.nextInt(NP);
                } while (C == I && C != A && C != B);

                int R = random.nextInt(problem.numberOfDimensions);

                double[] y = new double[problem.numberOfDimensions];
                for (int i = 0; i < problem.numberOfDimensions; i++) {
                    r = random.nextDouble();
                    if (r <= CR) {
                        y[i] = population.get(A).x[i] + F * (population.get(B).x[i] - population.get(C).x[i]);
                    }
                    else y[i] = population.get(I).x[i];
                }
                problem.setFeasible(y);

                Solution offspring = new Solution(y, problem.evaluate(y));

                if (offspring.fitness < population.get(I).fitness) {
                    population.set(I,offspring);

                    if (offspring.fitness < bestSolution.fitness)
                        bestSolution = new Solution(offspring);
                }
            }
            currentGen++;
        }

        return bestSolution;
    }
}

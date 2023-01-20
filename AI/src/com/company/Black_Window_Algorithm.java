package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Black_Window_Algorithm extends Algorithm {

    public Random random = new Random();
    int NP = 50; // population size

    int female_widow;
    int male_widow;
    double alpha;
    ArrayList<Solution> pop1 = new ArrayList();
    ArrayList<Solution> pop2 = new ArrayList();
    ArrayList<Solution> pop3 = new ArrayList();
    int maxGen = 100;
    int currentGen = 1;

    Solution temp;
    double CR = 0.8;
    double PM = 0.4; //mutation rate

    public Solution execute(Problem problem) {
        int i;

        for (i = 0; i <= NP; i++) {
            Solution solution = problem.generateSolution();
            pop1.add(solution);
        }
        while (currentGen < maxGen) {
                        Collections.shuffle(pop1);


            for (i = 0; i < problem.numberOfDimensions; i++) {
                do {
                    female_widow = (int) (Math.random() * pop1.size());
                    male_widow = (int) (Math.random() * pop1.size());
                } while (female_widow == male_widow);

                alpha = random.nextDouble();
                double[] offspringX1 = new double[problem.numberOfDimensions];
                double[] offspringX2 = new double[problem.numberOfDimensions];
                for (int j = 0; j < problem.numberOfDimensions; j++) {
                    offspringX1[j] = alpha * pop1.get(female_widow).x[j] + (1 - alpha) * pop1.get(male_widow).x[j];
                    offspringX2[j] = alpha * pop1.get(male_widow).x[j] + (1 - alpha) * pop1.get(female_widow).x[j];
                }

                problem.setFeasible(offspringX1);
                problem.setFeasible(offspringX2);

                Solution offspring1 = new Solution(offspringX1, problem.evaluate(offspringX1));
                Solution offspring2 = new Solution(offspringX2, problem.evaluate(offspringX2));

                pop2.add(offspring1);
                pop2.add(offspring2);

                pop2.add(pop1.get(female_widow));

            }
            //Collections.sort(pop2, new Comparator())
            for (i = 0; i < pop2.size(); i++) {  //organizing spiders by fitness level
                for (int j = i + 1; j < pop2.size(); j++) {
                    if (pop2.get(i).fitness > pop2.get(j).fitness) {      //swap elements if not in order
                        temp = pop2.get(i);
                        pop2.get(i).fitness = pop2.get(j).fitness;
                        pop2.set(j, temp);
                    }
                }
            }

            for (i = 0; i < pop2.size() * CR; i++) {
                int n = random.nextInt(pop2.size());
                pop3.add(pop2.get(i));
                pop3.set(i, pop2.get(n));
            }

            int nm = (int) (pop1.size() * PM);
            for (i = 0; i < nm; i++) {
                for (int j = 0; j < problem.numberOfDimensions; j++) {
                    int mutated_pop = (int) (Math.random() * pop1.size());
                    double mutaded_x = Math.random();
                    pop1.get(mutated_pop).x[j] =  mutaded_x;
                }
            }

            for (i = 0; i < pop2.size(); i++) {
                pop1.add(pop2.get(i));
            }
            for (i = 0; i < pop3.size(); i++) {
                pop1.add(pop3.get(i));
            }

            pop2.clear();
            pop3.clear();

            for (i = 0; i < pop1.size(); i++) {  //organizing spiders by fitness level
                for (int j = 0; j < pop1.size(); j++) {
                    if (pop1.get(i).fitness > pop1.get(j).fitness) {      //swap elements if not in order
                        temp = pop1.get(i);
                        pop1.get(i).fitness = pop1.get(j).fitness;
                        pop1.set(j, temp);
                    }
                }
            }
            currentGen++;
        }
        return pop1.get(0);
    }
}


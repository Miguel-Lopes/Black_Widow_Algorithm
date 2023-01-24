package com.company;

public class Main {

    public static void main(String[] args) {

        Problem p = new Sphere(2);
        //Algorithm DE = new DE();
        Algorithm Black_widow = new Black_Window_Algorithm();
        Solution bestSolution = Black_widow.execute(p);
     //   Solution Spooder  = Black_Window_Algorithm.execute(p);

        System.out.println("The best solution is " + bestSolution.fitness);


    }


}
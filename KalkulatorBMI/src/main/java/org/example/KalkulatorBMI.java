package org.example;
import java.util.Scanner;
public class KalkulatorBMI {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Witaj w kalkulatorze BMI");
        System.out.print("Podaj masę ciała (kg): ");
        double weight = scanner.nextDouble();

        System.out.print("Podaj wzrost (m): ");
        double height = scanner.nextDouble();

        double bmi = calculateBMI(weight, height);

        System.out.println("Twoje BMI to: " + bmi);

        InterpretBMI.calculateBMI(bmi); //wywołanie metody z klasu InterpretBMI
    }
    public static double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }

}
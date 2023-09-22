package ex03;

import java.util.Scanner;

public class Program {
    public static long inverseMinGrades(long currentGrades) {
        long inverseGrades = 0;
        for(; currentGrades > 0; currentGrades /= 10) {
            inverseGrades *= 10;
            inverseGrades += currentGrades % 10;
        }
        return inverseGrades;
    }

    public static void printGraph(int countWeek, long minGrades) {
        int currentGrade;
        minGrades = inverseMinGrades(minGrades);
        for(int i = 1; countWeek > i; i++) {
            System.out.print("Week ");
            System.out.print(i);
            System.out.print(" ");
            currentGrade = (int)(minGrades % 10);
            minGrades /= 10;
            for(int j = 1; currentGrade >= j; j++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
    }

    public static int definitionMinGrade(long grades) {
        int currentGrade, minGrade = 10;
        while(grades > 0) {
            currentGrade = (int)(grades % 10);
            grades /= 10;
            if(minGrade > currentGrade) {
                minGrade = currentGrade;
            }
        }
        return minGrade;
    }

    public static int getGrades(Scanner input) {
        int grades = 0, grade;
        for(int i = 0; i < 5; i++) {
            if(input.hasNextInt()) {
                grade = input.nextInt();
                if(grade < 1 || grade > 9) {
                    printError(input);
                }
                grades *= 10;
                grades += grade;
            }
        }
        input.nextLine();
        return grades;
    }
    public static void printError(Scanner input) {
        System.err.println("IllegalArgument");
        input.close();
        System.exit(-1);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String inputWeek;
        int inputCountWeek, grades, countWeek = 1;
        long minGrades = 0L;
        while(!(inputWeek = input.next()).equals("42") && countWeek <= 18) {
            inputCountWeek = input.nextInt();
            if(!inputWeek.equals("Week") || inputCountWeek != countWeek) {
                printError(input);
            }
            grades = getGrades(input);
            minGrades *= 10;
            minGrades += definitionMinGrade(grades);
            countWeek++;
        }
        printGraph(countWeek, minGrades);
        input.close();
    }
}

package ex02;

import java.util.Scanner;

public class Program {
    public static int primeDefinition(int number) {
        int flagPrime = 0;
        if(number > 1) {
            for(int i = 2; (i * i) <= number; i++) {
                if (number % i == 0) {
                    flagPrime = 1;
                    break;
                }
            }
        } else {
            flagPrime = 2;
        }
        return flagPrime;
    }

    public static int sumNumbers(int numbers) {
        int sum = 0;
        while(numbers > 0) {
            sum += numbers % 10;
            numbers /= 10;
        }
        return sum;
    }

    public static void main(String[] arg) {
        Scanner input = new Scanner(System.in);
        int numbers = 0, resSum, resPrime, countCoffee = 0;
        while (numbers != 42) {
            if(input.hasNextInt()) {
                numbers = input.nextInt();
                resSum = sumNumbers(numbers);
                resPrime = primeDefinition(resSum);
                if (resPrime == 2) {
                    System.err.println("Illegal Argument");
                    input.close();
                    System.exit(-1);
                } else if (resPrime == 0) {
                    countCoffee++;
                }
            } else {
                break;
            }
        }
        System.out.println("Count of coffee-request – " + countCoffee);
        input.close();
    }
}

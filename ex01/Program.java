package ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] arg) {
        Scanner input = new Scanner(System.in);
        if (input.hasNextInt()) {
            int number = input.nextInt(), count = 1;
            boolean isPrime = true;
            if(number > 1) {
                for(int i = 2; (i * i) <= number; i++, count++) {
                    if (number % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
            } else {
                System.err.println("Illegal Argument");
                input.close();
                System.exit(-1);
            }
            System.out.println(isPrime + " " + count);
        }
        input.close();
    }
}

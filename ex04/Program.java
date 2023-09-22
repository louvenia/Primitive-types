package ex04;

import java.util.Scanner;

public class Program {
    public static void printHistogram(int[] countLetters, char[] letters) {
        System.out.println();
        int maxCount = countLetters[0];
        for(int i = 0; i < 10; i++) {
            if(countLetters[i] == maxCount ) {
                System.out.printf("%d%s",countLetters[i], "\t");
            }
        }
        System.out.println();
        for (int i = 10; i > 0; i--) {
            for (int j = 0; j < 10; j++) {
                int countDelimiter = countLetters[j] * 10 / maxCount;
                if (countDelimiter >= i) {
                    System.out.print("#\t");
                } else if (countDelimiter == i - 1) {
                    if (countLetters[j] != 0) {
                        System.out.printf("%d%s",countLetters[j], "\t");
                    }
                }
            }
            System.out.println();
        }
        for (int i = 0; i < 10; i++){
            System.out.printf("%c%s",letters[i], "\t");
        }
    }

    public static void mostFrequentRepetitions(int[] numberRep, int[] countLetters, char[] letters) {
        int index = 0;
        for (int i = 0; i < countLetters.length; i++) {
            int maxCount = 0;
            char maxLetter = ' ';
            for (int j = 0; j < numberRep.length; j++) {
                if (numberRep[j] > maxCount) {
                    maxCount = numberRep[j];
                    maxLetter = (char) j;
                    index = j;
                }
            }
            countLetters[i] = numberRep[index];
            letters[i] = maxLetter;
            numberRep[index] = 0;
        }
    }

    public static void printError(String str) {
        System.err.println(str);
        System.exit(-1);
    }

    public static void definitionRepetitions(int[] numberRep, char[] inputArr) {
        for (char c : inputArr) {
            if (numberRep[c] > 999) {
                printError("The maximum number of occurrences of characters is 999");
            }
            numberRep[c]++;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        input.close();
        char[] inputArr = str.toCharArray(), letters = new char[10];
        int[] numberRep = new int[65535], countLetters = new int[10];
        if(!str.isEmpty()) {
            definitionRepetitions(numberRep, inputArr);
            mostFrequentRepetitions(numberRep, countLetters, letters);
            printHistogram(countLetters, letters);
        } else {
            printError("Empty Line");
        }
    }
}

package ex00;

public class Program {
    public static void main(String[] args) {
        int number = 479598, res = 0;
        if(number < 0) {
            number *= -1;
        }
        res += number % 10;
        number /= 10;
        res += number % 10;
        number /= 10;
        res += number % 10;
        number /= 10;
        res += number % 10;
        number /= 10;
        res += number % 10;
        number /= 10;
        res += number % 10;
        System.out.println(res);
    }
}

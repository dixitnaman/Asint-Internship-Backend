
import java.util.Scanner;

public class Main {

    static void Method(String a) {
        System.out.println("Meow" + a);
    }

    static int add(int a, int b, int c) {
        System.out.println(a + b + c);
        return (a + b + c);
    }

    static int add(int a, int b) {
        System.out.println(a + b);
        return (a + b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // boolean val = true;

        // int value = sc.nextInt();
        // if (value < 10) {
        //     val = true;
        // } else {
        //     val = false;
        // }
        // if (val) {
        //     System.err.println("True");
        // } else {
        //     System.err.println("False");
        // }
        // double a = sc.nextDouble();
        // int b = 10;
        // int a = 2;
        // System.out.println((int)(Math.pow(a,b)));
        // String str1 = "Naman";
        // String str2="Dixit";

        // System.out.println(str1+" "+str2);
        // Method(str1);
        add(1, 2);
        add(1, 2, 3);
        int ans1 = add(1, 2);
        int ans2 = add(1, 2, 3);
        System.out.println(ans1+ans2);

    }
}

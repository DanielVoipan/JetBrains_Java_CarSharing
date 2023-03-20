import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int remainder = a % 2;
        int nextNumber = a + (2 - remainder);
        System.out.println(nextNumber);
    }
}

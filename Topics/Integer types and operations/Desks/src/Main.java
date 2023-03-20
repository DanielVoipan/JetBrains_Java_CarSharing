import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nrStudents1 = scanner.nextInt();
        int nrStudents2 = scanner.nextInt();
        int nrStudents3 = scanner.nextInt();

        int studentsPerDesk1 = nrStudents1 / 2;
        int studentsPerDesk2 = nrStudents2 / 2;
        int studentsPerDesk3 = nrStudents3 / 2;

        int remainingStudents1 = nrStudents1 % 2;
        int remainingStudents2 = nrStudents2 % 2;
        int remainingStudents3 = nrStudents3 % 2;

        int minimDesk = studentsPerDesk1 + studentsPerDesk2 + studentsPerDesk3 + 
            remainingStudents1 + remainingStudents2 + remainingStudents3;
        System.out.println(minimDesk);
    }
}

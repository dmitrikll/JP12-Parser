import java.util.Scanner;

public class ConsoleUI {
    public String getName() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the book title: ");
        return sc.nextLine();
    }
}

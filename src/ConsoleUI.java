import java.util.Scanner;

public class ConsoleUI {
    public String getBookName() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the book title: ");
        return sc.nextLine();
    }
}

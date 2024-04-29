public class Main {
    public static void main(String[] args) {

        ConsoleUI book = new ConsoleUI();
        BookAnalyzer bookAnalyzer = new BookAnalyzer();

        bookAnalyzer.getAnalyze(book.getName());
    }
}

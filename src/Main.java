public class Main {
    public static void main(String[] args) {

        ConsoleUI bookName = new ConsoleUI();
        BookAnalyzer bookAnalyzer = new BookAnalyzer();

        bookAnalyzer.getAnalyze(bookName.getBookName());
    }
}

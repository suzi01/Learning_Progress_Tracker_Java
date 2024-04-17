package tracker;

import java.util.Scanner;

public class InputScanner {

    private Scanner scanner;

    public InputScanner() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine().trim();
    }

}

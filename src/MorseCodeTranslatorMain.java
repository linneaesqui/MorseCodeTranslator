import java.util.Scanner;

public class MorseCodeTranslatorMain {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        MorseCodeTranslator myTranslator = new MorseCodeTranslator();
        boolean isRunning = true;

        System.out.println("Welcome to the Morse Code Translator!");
        while (isRunning) {
            System.out.println("What do you want to do?" +
                    "\n1. Translate English to Morse" +
                    "\n2. Translate Morse to English" +
                    "\n3. Write out the Morse alphabet" +
                    "\n4. Exit program");
            int choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Write the text you want to translate to Morse: (Only English letters!)");
                    System.out.println(myTranslator.englishToMorse(scan.nextLine()));
                    break;
                case 2:
                    System.out.println("Write the Morse code you want to translate to English:");
                    System.out.println(myTranslator.morseToEnglish(scan.nextLine()));
                    break;
                case 3:
                    System.out.println(myTranslator.printMorseAlphabet());
                    break;
                case 4:
                    System.out.println("Program closing down...");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid input, please pick a number between 1 and 4!");
            }
        }
    }
}
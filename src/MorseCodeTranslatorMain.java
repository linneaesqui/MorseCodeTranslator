import java.util.Scanner;

public class MorseCodeTranslatorMain {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        MorseCodeTranslator myTranslator = new MorseCodeTranslator();
        boolean isRunning = true;

        System.out.println("Welcome to the Morse Code Translator!");
        while (isRunning) {
            //IntelliJ ville byta ut långa konkateneringar mot ett textblock. Ett textblock behåller alla radbyten och
            //markeras med """ i början och slutet. För en längre text med många radbyten blir koden mer lättläslig med
            //testblock istället för konkateneringar.
            System.out.println("""
                    What do you want to do?
                    1. Translate English to Morse
                    2. Translate Morse to English
                    3. Print Morse alphabet
                    4. Exit program""");
            //Vi kastar ett NumberFormatException för att fånga upp om användaren skriver in nåt annat än siffror.
            //Ogiltiga siffror fångas upp i default.
            try {
                int choice = Integer.parseInt(scan.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Write the message you want to translate to Morse (only English letters):");
                        while (true) {
                            String input = scan.nextLine();
                            //Vi skriver ut ett felmeddelande för en tom string.
                            if (input.isEmpty()) {
                                System.out.println("Empty string, please try again!");
                            //Vi skriver ut ett felmeddelande om det inte finns några matchande tecken, pga att jag
                            //upplevde det förvirrande om man bara fick tillbaka frågetecken.
                            } else if (myTranslator.englishToMorse(input).matches("^\\?([ ?]*\\?)?$")) {
                                System.out.println("No matches found!");
                                break;
                            } else {
                                System.out.println("Your text was: \n" + input);
                                System.out.println("The translation is: \n" + myTranslator.englishToMorse(input));
                                break;
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Write the message you want to translate to English:");

                        while (true) {
                            String input = scan.nextLine();
                            if (myTranslator.morseToEnglish(input).isEmpty()) {
                                System.out.println("Empty string, please try again!");
                            } else if (myTranslator.morseToEnglish(input).matches("^\\?([ ?]*\\?)?$")) {
                                System.out.println("No matches found!");
                                break;
                            } else {
                                System.out.println("Your message was:\n" + input);
                                System.out.println("The translation is:\n" + myTranslator.morseToEnglish(input));
                                break;
                            }
                        }
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
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number!");
            } catch (Exception e) {
                System.out.println("Something went wrong, please try again!");
            }
        }
    }
}
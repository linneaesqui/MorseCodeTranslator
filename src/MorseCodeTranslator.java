//Saker som jag lagt till/tolkat:
//Jag vill översätta mellanslag korrekt åt båda hållen (ett mellanslag mellan bokstäver och tre mellan ord i morse-kod).
//Jag vill att specialtecken och tecken utanför det engelska alfabetet ska översättas med ett frågetecken,
//så att användaren lätt ska kunna se vilket tecken som inte gick att översätta.
//Ovannämnda lösning gör också att eventuella felinmatningar undviks, vilket ger programmet mer resiliens.
//Dock vill jag att inmatningar som endast ger frågetecken i översättningen ska ge ett felmeddelande om att "No matches found",
//detta för att undvika förvirring.
//Tomma rader ska ge ett felmeddelande om att raden är tom, användaren får fortsätta tills korrekt inmatning skett.
//Ogiltigt menyval ska ge ett felmeddelande om ogiltigt val, användaren får fortsätta tills korrekt inmatning skett.
//Jag vill ha en metod för att kunna skriva ut hela morse-alfabetet från menyn i min Main.
import java.util.HashMap;

public class MorseCodeTranslator {

    //Min första lösning var en HashMap med två Strings, men efter att ha gjort lite research läste jag att det är mer
    //effektivt att ha en Character som objekt, då det kostar mindre i prestanda. Jag valde att göra två HashMaps,
    //helt enkelt för att det var lättast.
    private static final HashMap <String, Character> morseToLetter = new HashMap<>();
    private static final HashMap <Character, String> letterToMorse = new HashMap<>();

    //För att slippa köra koden varje gång ett objekt skapas, bytte jag ut kontruktorn mot ett static block, vilket innebär
    //att koden bara körs en gång när klassen skapas, och lagras i klassen istället för i varje objekt. FÖr att detta ska
    //fungera måste HashMapsen också sättas till static.
    static {
        String [] morseCode = (".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- " +
                "-. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..").split(" ");
        char [] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (int i = 0; i < letters.length; i++) {
            morseToLetter.put(morseCode[i], letters[i]);
            letterToMorse.put(letters[i], morseCode[i]);
        }
    }

    public String morseToEnglish(String input) {
        //IntelliJ gillar inte att man konkatenerar en String i en loop, detta för att en String görs om
        //varje gång man vill ändra den. Det är egentligen ingen skillnad på += och concat(), metoderna gör detsamma.
        //Förslaget var StringBuilder, som istället byggs på, och på så sätt sparar prestanda.
        StringBuilder result = new StringBuilder();
        //Jag behövde ett sätt att räkna de tomma platserna i min Array, detta för att på ett korrekt sätt kunna
        //behandla mellanslag i mitt program.
        int spaceCount = 0;
        //Vi trimmar bort alla mellanslag före och efter eventuella tecken för att kunna kontrollera om input är tom.
        input = input.trim();

        if (input.isEmpty()) {
            return input;
        }
        String [] letters = input.split(" ");
        for (String letter : letters) {
            if (letter.isEmpty()) {
                //Om vi har en tom plats i Arrayen, ökar spaceCount med 1.
                spaceCount++;
                //Två tomma platser betyder att det var 3 mellanslag i input, och vi lägger till ett mellanslag
                //till vår StringBuilder.
                if (spaceCount == 2) {
                    result.append(" ");
                //Continue betyder att vi i övrigt inte fortsätter med översättningen för de tomma platserna
                //(då detta skulle ge ett frågetecken i utskriften).
                } continue;
            //Så fort vi stöter på ett tecken igen, sätts spaceCount till 0, och vi börjar om räkningen.
            } else {
                spaceCount = 0;
            }
            //Jag hittade metoden getOrDefault, som nu returnerar ett frågetecken om vi inte hittar bokstaven i vår HashMap.
            result.append(morseToLetter.getOrDefault(letter, '?'));
        } return result.toString().trim();
    }

    public String englishToMorse(String input) {
        StringBuilder result = new StringBuilder();

        input = input.trim().toUpperCase();

        if (input.isEmpty()) {
            return input;
        }
        String [] words = input.split(" ");
        for (String word : words) {
            //Här behöver vi inte räkna mellanslag, om det blivit ett extra mellanslag i input kan vi räkna med att det
            //var ett misstag, och vi hoppar helt enkelt över tomma platser i Arrayen.
            if (word.isEmpty()) {
                continue;
            }
            //Vi delar upp orden i bokstäver för att kunna använda vår HashMap.
            char [] letters = word.toCharArray();
            //I utskriften vill i lägga till ett mellanslag mellan varje bokstav...
            for (char letter : letters) {
                result.append(letterToMorse.getOrDefault(letter, "?")).append(" ");
            //...och ytterligare två mellanslag mellan varje ord...
            } result.append("  ");
        }
        //...och slutligen översätter vi vår StringBuilder till en String och trimmar bort överflödiga mellanslag.
        return result.toString().trim();
    }

    public String printMorseAlphabet() {
        StringBuilder result = new StringBuilder();
        for (char key : letterToMorse.keySet()) {
            result.append(key).append(" = ").append(letterToMorse.get(key)).append("\n");
        } return result.toString().trim();
    }
}

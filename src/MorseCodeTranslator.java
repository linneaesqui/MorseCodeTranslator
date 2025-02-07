import java.util.HashMap;

public class MorseCodeTranslator {

    private static final HashMap <String, String> morseToLetter = new HashMap<>();
    private static final HashMap <String, String> letterToMorse = new HashMap<>();

    public MorseCodeTranslator() {
        String [] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        String [] morseCode = (".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- " +
                "-. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..").split(" ");
        for (int i = 0; i < letters.length; i++) {
            morseToLetter.put(morseCode[i], letters[i]);
            letterToMorse.put(letters[i], morseCode[i]);
        }
    }

    public String morseToEnglish(String input) {
        //IntelliJ gillar inte att man konkatenerar en String i en loop, detta för att en String görs om
        //varje gång man vill ändra den. Förslaget var StringBuilder, som istället byggs på, och på så sätt sparar
        //prestanda.
        StringBuilder result = new StringBuilder();
        //Jag behövde ett sätt att räkna de tomma platserna i min Array, detta för att på ett korrekt sätt kunna
        //behandla mellanslag i mitt program.
        int spaceCount = 0;
        input = input.trim();
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
            result.append(morseToLetter.getOrDefault(letter, "?"));
        } return result.toString().trim();
    }

    public String englishToMorse(String input) {
        StringBuilder result = new StringBuilder();
        input = input.trim().toUpperCase();
        String [] words = input.split(" ");
        for (String word : words) {
            //Här behöver vi inte räkna mellanslag, om det blivit ett extra mellanslag i input kan vi räkna med att det
            //var ett misstag, och vi hoppar helt enkelt över tomma platser i Arrayen.
            if (word.isEmpty()) {
                continue;
            }
            //Vi delar upp orden i bokstäver för att kunna använda vår HashMap.
            String [] letters = word.split("");
            //I utskriften vill i lägga till ett mellanslag mellan varje bokstav...
            for (String letter : letters) {
                result.append(letterToMorse.getOrDefault(letter, "?")).append(" ");
            //...och ytterligare två mellanslag mellan varje ord...
            } result.append("  ");
        }
        //...och slutligen trimmar vi bort överflödiga mellanslag.
        return result.toString().trim();
    }

    public String printMorseAlphabet() {
        StringBuilder result = new StringBuilder();
        for (String key : letterToMorse.keySet()) {
            result.append(key).append(" = ").append(letterToMorse.get(key)).append("\n");
        } return result.toString().trim();
    }
}

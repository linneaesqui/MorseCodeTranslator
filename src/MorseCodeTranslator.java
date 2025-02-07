import java.util.HashMap;

public class MorseCodeTranslator {

    private static final HashMap <String, String> morseToLetter = new HashMap<>();
    private static final HashMap <String, String> letterToMorse = new HashMap<>();
    private String [] letters;
    private String [] morseCode;

    public MorseCodeTranslator() {
        letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        morseCode = (".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- " +
                "-. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..").split(" ");
        for (int i = 0; i < letters.length; i++) {
            morseToLetter.put(morseCode[i], letters[i]);
            letterToMorse.put(letters[i], morseCode[i]);
        }
    }

    public String morseToEnglish(String input) {
        String result = "";
        int spaceCount = 0;
        input = input.trim();
        String [] letters = input.split(" ");
        for (String letter : letters) {
            if (letter.isEmpty()) {
                spaceCount++;
                if (spaceCount == 2) {
                    result += " ";
                } continue;
            } else {
                spaceCount = 0;
            }
            result += morseToLetter.getOrDefault(letter, "?");
        } return result.trim();
    }

    public String englishToMorse(String input) {
        String result = "";
        input = input.trim().toUpperCase();
        String [] words = input.split(" ");
        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            String [] letters = word.split("");
            for (String letter : letters) {
                result += letterToMorse.getOrDefault(letter, "?") + " ";
            } result += "  ";
        }
        return result.trim();
    }

    public String printMorseAlphabet() {
        String result = "";
        for (String key : letterToMorse.keySet()) {
            result += key + " = " + letterToMorse.get(key) + "\n";
        } return result.trim();
    }
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MorseCodeTranslatorTest {

    @Test
    void testMorseToEnglish() {
        MorseCodeTranslator myTranslator = new MorseCodeTranslator();
        assertEquals("S", myTranslator.morseToEnglish("..."));
    }

    @Test
    void testEnglishToMorse() {
        MorseCodeTranslator myTranslator = new MorseCodeTranslator();
        assertEquals("...", myTranslator.englishToMorse("S"));
    }

    @Test
    void testSpaceBetweenMorse() {
        MorseCodeTranslator myTranslator = new MorseCodeTranslator();
        assertEquals("HEJ HEJ", myTranslator.morseToEnglish(".... . .---   .... . .---"));
    }

    @Test
    void testSpaceBetweenWords() {
        MorseCodeTranslator myTranslator = new MorseCodeTranslator();
        assertEquals(".... . .---   .... . .---", myTranslator.englishToMorse("hej hej"));
    }

    @Test
    void testSpecialChar() {
        MorseCodeTranslator myTranslator = new MorseCodeTranslator();
        assertEquals("? ? ?", myTranslator.englishToMorse("!!!"));
    }

    @Test
    void testNonExistingMorseCode() {
        MorseCodeTranslator myTranslator = new MorseCodeTranslator();
        assertEquals("???", myTranslator.morseToEnglish("--... --... --..."));
    }

    @Test
    void testPrintMorseAlphabet() {
        MorseCodeTranslator myTranslator = new MorseCodeTranslator();
        assertEquals("A = .-\nB = -...\nC = -.-.\nD = -..\nE = .\nF = ..-.\nG = --.\nH = ....\nI = .." +
                "\nJ = .---\nK = -.-\nL = .-..\nM = --\nN = -.\nO = ---\nP = .--.\nQ = --.-\nR = .-.\nS = ..." +
                "\nT = -\nU = ..-\nV = ...-\nW = .--\nX = -..-\nY = -.--\nZ = --..", myTranslator.printMorseAlphabet());
    }

    @Test
    void testEmptyStringMTE() {
        MorseCodeTranslator myTranslator = new MorseCodeTranslator();
        assertEquals("", myTranslator.morseToEnglish("      "));
    }

    @Test
    void testEmptyStringETM() {
        MorseCodeTranslator myTranslator = new MorseCodeTranslator();
        assertEquals("", myTranslator.englishToMorse("      "));
    }
}

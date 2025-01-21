import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void testParseVariables() {
        String input = "a 5 b 10";
        Map<String, Double> result = Main.parseVariables(input);
        assertEquals(5.0, result.get("a"));
        assertEquals(10.0, result.get("b"));
    }

    @Test
    void testEncryptDecrypt() {
        String original = "Hello, World!";
        SecretKey key = Main.generateKey();
        String encrypted = Main.encrypt(original, key);
        String decrypted = Main.decrypt(encrypted, key);
        assertEquals(original, decrypted);
    }

    @Test
    void testEvaluateArithmeticExpressions() {
        String input = "2+5*3";
        String evaluated = Main.evaluateArithmeticExpressions(input);
        assertEquals("17.0", evaluated.trim());
    }
    @Test
    void testReadFile() throws Exception {
        String filePath = "test.txt";
        Files.write(Paths.get(filePath), "Hello World!".getBytes());
        String content = Main.readFile(filePath);
        assertEquals("Hello World!" + System.lineSeparator(), content);
        new File(filePath).delete(); // Удаляем тестовый файл после проверки
    }

    @Test
    void testWriteFile() throws Exception {
        String filePath = "output.txt";
        String content = "This is a test.";
        Main.writeFile(filePath, content);
        String writtenContent = new String(Files.readAllBytes(Paths.get(filePath)));
        assertEquals(content, writtenContent);
        new File(filePath).delete();
    }

    @Test
    void testGetOutputFileName() {
        String originalPath = "document.txt";
        String newExtension = ".enc";
        String expectedOutput = "document.enc";
        assertEquals(expectedOutput, Main.getOutputFileName(originalPath, newExtension));
    }

}

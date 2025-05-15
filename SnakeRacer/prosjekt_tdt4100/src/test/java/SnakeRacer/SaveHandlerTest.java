package SnakeRacer;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaveHandlerTest {
    
    private static String TEST_FILENAME = "test-save-file";
    private SaveHandler saveHandler;

    @BeforeEach
    void setUp() {
        saveHandler = new SaveHandler(TEST_FILENAME);
    }

    @Test
    public void testSave() throws IOException {

        String name = "test-player";
        int score = 111;

        saveHandler.save(name, score);

        // Sjekker at filen eksisterer etter at den er opprettet.
        Path path = Paths.get(TEST_FILENAME);
        assertTrue(Files.exists(path), "Filen finnes ikke.");

        String content = Files.readString(path).trim();
        String expectedContent = name + " fikk " + score + " poeng.";

        assertEquals(expectedContent, content, "Innholdet matcher ikke.");
    }

    @Test
    public void testReadScores() throws IOException {

        List <String> testContent = List.of(
            "Spiller x fikk 5 poeng.",
            "Spiller y fikk 8 poeng."
        );

        Path path = Paths.get(TEST_FILENAME);
        Files.write(path, testContent);

        List <String> actualContent = saveHandler.readScores();

        assertEquals(testContent, actualContent, "Filens innhold matcher ikke det faktiske innholdet.");
    }

    @AfterEach
    void cleanUp() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILENAME));
    }
}

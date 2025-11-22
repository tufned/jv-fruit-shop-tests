package core.basesyntax.service.file.impl;

import core.basesyntax.service.file.ShopFileWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShopFileWriterImplTest {
    private static final String CONTENT = "b,banana,20";
    private final ShopFileWriter shopFileWriter;
    @TempDir
    Path tempDir;

    public ShopFileWriterImplTest() {
        shopFileWriter = new ShopFileWriterImpl();
    }

    @Test
    void write_writeSuccessfully_ok() throws IOException {
        Path path = tempDir.resolve("test.csv");

        shopFileWriter.write(CONTENT, path.toString());

        List<String> actualLines = Files.readAllLines(path);
        assertEquals(CONTENT, actualLines.get(0));
    }
}
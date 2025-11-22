package core.basesyntax.service.impl;

import core.basesyntax.db.ShopStorage;
import core.basesyntax.service.ReportGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportGeneratorImplTest {
    private static final String VALID_REPORT = "fruit,quantity\nbanana,100\napple,13\n";
    private ReportGenerator reportGeneratorImpl;

    @BeforeEach
    void beforeEach() {
        ShopStorage storage = new ShopStorage();
        storage.update("banana", 100);
        storage.update("apple", 13);
        reportGeneratorImpl = new ReportGeneratorImpl(storage);
    }

    @Test
    void getReport_reportGenerated_ok() {
        String expected = reportGeneratorImpl.getReport();
        assertEquals(VALID_REPORT, expected);
    }
}
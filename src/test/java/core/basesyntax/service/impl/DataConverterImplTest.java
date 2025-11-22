package core.basesyntax.service.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataConverterImplTest {
    private static DataConverter dataConverterImpl;
    private final String invalidLine = "b,banana";
    private final String validLine = "b,banana,20";

    public DataConverterImplTest() {
        dataConverterImpl = new DataConverterImpl();
    }

    @Test
    void convertToTransactions_reportsIsNull_notOk() {
        assertThrows(IllegalArgumentException.class, () -> dataConverterImpl.convertToTransactions(null));
    }

    @Test
    void convertToTransactions_reportNotValid_notOk() {
        assertThrows(IllegalArgumentException.class, () -> dataConverterImpl.convertToTransactions(List.of(invalidLine)));
    }

    @Test
    void convertToTransactions_fruitTransactionCreated_ok() {
        String[] parts = validLine.split(",");
        FruitTransaction expectedTransaction = FruitTransaction.of(FruitTransaction.Operation.fromCode(parts[0]), parts[1],Integer.parseInt(parts[2]));
        List<FruitTransaction> actual = dataConverterImpl.convertToTransactions(List.of(validLine));
        assertEquals(List.of(expectedTransaction), actual);
    }
}
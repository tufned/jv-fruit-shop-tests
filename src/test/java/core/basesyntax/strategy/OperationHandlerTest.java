package core.basesyntax.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.ShopStorage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.impl.BalanceOperation;
import core.basesyntax.strategy.impl.PurchaseOperation;
import core.basesyntax.strategy.impl.ReturnOperation;
import core.basesyntax.strategy.impl.SupplyOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperationHandlerTest {
    private FruitTransaction transaction;
    private final ShopStorage storage;
    private final BalanceOperation balanceOperation;
    private final PurchaseOperation purchaseOperation;
    private final ReturnOperation returnOperation;
    private final SupplyOperation supplyOperation;

    public OperationHandlerTest() {
        storage = new ShopStorage();
        balanceOperation = new BalanceOperation();
        purchaseOperation = new PurchaseOperation();
        returnOperation = new ReturnOperation();
        supplyOperation = new SupplyOperation();
    }

    @BeforeEach
    void beforeEach() {
        storage.getStorage().clear();
        storage.add("apple", 30);
        transaction = FruitTransaction.of(FruitTransaction.Operation.BALANCE, "apple", 20);
    }

    @Test
    void balanceOperation_addOrResetFruits_ok() {
        balanceOperation.handle(transaction, storage);
        assertEquals(1, storage.getStorage().size());
        assertEquals(transaction.getQuantity(), storage.get(transaction.getFruit()));

        transaction = FruitTransaction.of(FruitTransaction.Operation.BALANCE, "banana", 5);
        balanceOperation.handle(transaction, storage);
        assertEquals(2, storage.getStorage().size());
        assertEquals(transaction.getQuantity(), storage.get(transaction.getFruit()));
    }

    @Test
    void purchaseOperation_extractFruitQuantity_ok() {
        purchaseOperation.handle(transaction, storage);
        assertEquals(1, storage.getStorage().size());
        assertEquals(10, storage.get(transaction.getFruit()));
    }

    @Test
    void purchaseOperation_quantityBelowZero_notOk() {
        storage.add("apple", 10);
        assertThrows(RuntimeException.class,
                () -> purchaseOperation.handle(transaction, storage));
    }

    @Test
    void returnOperation_addToFruitQuantity_ok() {
        returnOperation.handle(transaction, storage);
        assertEquals(1, storage.getStorage().size());
        assertEquals(50, storage.get(transaction.getFruit()));
    }

    @Test
    void supplyOperation_addToFruitQuantity_ok() {
        supplyOperation.handle(transaction, storage);
        assertEquals(1, storage.getStorage().size());
        assertEquals(50, storage.get(transaction.getFruit()));
    }
}

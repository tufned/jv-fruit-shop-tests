package core.basesyntax.service.impl;

import core.basesyntax.db.ShopStorage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.OperationStrategy;
import core.basesyntax.strategy.OperationHandler;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationStrategyImplTest {
    private final OperationStrategy operationStrategy;

    public OperationStrategyImplTest() {
        Map<FruitTransaction.Operation, OperationHandler> operationHandlers = new HashMap<>();
        operationHandlers.put(FruitTransaction.Operation.BALANCE, new MockedHandler());

        operationStrategy = new OperationStrategyImpl(operationHandlers);
    }

    @Test
    void get_getStrategy_ok() {
        OperationHandler actual = operationStrategy.get(FruitTransaction.Operation.BALANCE);
        assertEquals(MockedHandler.class, actual.getClass());
    }

    static class MockedHandler implements OperationHandler {
        @Override
        public void handle(FruitTransaction transaction, ShopStorage storage) {
            return;
        }
    }
}
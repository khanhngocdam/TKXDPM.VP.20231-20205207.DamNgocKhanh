package test;

import controllers.PlaceOrderController;
import models.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateShippingFeeTest {
    private PlaceOrderController placeOrderController;
    @BeforeEach
    void setUp() throws Exception {
        placeOrderController = new PlaceOrderController();
    }

    @ParameterizedTest
    @CsvSource({
        
    })

    public void test(Order order, double shippingFeeExpected) {
        double shippingFee  = placeOrderController.calculateShippingFee(order);
        assertEquals(shippingFeeExpected, shippingFee );
    }
}

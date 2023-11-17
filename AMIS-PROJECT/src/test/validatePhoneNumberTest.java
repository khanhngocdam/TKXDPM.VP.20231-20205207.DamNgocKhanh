package test;

import controllers.PlaceOrderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class validatePhoneNumberTest {
    private PlaceOrderController placeOrderController;
    @BeforeEach
    void setUp() throws Exception {
        placeOrderController = new PlaceOrderController();
    }

    @ParameterizedTest
    @CsvSource({
            "0324433, true",
            "5207Khanh, false",
            "   , false",
            "0347988608, true"
    })

    public void test(String phoneNumber, boolean expected) {
        boolean isValid = placeOrderController.validatePhoneNumber(phoneNumber);
        assertEquals(expected, isValid);
    }
}

package test;

import controllers.PlaceOrderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidateAddressPlaceRushOrderTest {
    private PlaceOrderController placeOrderController;
    @BeforeEach
    void setUp() throws Exception {
        placeOrderController = new PlaceOrderController();
    }
    @ParameterizedTest
    @CsvSource({
            "Hà Nội, 74 Gốc Đề - Minh Khai, true",
            "Hưng Yên, Phụng Công - Văn Giang, false",
    })

    public void test(String province, String address, boolean expected) {
        boolean isValid = placeOrderController.validateAddressPlaceRushOrder(province, address);
        assertEquals(expected, isValid);
    }
}

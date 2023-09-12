import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(java.util.Optional.of(new Product("1", "Apfel"))),OrderStatus.PROCESSING);//
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //THEN
        assertThrows(RuntimeException.class,

                //WHEN
                ()->shopService.addOrder(productsIds));

        /////
        //WHEN
        // Order actual = shopService.addOrder(productsIds);
        //THEN
        //assertNull(actual);
    }
    @Test
    void printAllOrdersForaStatus_PROCESSING(){
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");
        Order order1 = shopService.addOrder(productsIds);

        //WHEN
         List<Order> actual = shopService.allOrdersForSpecificStatus(OrderStatus.PROCESSING);

        //THEN
        List<Order> expected =shopService.getOrderRepo().getOrders().stream().filter(order -> order.orderstatus().equals(OrderStatus.PROCESSING)).toList();
        assertEquals(expected,actual);
    }
}

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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
        Order expected = new Order("-1", List.of(java.util.Optional.of(new Product("1", "Apfel"))),OrderStatus.PROCESSING, LocalDateTime.now().withSecond(0).withNano(0));//||
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

    @Test
    void updateTheStatusOfAnOrderFrom_Processing_To_InDelivery(){
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");
        Order order1 = shopService.addOrder(productsIds);
        //WHEN
        OrderStatus actual = shopService.updateOrder(order1.id(), OrderStatus.IN_DELIVERY).orderstatus();
        //THEN
        OrderStatus expected = OrderStatus.IN_DELIVERY;
        assertEquals(expected,actual);
    }

    @Test
    void testValidityOfTheOrderTime_ifItMatches(){
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");
        Order order1 = shopService.addOrder(productsIds);
        //WHEN
        LocalDateTime actual = shopService.addOrder(productsIds).orderTime();
        //THEN
        LocalDateTime expected = LocalDateTime.now().withSecond(0).withNano(0);
        // assertEquals(expected,actual);
        assertTrue(expected.isEqual(actual));

    }
}

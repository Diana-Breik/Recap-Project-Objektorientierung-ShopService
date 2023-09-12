import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public OrderRepo getOrderRepo() {
        return orderRepo;
    }

    public Order addOrder(List<String> productIds) {
        List<Optional<Product>> products = new ArrayList<>();//
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);//
            if (productToOrder.isEmpty()) {//
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                return null;
            }
            products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products,OrderStatus.PROCESSING);
        return orderRepo.addOrder(newOrder);
    }

    //Schreibt in dem ShopService eine Methode, die alle Bestellungen mit einem bestimmten Bestellstatus (Parameter) in einer Liste zurückgibt. Nutzt dafür Streams.
    public List<Order> allOrdersForSpecificStatus(OrderStatus orderStatus){

        List<Order> collect = orderRepo.getOrders()
                .stream()
                .filter(order -> order.orderstatus().equals(orderStatus))
                .toList();

        return collect;

    }
}

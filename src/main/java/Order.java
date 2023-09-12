import lombok.With;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public record Order(
        String id,
        List<Optional<Product>> products,//
        @With
        OrderStatus orderstatus,
        LocalDateTime orderTime

) {

}

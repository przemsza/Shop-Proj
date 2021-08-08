package przemsza.com.github.shopproj.model.order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import przemsza.com.github.shopproj.model.item.Item;

@Component
@SessionScope
public class ClientOrder {

    private Order order;

    public ClientOrder() {
        clear();
    }

    public Order getOrder() {
        return order;
    }

    public void add(Item item) {
        order.getOrderList().add(item);
    }

    public void clear() {
        order = new Order();
        order.setStatus(OrderStatus.NEW);
    }
}
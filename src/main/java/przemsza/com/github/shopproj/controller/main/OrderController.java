package przemsza.com.github.shopproj.controller.main;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import przemsza.com.github.shopproj.model.item.Item;
import przemsza.com.github.shopproj.model.item.ItemRepository;
import przemsza.com.github.shopproj.model.order.ClientOrder;
import przemsza.com.github.shopproj.model.order.OrderRepository;

import javax.persistence.criteria.Order;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
public class    OrderController {

    private ItemRepository itemRepository;
    private ClientOrder clientOrder;

    @Autowired
    public OrderController(ItemRepository itemRepository, ClientOrder clientOrder) {
        this.itemRepository = itemRepository;
        this.clientOrder = clientOrder;
    }

    @GetMapping("/add/{id}")
    public String addToOrder(@PathVariable Long id ){
        Optional<Item> byId = itemRepository.findById(id);
        byId.ifPresent(x-> clientOrder.add(byId.get()));
        return "redirect:/menu";
    }

    @GetMapping("/minus/{id}")
    public String removerFromOrder(@PathVariable Long id){
        var orderList = clientOrder.getOrder().getOrderList();
        var item = orderList.stream().filter(x -> x.getId().equals(id)).findFirst();
        item.ifPresent(x-> orderList.remove(item.get()));
        return "redirect:/order";
    }

    @GetMapping("/order")
    public String showOrder(Model model){
      model.addAttribute("orders", clientOrder.getOrder().getOrderList());
        BigDecimal bigDecimal = getPrice();

        model.addAttribute("price", bigDecimal.doubleValue());
      return "order";
    }

    public BigDecimal getPrice() {
        BigDecimal bigDecimal = (clientOrder.getOrder().getOrderList())
                  .stream()
                  .map(x-> BigDecimal.valueOf(x.getPrice()))
                  .reduce(BigDecimal.ZERO, BigDecimal::add);
        return bigDecimal;
    }
}

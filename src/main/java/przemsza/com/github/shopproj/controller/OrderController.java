package przemsza.com.github.shopproj.controller;

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
import java.util.Optional;

@Controller
@RequestMapping("")
public class OrderController {

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
        return "redirect:/";
    }

    @GetMapping("/order")
    public String showOrder(Model model){
        model.addAttribute("orders", clientOrder.getOrder().getOrderList());
        return "order";
    }
}

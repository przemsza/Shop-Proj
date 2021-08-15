package przemsza.com.github.shopproj.controller.panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import przemsza.com.github.shopproj.model.order.Order;
import przemsza.com.github.shopproj.model.order.OrderRepository;

import java.util.Optional;

@Controller
@RequestMapping("/panel/order")
public class AdminEditOrderController {

    private OrderRepository orderRepository;

    @Autowired
    public AdminEditOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/{id}")
    public String editOrder(@PathVariable("id") Long id, Model model){
        Optional<Order> byId = orderRepository.findById(id);
        byId.ifPresent(x->model.addAttribute("order",byId.get()));
        return "/panel/edit-order";
    }

}

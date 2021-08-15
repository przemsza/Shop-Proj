package przemsza.com.github.shopproj.controller.panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import przemsza.com.github.shopproj.model.order.Order;
import przemsza.com.github.shopproj.model.order.OrderRepository;
import przemsza.com.github.shopproj.model.order.OrderStatus;

import java.util.List;

@Controller
@RequestMapping("/panel")
public class AdminOrdersController {

    private OrderRepository orderRepository;

    @Autowired
    public AdminOrdersController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    public String mainSite(Model model){
        List<Order> all = orderRepository.findAll();
        model.addAttribute("ordersList", all);
        return "/panel/panel-orders";
    }

    @GetMapping("/orders/status/{name}")
    public String sortStatus(@PathVariable("name") OrderStatus orderStatus, Model model){
        System.out.println(orderStatus);
        List<Order> byStatusList = orderRepository.findAllByStatus(orderStatus);
        model.addAttribute("ordersList",byStatusList);
        return "/panel/panel-orders";
    }

}

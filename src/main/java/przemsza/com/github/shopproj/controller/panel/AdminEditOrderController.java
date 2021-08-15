package przemsza.com.github.shopproj.controller.panel;

import org.apache.commons.mail.EmailException;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import przemsza.com.github.shopproj.controller.main.OrderController;
import przemsza.com.github.shopproj.model.mail.Mail;
import przemsza.com.github.shopproj.model.order.Order;
import przemsza.com.github.shopproj.model.order.OrderRepository;
import przemsza.com.github.shopproj.model.time.DeliveryTime;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/panel/order")
public class AdminEditOrderController {

    private OrderRepository orderRepository;
    private OrderController orderController;
    private DeliveryTime deliveryTime;
    private Mail mail;

    @Autowired
    public AdminEditOrderController(OrderRepository orderRepository, Mail mail, OrderController orderController, DeliveryTime deliveryTime) {
        this.orderRepository = orderRepository;
        this.mail = mail;
        this.orderController = orderController;
        this.deliveryTime = deliveryTime;
    }

    @GetMapping("/{id}")
    public String editOrder(@PathVariable("id") Long id, Model model){
        Optional<Order> byId = orderRepository.findById(id);
        byId.ifPresent(x-> {
                    model.addAttribute("order", byId.get());
                    model.addAttribute("times",deliveryTime.getTimes());
                }
        );
        return "/panel/edit-order";
    }

    @PostMapping("/{id}/status")
    public String post(@ModelAttribute("ti") String time, @PathVariable("id") Long id) throws EmailException {
        String result = deliveryTime.getTimes().get(Integer.valueOf(time));
        Order order = orderRepository.findById(id).get();
        BigDecimal price = getPrice(order);
        mail.sendEmail(order, price.doubleValue(),result);
        return "redirect:/panel/orders";
    }

    @After("")
    public void sen(){

    }

    public BigDecimal getPrice(Order order) {
        BigDecimal bigDecimal = (order.getOrderList())
                .stream()
                .map(x -> BigDecimal.valueOf(x.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return bigDecimal.setScale(2);
    }


}

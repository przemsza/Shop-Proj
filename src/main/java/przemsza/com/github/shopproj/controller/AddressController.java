package przemsza.com.github.shopproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import przemsza.com.github.shopproj.model.address.ClientAddress;
import przemsza.com.github.shopproj.model.address.ClientAddressRepository;
import przemsza.com.github.shopproj.model.order.ClientOrder;
import przemsza.com.github.shopproj.model.order.OrderRepository;

import java.math.BigDecimal;

@Controller
@RequestMapping("/address")
public class AddressController {

    private ClientAddressRepository clientAddressRepository;
    private ClientOrder clientOrder;
    private OrderController orderController;
    private OrderRepository orderRepository;

    @Autowired
    public AddressController(ClientAddressRepository clientAddressRepository, ClientOrder clientOrder, OrderController orderController, OrderRepository orderRepository) {
        this.clientAddressRepository = clientAddressRepository;
        this.clientOrder = clientOrder;
        this.orderController = orderController;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/send")
    public String getClientAddres(@ModelAttribute("address")ClientAddress clientAddress, Model model){
        System.out.println(clientAddress);
        clientAddressRepository.save(clientAddress);
        orderRepository.save(clientOrder.getOrder());
        model.addAttribute("address", clientAddress);
        return "redirect:/";
    }

    @GetMapping("")
    public String showFormAddress(Model model){
        model.addAttribute("address", new ClientAddress("","","","",""));
        model.addAttribute("items",clientOrder.getOrder().getOrderList());
        BigDecimal price = orderController.getPrice();
        model.addAttribute("price", price);
        return "address";
    }

}

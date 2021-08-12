package przemsza.com.github.shopproj.controller;

import org.apache.commons.mail.EmailException;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import przemsza.com.github.shopproj.mail.Mail;
import przemsza.com.github.shopproj.model.address.ClientAddress;
import przemsza.com.github.shopproj.model.address.ClientAddressRepository;
import przemsza.com.github.shopproj.model.client.Client;
import przemsza.com.github.shopproj.model.client.ClientRepository;
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
    private Mail email;
    private ClientRepository clientRepository;

    @Autowired
    public AddressController(ClientAddressRepository clientAddressRepository, ClientOrder clientOrder, OrderController orderController, OrderRepository orderRepository, Mail email,ClientRepository clientRepository) {
        this.clientAddressRepository = clientAddressRepository;
        this.clientOrder = clientOrder;
        this.orderController = orderController;
        this.orderRepository = orderRepository;
        this.email = email;
        this.clientRepository=clientRepository;
    }

    @PostMapping("/send")
    public String getClientAddres(@ModelAttribute("address")ClientAddress clientAddress,@ModelAttribute("client") Client client, Model model){
        clientAddressRepository.save(clientAddress);
        clientRepository.save(client);
        clientOrder.getOrder().setClient(client);
        orderRepository.save(clientOrder.getOrder());
        sendEmail(clientAddress, orderController.getPrice().doubleValue(), client);
        model.addAttribute("address", clientAddress);
        return "redirect:/";
    }

    @GetMapping("")
    public String showFormAddress(Model model){
        model.addAttribute("address", new ClientAddress("","",""));
        model.addAttribute("client", new Client("","",""));
        model.addAttribute("items",clientOrder.getOrder().getOrderList());
        BigDecimal price = orderController.getPrice();
        model.addAttribute("price", price);
        return "address";
    }


    private void sendEmail(ClientAddress clientAddress, Double price, Client client){
        try {
            email.sendEmail(clientOrder.getOrder(), clientAddress,price, client);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}

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

@Controller
@RequestMapping("/address")
public class AddressController {

    private ClientAddressRepository clientAddressRepository;

    @Autowired
    public AddressController(ClientAddressRepository clientAddressRepository) {
        this.clientAddressRepository = clientAddressRepository;
    }

    @PostMapping("/send")
    public String getClientAddres(@ModelAttribute("address")ClientAddress clientAddress, Model model){
        clientAddressRepository.save(clientAddress);
        model.addAttribute("address", clientAddress);
        return "address";
    }

    @GetMapping("")
    public String showFormAddress(Model model){
        model.addAttribute("address", new ClientAddress("", "",""));
        return "address";
    }

}

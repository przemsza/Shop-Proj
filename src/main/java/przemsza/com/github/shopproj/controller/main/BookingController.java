package przemsza.com.github.shopproj.controller.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import przemsza.com.github.shopproj.controller.main.check.ClientCheck;
import przemsza.com.github.shopproj.model.booking.Booking;
import przemsza.com.github.shopproj.model.booking.BookingRepository;
import przemsza.com.github.shopproj.model.booking.ClientBooking;
import przemsza.com.github.shopproj.model.client.Client;
import przemsza.com.github.shopproj.model.client.ClientRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private BookingRepository bookingRepository;
    private ClientBooking clientBooking;
    private ClientCheck clientCheck;

    @Autowired
    public BookingController(BookingRepository bookingRepository, ClientBooking clientBooking, ClientCheck clientCheck){
        this.bookingRepository = bookingRepository;
        this.clientBooking = clientBooking;
        this.clientCheck = clientCheck;
    }

    @GetMapping("")
    public String showBookingSite(Model model){
        if(model.getAttribute("client") == null || model.getAttribute("booking") == null) {
            model.addAttribute("client", new Client("", "", ""));
            model.addAttribute("booking", new ClientBooking().getBooking());
        }
        return "booking-page";
    }

    @PostMapping("/send")
    public String makeBooking(@ModelAttribute("booking") @Valid Booking booking, BindingResult bindingBooking,
                              @ModelAttribute("client") @Valid Client client, BindingResult bindingClient, Model model){

        if(bindingBooking.hasErrors() || bindingClient.hasErrors()){
            model.addAttribute("client", client);
            model.addAttribute("booking", booking);
            return "booking-page";
        }

        Client clientChecked = clientCheck.checkClintExist(client);
        booking.setClient(clientChecked);
        clientBooking.setBooking(booking);
        bookingRepository.save(clientBooking.getBooking());
        return "redirect:/booking";
    }
}

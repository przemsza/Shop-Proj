package przemsza.com.github.shopproj.controller.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import przemsza.com.github.shopproj.model.address.ClientAddress;
import przemsza.com.github.shopproj.model.booking.Booking;
import przemsza.com.github.shopproj.model.booking.BookingRepository;
import przemsza.com.github.shopproj.model.booking.ClientBooking;
import przemsza.com.github.shopproj.model.client.Client;
import przemsza.com.github.shopproj.model.client.ClientRepository;

import javax.validation.Valid;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private BookingRepository bookingRepository;
    private ClientRepository clientRepository;
    private ClientBooking clientBooking;

    @Autowired
    public BookingController(BookingRepository bookingRepository, ClientRepository clientRepository, ClientBooking clientBooking) {
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
        this.clientBooking = clientBooking;
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
        clientRepository.save(client);
        booking.setClient(client);
        clientBooking.setBooking(booking);
        bookingRepository.save(clientBooking.getBooking());
        return "redirect:/booking";

    }

}

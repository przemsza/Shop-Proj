package przemsza.com.github.shopproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import przemsza.com.github.shopproj.model.address.ClientAddress;
import przemsza.com.github.shopproj.model.booking.Booking;
import przemsza.com.github.shopproj.model.booking.BookingRepository;
import przemsza.com.github.shopproj.model.client.Client;
import przemsza.com.github.shopproj.model.client.ClientRepository;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private BookingRepository bookingRepository;
    private ClientRepository clientRepository;

    @Autowired
    public BookingController(BookingRepository bookingRepository, ClientRepository clientRepository) {
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping("")
    public String showBookingSite(Model model){
        model.addAttribute("client", new Client("","",""));
        model.addAttribute("booking", new Booking(LocalDate.now().toString(), "13:00",1,""));
        return "booking-page";

    }

    @PostMapping("/send")
    public String makeBooking(@ModelAttribute("booking") Booking booking, @ModelAttribute("client") Client client, Model model){
        clientRepository.save(client);
        booking.setClient(client);
        bookingRepository.save(booking);
        model.addAttribute("client", client);
        model.addAttribute("booking", booking);
        return "redirect:/booking";

    }

}

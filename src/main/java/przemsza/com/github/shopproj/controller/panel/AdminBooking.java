package przemsza.com.github.shopproj.controller.panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import przemsza.com.github.shopproj.model.booking.Booking;
import przemsza.com.github.shopproj.model.booking.BookingRepository;
import przemsza.com.github.shopproj.model.booking.BookingStatus;
import przemsza.com.github.shopproj.model.order.Order;
import przemsza.com.github.shopproj.model.order.OrderStatus;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/panel/booking")
public class AdminBooking {

    private BookingRepository bookingRepository;

    @Autowired
    public AdminBooking(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("")
    public String showBookings(Model model){
        List<Booking> bookingList = bookingRepository.findAll();
        model.addAttribute("bookings", bookingList);
        return "panel/panel-booking";
    }

    @GetMapping("/status/{name}")
    public String sortStatus(@PathVariable("name") BookingStatus bookingStatus, Model model){
        List<Booking> byStatusList = bookingRepository.findAllByStatus(bookingStatus);
        model.addAttribute("bookings",byStatusList);
        return "/panel/panel-booking";
    }

    @GetMapping("/canceled/{id}")
    public String changeStatusToCanceled(@PathVariable("id") Long bookingId ){
        Optional<Booking> byId = bookingRepository.findById(bookingId);
        byId.ifPresent(x->byId.get().setStatus(BookingStatus.CANCELED));
        bookingRepository.save(byId.get());
        return "redirect:/panel/booking";
    }

    @GetMapping("/accept/{id}")
    public String changeStatusToConfirmed(@PathVariable("id") Long bookingId ){
        Optional<Booking> byId = bookingRepository.findById(bookingId);
        byId.ifPresent(x->byId.get().setStatus(BookingStatus.CONFIRMED));
        bookingRepository.save(byId.get());
        return "redirect:/panel/booking";
    }
}

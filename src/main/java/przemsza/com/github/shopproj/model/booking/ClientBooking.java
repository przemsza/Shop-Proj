package przemsza.com.github.shopproj.model.booking;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import przemsza.com.github.shopproj.model.item.Item;
import przemsza.com.github.shopproj.model.order.Order;
import przemsza.com.github.shopproj.model.order.OrderStatus;

import java.time.LocalDate;

@Component
@SessionScope
public class ClientBooking {

    private Booking booking;

    public ClientBooking() {
        clear();
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
        booking.setStatus(BookingStatus.NEW);
    }

    public void clear() {
        booking = new Booking(LocalDate.now().toString(), "13:00", 1, "");
        booking.setStatus(BookingStatus.NEW);
    }
}

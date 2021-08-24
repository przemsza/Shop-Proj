package przemsza.com.github.shopproj.model.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    public List<Booking> findAllByStatus(BookingStatus bookingStatus);
}

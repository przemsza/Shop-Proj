package przemsza.com.github.shopproj.model.booking;

import org.springframework.format.annotation.NumberFormat;
import przemsza.com.github.shopproj.model.client.Client;
import przemsza.com.github.shopproj.model.order.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
      @Column(name = "local_date")
      @NotBlank(message = "Data jest wymagana")
      private String date;
      @NotBlank(message = "Czas jest wymagany")
      private String time;
      @NumberFormat
      private Integer numberOfPerson;
      @Column(length = 500)
      private String comments;
      @ManyToOne
      private Client client;
      @Enumerated(EnumType.STRING)
      private BookingStatus status;

        public Booking() {
        }

        public Booking(String date, String time, Integer numberOfPerson, String comments) {
            this.date = date;
            this.time = time;
            this.numberOfPerson = numberOfPerson;
            this.comments = comments;
            this.status = BookingStatus.NEW;
        }


    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        comments = comments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getNumberOfPerson() {
        return numberOfPerson;
    }

    public void setNumberOfPerson(Integer numberOfPerson) {
        this.numberOfPerson = numberOfPerson;
    }
}

package przemsza.com.github.shopproj.model.address;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class ClientAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "Ulica musi zostać podana")
    private String street;
    @Column(nullable = false)
    @NotBlank(message = "Miasto musi zostać podane")
    private String city;
    @Column(length = 500)
    private String comments;

    public ClientAddress() {
    }

    public ClientAddress(String street, String city, String comments) {
        this.street = street;
        this.city = city;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return String.format("Ulica: %s \n Miasto: %s",street,city);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientAddress)) return false;
        ClientAddress that = (ClientAddress) o;
        return getStreet().equals(that.getStreet()) && getCity().equals(that.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreet(), getCity());
    }
}

package przemsza.com.github.shopproj.model.address;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
public class ClientAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String telephone;
    @Email
    private String email;
    @Column(length = 500)
    private String comments;
    public ClientAddress() {
    }

    public ClientAddress(String street, String city, String telephone, String email, String comments) {
        this.street = street;
        this.city = city;
        this.telephone = telephone;
        this.email = email;
        this.comments = comments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "ClientAddress{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}

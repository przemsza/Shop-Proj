package przemsza.com.github.shopproj.model.client;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "name_and_last_name")
    @NotBlank(message = "Musisz podać imię oraz nazwisko")
    private String nameAndLastName;
    @Column(nullable = false)
    @NotBlank(message = "Numer telefonu musi być podany")
    private String telephone;
    @Email(message = "Podaj poprawny adres email")
    @NotBlank(message = "Podaj adres email")
    private String email;

    public Client(String nameAndLastName, String telephone, String email) {
        this.nameAndLastName = nameAndLastName;
        this.telephone = telephone;
        this.email = email;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAndLastName() {
        return nameAndLastName;
    }

    public void setNameAndLastName(String nameAndLastName) {
        this.nameAndLastName = nameAndLastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getNameAndLastName().equals(client.getNameAndLastName()) && getTelephone().equals(client.getTelephone()) && getEmail().equals(client.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNameAndLastName(), getTelephone(), getEmail());
    }
}

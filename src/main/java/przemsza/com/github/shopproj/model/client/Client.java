package przemsza.com.github.shopproj.model.client;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "name_and_last_name")
    private String nameAndLastName;
    @Column(nullable = false)
    private String telephone;
    @Email
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
}

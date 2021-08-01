package przemsza.com.github.shopproj.model.order;

import przemsza.com.github.shopproj.model.item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(
            name = "order_items",
            joinColumns = {@JoinColumn(name = "id_order", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_item", referencedColumnName = "id")}
    )
    private List<Item> orderList = new ArrayList<>();
    private String address;
    private String telephone;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order() {
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Item> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Item> orderList) {
        this.orderList = orderList;
    }
}

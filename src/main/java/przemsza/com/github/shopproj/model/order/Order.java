package przemsza.com.github.shopproj.model.order;

import przemsza.com.github.shopproj.model.address.ClientAddress;
import przemsza.com.github.shopproj.model.client.Client;
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
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name="addres_id")
    private ClientAddress address;
    
    public ClientAddress getAddress() {
        return address;
    }

    public void setAddress(ClientAddress address) {
        this.address = address;
    }

    public Order() {
        status = OrderStatus.NEW;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

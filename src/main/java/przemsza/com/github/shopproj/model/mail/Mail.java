package przemsza.com.github.shopproj.model.mail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;
import przemsza.com.github.shopproj.model.address.ClientAddress;
import przemsza.com.github.shopproj.model.client.Client;
import przemsza.com.github.shopproj.model.order.Order;

import java.util.stream.Collectors;

@Component
public class Mail {

    private Email email;

    public Mail() {
        email = new SimpleEmail();
    }

    private final String emailTitle = "Zamówienie z Pizzeria Roma - Siemianowice Śląskie";
    private final String ourEmail = "mateusz.kraspeed@gmail.com";
    private final String ourEmailLogin ="mateusz.kraspeed";
    private final String ourEmailPassword ="xxxxxxxxx";
    private Double price;

    public void sendEmail(Order order, Double price, String time) throws EmailException {
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(ourEmailLogin, ourEmailPassword));
        email.setSSLOnConnect(true);
        email.setFrom(ourEmail);
        email.setSubject(emailTitle);
        email.setMsg(createMessage(order, price, time));
        email.addTo(order.getClient().getEmail());
        email.send();
    }

    private String createMessage(Order order, Double price, String time) {

        String listOrder = createOrderListMessage(order);
        System.out.println(listOrder);
        return String.format("Dzień dobry, \n\n" +
                "Dziękujemy za złożenie zamówienia: \n %s \n" +
                "Do zapłaty: %f \n\n" +
                "Twoje zamówinie zostanie dostarczone za: %s minut \n\n" +
                "Pizzeria Roma - najlepsza pizza w mieście", listOrder, price, time);
    }

    private String createOrderListMessage(Order order){
       return (order.getOrderList()).stream()
               .map(x -> x.getName())
               .collect(Collectors
               .joining(" \n"));
    }
}

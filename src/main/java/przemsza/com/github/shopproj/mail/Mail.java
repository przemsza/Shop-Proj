package przemsza.com.github.shopproj.mail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Mail {
    Email email = new SimpleEmail();

    public void sendEmail(String status, String clientEmail, String clientName, String loadingPlace, String unloadingPlace, String cargo) throws EmailException {
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("mateusz.kraspeed", "Magazyn6"));
        email.setSSLOnConnect(true);
        email.setFrom("mateusz.kraspeed@gmail.com");
        email.setSubject(String.format("Cargo update: %s - %s", loadingPlace, unloadingPlace));
        String message = String.format("Dear %s,\n \n" +
                "Cargo: %s has been %s \n \n " +
                "Regards, Mateusz Zalewski - RamcioLogistics", clientName, cargo, status);
        email.setMsg(message);
        email.addTo(clientEmail);
        System.out.println("Rozpoczęto wysyłanie wiadomości email");
        email.send();
        System.out.println("Wiadomość wysłana");
    }
}

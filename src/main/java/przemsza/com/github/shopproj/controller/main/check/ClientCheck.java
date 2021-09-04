package przemsza.com.github.shopproj.controller.main.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import przemsza.com.github.shopproj.model.client.Client;
import przemsza.com.github.shopproj.model.client.ClientRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ClientCheck {

    private ClientRepository clientRepository;

    @Autowired
    public ClientCheck(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client checkClintExist(Client client){
        List<Client> allClients = clientRepository.findAll();
        Optional<Client> checkedClient = allClients.stream()
                .filter(x -> x.equals(client))
                .findFirst();

        if(checkedClient.isEmpty()) {
            clientRepository.save(client);
            return client;
        }
        return checkedClient.get();
    }
}

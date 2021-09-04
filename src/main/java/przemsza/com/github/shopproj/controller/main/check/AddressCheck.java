package przemsza.com.github.shopproj.controller.main.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import przemsza.com.github.shopproj.model.address.ClientAddress;
import przemsza.com.github.shopproj.model.address.ClientAddressRepository;

import java.util.List;
import java.util.stream.Stream;

@Component
public class AddressCheck {

    private ClientAddressRepository clientAddressRepository;

    @Autowired
    public AddressCheck(ClientAddressRepository clientAddressRepository) {
        this.clientAddressRepository = clientAddressRepository;
    }

    public ClientAddress checkAddress(ClientAddress clientAddress){
        List<ClientAddress> allAdress = clientAddressRepository.findAll();

        var checkedAddress = allAdress.stream()
                                                          .filter(x -> x.equals(clientAddress))
                                                          .findAny();

        if(checkedAddress.isEmpty()){
            clientAddressRepository.save(clientAddress);
            return clientAddress;
        }
        return checkedAddress.get();
    }
}

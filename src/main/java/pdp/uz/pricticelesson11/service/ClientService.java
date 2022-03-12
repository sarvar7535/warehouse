package pdp.uz.pricticelesson11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.pricticelesson11.entity.Client;
import pdp.uz.pricticelesson11.entity.ClientDTO;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public List<Client> getAll() {
        List<Client> all = clientRepository.findAll();
        return all;
    }

    public Client getById(Integer id) {
        Optional<Client> byId = clientRepository.findById(id);
        if (byId.isPresent()) {
            Client client = byId.get();
            return client;
        }
        return new Client();

    }

    public ApiResponse add(ClientDTO clientDTO) {
        Client client = new Client();

        if (clientRepository.existsByPhoneNumber(clientDTO.getPhoneNumber())) {
            return new ApiResponse("Bunday phoneNumber li Client mavjud", false);
        }
        client.setName(clientDTO.getName());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        clientRepository.save(client);
        return new ApiResponse("saved", true);

    }

    public ApiResponse edit(Integer id, ClientDTO clientDTO) {
        Optional<Client> byId = clientRepository.findById(id);
        boolean exists = clientRepository.existsByPhoneNumber(clientDTO.getPhoneNumber());
        if (!byId.isPresent()){
            return new ApiResponse("Not found Id", false);
        }
        if (exists){
            return new ApiResponse("Bunday phoneNumber li Client mavjud", false);
        }
        Client client = byId.get();
        client.setName(clientDTO.getName());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        clientRepository.save(client);
        return new ApiResponse("saved", true);

    }
    public ApiResponse delete(Integer id){
        Optional<Client> byId = clientRepository.findById(id);
        if (byId.isPresent()){
            clientRepository.deleteById(id);
            return new ApiResponse("Client deleted", true);
        }
        return new ApiResponse("not found Id", false);
    }


}

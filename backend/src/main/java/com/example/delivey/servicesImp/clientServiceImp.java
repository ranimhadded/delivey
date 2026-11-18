package com.example.delivey.servicesImp;

import com.example.delivey.dto.clientDto;
import com.example.delivey.model.client;
import com.example.delivey.repository.clientRepo;
import com.example.delivey.services.AuthService;
import com.example.delivey.services.clientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class clientServiceImp implements clientService {
   @Autowired
   private clientRepo cliRepo;
    @Autowired
    private AuthService authService;
    @Override
    public client AddClient(client c) {
        return cliRepo.save(c);
    }


    public client updateClient( String email,clientDto clientDto) {
        long clientId = Long.parseLong(authService.getClientIdByEmail(email));
        Optional<client> optionalClient = cliRepo.findById(clientId);
        if (optionalClient.isPresent()) {
            client existingClient = optionalClient.get();
            if (clientDto.getClientName() != null) {
                existingClient.setClientName(clientDto.getClientName());
            }
            if (clientDto.getEmail() != null) {
                existingClient.setEmail(clientDto.getEmail());
            }
            if (clientDto.getJob() != null) {
                existingClient.setJob(clientDto.getJob());
            }
            if (clientDto.getCompany() != null) {
                existingClient.setCompany(clientDto.getCompany());
            }
            if (clientDto.getPassword() != null) {
                existingClient.setPassword(clientDto.getPassword());
            }
            if (clientDto.getPhone() != null) {
                existingClient.setPhone(clientDto.getPhone());
            }
            if (clientDto.getIndustry() != null) {
                existingClient.setIndustry(clientDto.getIndustry());
            }

            return cliRepo.save(existingClient);
        }
        return null;
    }


    public ResponseEntity<String> deleteClient(Long clientId) {
        Optional<client> optionalClient = cliRepo.findById(clientId);
        if (optionalClient.isPresent()) {
            cliRepo.deleteById(clientId);
            return new ResponseEntity<>("Client deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
    }


    public List<client> getAllClients() {
        return cliRepo.findAll();
    }


    public client getClientById(Long clientId) {
        Optional<client> optionalClient = cliRepo.findById(clientId);
        return optionalClient.orElse(null);
    }
    public String getCompanyByEmail(String email) {
        return cliRepo.findCompanyNameByEmail(email);
    }
    public String getClientNameByEmail(String email) {
        return cliRepo.findClientNameByEmail(email);
    }
    public String getClientJobByEmail(String email) {
        return cliRepo.findClientJobByEmail(email);
    }
    public client getClientByEmail(String email) {
        return cliRepo.findByEmail(email);
    }
}







package com.example.delivey.controller;

import com.example.delivey.dto.clientDto;
import com.example.delivey.model.client;
import com.example.delivey.repository.clientRepo;
import com.example.delivey.servicesImp.clientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class clientController {

    @Autowired
    private clientServiceImp cliService;
    @Autowired
    private clientRepo cliRepo;
    @PostMapping("client/add")
    public ResponseEntity<client> AddClient(@RequestBody client c){
        client newc=cliService.AddClient(c);
        return new ResponseEntity<>(newc, HttpStatus.CREATED);
    }
    @PutMapping("client/update")
    public ResponseEntity<client> updateClient(@RequestParam("email") String email, @RequestBody clientDto ClientDto) {
        client updatedClient = cliService.updateClient(email, ClientDto);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }
    @DeleteMapping("/client/delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        return cliService.deleteClient(id);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<client>> getAllClients() {
        List<client> clients = cliService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<client> getClientById(@PathVariable Long id) {
        client client = cliService.getClientById(id);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/company")
    public ResponseEntity<String> getCompanyByEmail(@RequestParam("email") String email) {
        String company = cliService.getCompanyByEmail(email);
        return ResponseEntity.ok(company);
    }
    @GetMapping("/clientName")
    public ResponseEntity<String> getClientNameByEmail(@RequestParam("email") String email) {
        String name = cliService.getClientNameByEmail(email);
        return ResponseEntity.ok(name);
    }
    @GetMapping("/clientjob")
    public ResponseEntity<String> getClientJobByEmail(@RequestParam("email") String email) {
        String job = cliService.getClientJobByEmail(email);
        return ResponseEntity.ok(job);
    }
    @GetMapping("/client")
    public ResponseEntity<client> getClientByEmail(@RequestParam("email") String email) {
        client cli = cliService.getClientByEmail(email);
        return ResponseEntity.ok(cli);
    }
}

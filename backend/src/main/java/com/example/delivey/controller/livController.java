package com.example.delivey.controller;

import com.example.delivey.dto.LivreurDto;
import com.example.delivey.model.livreur;
import com.example.delivey.repository.livRepo;
import com.example.delivey.servicesImp.livServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class livController {
    @Autowired
    private livServiceImp livService;
    @Autowired
    private livRepo livRepo;
    @PostMapping("livreur/add")
    public ResponseEntity<livreur> AddLivreur(@RequestBody livreur l){
        livreur newl=livService.AddLivreur(l);
        return new ResponseEntity<>(newl, HttpStatus.CREATED);
    }

    @DeleteMapping("livreur/delete/{livreurId}")
    public ResponseEntity<String> deleteLivreur(@PathVariable Long livreurId) {
        return livService.deleteLivreur(livreurId);
    }

    @GetMapping("livreurs")
    public ResponseEntity<List<livreur>> getAllLivreurs() {
        List<livreur> livreurs = livService.getAllLivreurs();
        return new ResponseEntity<>(livreurs, HttpStatus.OK);
    }

    @GetMapping("livreur/{livreurId}")
    public ResponseEntity<livreur> getLivreurById(@PathVariable Long livreurId) {
        livreur retrievedLivreur = livService.getLivreurById(livreurId);
        if (retrievedLivreur != null) {
            return new ResponseEntity<>(retrievedLivreur, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("livreur/update/{livreurId}")
    public ResponseEntity<livreur> updateLivreur(@PathVariable Long livreurId, @RequestBody LivreurDto livreurDto) {
        livreur updatedLivreur = livService.updateLivreur(livreurId, livreurDto);
        if (updatedLivreur != null) {
            return new ResponseEntity<>(updatedLivreur, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}

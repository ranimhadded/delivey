package com.example.delivey.controller;
import com.example.delivey.model.Notification;
import com.example.delivey.model.client;
import com.example.delivey.model.colis;
import com.example.delivey.repository.NotificationRepo;
import com.example.delivey.repository.colisRepo;
import com.example.delivey.services.AuthService;
import com.example.delivey.servicesImp.colisServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class colisController {
    @Autowired
    private colisServiceImp colService;
    @Autowired
    private colisRepo colRepo;
    @Autowired
    private NotificationRepo notificationRepository;
    @Autowired
    private com.example.delivey.repository.clientRepo clientRepo;
    @Autowired
    private AuthService authService;
    @PostMapping("/colis/add")
    public ResponseEntity<colis> AddColis(@RequestParam("email") String email, @RequestBody colis col) {
        String clientId = authService.getClientIdByEmail(email);
        if (clientId != null) {
            col.setClientEmail(email);
            client client = clientRepo.findById(Long.valueOf(clientId)).orElse(null);
            if (client != null) {
                col.setClient(client);
                colis newcol = colService.AddColis(col);
                Notification notification = new Notification();
                notification.setMessage("A new package has been added by client: " + email);
                notificationRepository.save(notification);
                return new ResponseEntity<>(newcol, HttpStatus.CREATED);
            }
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/colissbyemail")
    public ResponseEntity<List<colis>> listColisByClientEmail(@RequestParam("email") String email) {
        String clientId = authService.getClientIdByEmail(email);
        if (clientId != null) {
            List<colis> coliss = colService.findByClientIdOrderByCreatedAtAsc(clientId);
            return new ResponseEntity<>(coliss, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("colis/dashboard-counts")
    public ResponseEntity<Map<String, Integer>> getColisDashboardCounts(@RequestParam("email") String email) {
        Map<String, Integer> counts = new HashMap<>();
        int totalCount = colService.count();
        int enCoursCount = colService.getColisCountByStatus("pending", email);
        int retourCount = colService.getColisCountByStatus("returned", email);
        int livreCount = colService.getColisCountByStatus("delivered", email);
        int livrePayeCount = colService.getColisCountByStatus("delivered payed", email);
        int echangeCount = colService.getColisCountByEchange("oui", email);

        counts.put("totalCount", totalCount);
        counts.put("enCoursCount", enCoursCount);
        counts.put("retourCount", retourCount);
        counts.put("livreCount", livreCount);
        counts.put("livrePayeCount", livrePayeCount);
        counts.put("echangeCount", echangeCount);

        return new ResponseEntity<>(counts, HttpStatus.OK);
    }
    @GetMapping("Allcolis/dashboard-counts")
    public ResponseEntity<Map<String, Integer>> getColisDashboardCounts() {
        Map<String, Integer> counts = new HashMap<>();
        int totalCount = colService.count();
        int enCoursCount = colService.getColisCountByStatus("pending");
        int retourCount = colService.getColisCountByStatus("returned");
        int livreCount = colService.getColisCountByStatus("delivered");
        int livrePayeCount = colService.getColisCountByStatus("delivered payed");


        counts.put("totalCount", totalCount);
        counts.put("enCoursCount", enCoursCount);
        counts.put("retourCount", retourCount);
        counts.put("livreCount", livreCount);
        counts.put("livrePayeCount", livrePayeCount);


        return new ResponseEntity<>(counts, HttpStatus.OK);
    }
    @GetMapping("/coliss")
    public ResponseEntity<List<colis>> listAllCategorys(){
        List<colis> coliss = colService.findAllByOrderByCreatedAtAsc();
        return new ResponseEntity<>(coliss, HttpStatus.OK);
    }



    @GetMapping("/find/{idColis}")
    public ResponseEntity<colis> getColisById(@PathVariable("idColis") Long idColis){
        colis col = colService.getColisById(idColis);
        return new ResponseEntity<>(col,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{idColis}")
    public ResponseEntity<?> deleteColis(@PathVariable("idColis") Long idColis){
        colService.deleteColis(idColis);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/update/{idColis}")
    public ResponseEntity<colis> updateCategory(@PathVariable("idColis") long idColis, @RequestBody colis coli) {
        Optional<colis> catt = colRepo.findById(idColis);
        if (catt.isPresent()) {
            colis cat = catt.get();
            cat.setClientName(coli.getClientName());
            cat.setClientAdress(coli.getClientAdress());
            cat.setClientNumber(coli.getClientNumber());
            cat.setClientCitie(coli.getClientCitie());
            cat.setClientState(coli.getClientState());
            cat.setExchange(coli.getExchange());
            cat.setFragil(coli.getFragil());
            cat.setMsg(coli.getMsg());
            cat.setType(coli.getType());
            cat.setNbPiece(coli.getNbPiece());
            cat.setWeight(coli.getWeight());
            cat.setPrice(coli.getPrice());
            cat.setStatus(coli.getStatus());

            // Check if coli.getClient() is not null before updating the association
            if (coli.getClient() != null) {
                cat.setClient(coli.getClient());
            }

            return new ResponseEntity<>(colRepo.save(cat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /* @GetMapping("/coliss")
    public ResponseEntity<List<colis>> listAllColis() {
        LocalDate today = LocalDate.now();
        List<colis> coliss = colService.findByCreatedAt(today);
        return new ResponseEntity<>(coliss, HttpStatus.OK);
    }*/
   @GetMapping("/colis/today")
   public List<colis> getColisForToday() {
       return colService.getColisForToday();
   }
    @PutMapping("livreur/{colisId}/status")
    public void updateColisStatus(@PathVariable Long colisId, @RequestBody String newStatus) {
        String trimmedStatus = newStatus.trim().replace("\"", "");

        if (trimmedStatus.equals("delivered payed")) {
            colService.updateColisEtat(colisId, "validated");
            colService.updateColisStatus(colisId, trimmedStatus);
        } else {
            colService.updateColisStatus(colisId, trimmedStatus);
        }
    }



    @GetMapping("/colis/{status}")
    public List<colis> getColisByStatus(@PathVariable String status) {
        return colService.findAllColisByStatus(status);
    }
    @GetMapping("/totaldept")
    public ResponseEntity<Float> getTotalDept(@RequestParam("email") String email) {
        String status = "delivered payed";
        String etat = "not validated";
        Float totalDept = colService.getTotalDept(email, status, etat);
        return ResponseEntity.ok(totalDept);
    }


    @GetMapping("/totalpayed")
    public ResponseEntity<Float> getTotalPayed(@RequestParam("email") String email) {
        String status = "delivered payed";
        String etat = "validated";
        Float totalPayed = colService.gettotalpayed(email, status, etat);
        return ResponseEntity.ok(totalPayed);
    }
    @GetMapping("client/colis/{status}")
    public List<colis> getColisByStatus(@PathVariable String status, @RequestParam("email") String email)
    {
        return colService.findClientColisByStatus(status,email);
    }
}

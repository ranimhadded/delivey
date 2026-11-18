package com.example.delivey.servicesImp;

import com.example.delivey.model.client;
import com.example.delivey.model.colis;
import com.example.delivey.repository.clientRepo;
import com.example.delivey.repository.colisRepo;
import com.example.delivey.services.colisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class colisServiceImp implements colisService {
    @Autowired
    private colisRepo colRepo;

    public List<colis> findAllByOrderByCreatedAtAsc() {
        return colRepo.findAllByOrderByCreatedAtAsc();
    }

    @Override
    public List<colis> findByClientId(String clientId) {
        return null;
    }

    public List<colis> findByClientIdOrderByCreatedAtAsc(String clientId) {
        return colRepo.findByClientEmailOrderByCreatedAtAsc(clientId);
    }

    @Override
    public int getColisCountByStatus(String status, String clientEmail) {
        return colRepo.getColisCountByStatusAndClientEmail(status, clientEmail);
    }



    @Override
    public int getColisCountByEchange(String echange, String clientEmail) {
        return colRepo.getColisCountByEchangeAndClientEmail(echange, clientEmail);
    }

    @Override
    public int getColisCountByStatus(String status) {
        return colRepo.getColisCountByStatus(status);
    }

    @Override
    public Float getTotalDept(String clientEmail, String status, String etat) {
        return colRepo.getTotalDept(clientEmail, status, etat);
    }


    @Override
    public Float gettotalpayed(String clientEmail, String status, String etat) {
        return colRepo.gettotalpayed(clientEmail, status, etat);
    }

    @Override
    public void updateColisEtat(Long colisId, String newEtat) {
        Optional<colis> optionalColis = colRepo.findById(colisId);
        if (optionalColis.isPresent()) {
            colis colis = optionalColis.get();
            colis.setEtat(newEtat);
            colRepo.save(colis);
        } else {
            throw new NoSuchElementException("Colis not found with ID: " + colisId);
        }
    }


    @Override
    public colis AddColis(colis col) {
        col.setCreatedAt(LocalDateTime.now());
        return colRepo.save(col);
    }
    @Override
    public colis getColisById(Long idColis) {
        return colRepo.findById(idColis).get();

    }
    @Override
    public void deleteColis(Long idColis) {
        colRepo.deleteById(idColis);
    }
    public int count(){
        return (int) colRepo.count();
    }
    @Override
    public List<colis> findbytitle(String title) {
        return colRepo.findbytitle(title);
    }
    public void updateColisStatus(Long colisId, String newStatus) {
        colis foundcolis = colRepo.findById(colisId).orElseThrow(() -> new IllegalArgumentException("Invalid Colis ID"));
        foundcolis.setStatus(newStatus);
        colRepo.save(foundcolis);
    }

    public List<colis> findAllColisByStatus(String status) {
        return colRepo.findAllByStatus(status);
    }
    public List<colis> getColisForToday() {
        Date currentDate = new Date();
        return colRepo.findByCreatedAt(currentDate);
    }
    public List<colis> findClientColisByStatus(String status,String clientEmail) {
        return colRepo.findClientByStatus(status,clientEmail);
    }
}

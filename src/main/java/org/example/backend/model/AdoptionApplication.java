package org.example.backend.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.model.enums.ApplicationStatus;
import org.example.backend.model.enums.AnimalStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class AdoptionApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate submissionDate;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private List<PreAdoptionForm> preAdoptionForms = new ArrayList<>();

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private List<AdoptionContract> contracts = new ArrayList<>();

    public void runAutomatedValidation() {
        if (client != null && client.isBlocked())
            this.status = ApplicationStatus.REJECTED_BY_SYSTEM;
         else
             this.status = ApplicationStatus.IN_PROGRESS;
    }

    public void acceptApplication() {
        this.status = ApplicationStatus.ACCEPTED;
        if (this.animal != null)
            this.animal.setStatus(AnimalStatus.DURING_ADOPTION);
    }

    public void rejectApplication() {
        this.status = ApplicationStatus.REJECTED;
    }

    public PreAdoptionForm addPreAdoptionForm(double apartmentSize, boolean hasOtherAnimals) {
        PreAdoptionForm form = new PreAdoptionForm(this, apartmentSize, hasOtherAnimals);
        this.preAdoptionForms.add(form);
        return form;
    }

    public void removePreAdoptionForm(PreAdoptionForm form) {
        this.preAdoptionForms.remove(form);
    }

    public AdoptionContract addContract(LocalDate signingDate, String adoptionTerms) {
        AdoptionContract contract = new AdoptionContract(this, signingDate, adoptionTerms);
        this.contracts.add(contract);
        return contract;
    }

    public void removeContract(AdoptionContract contract) {
        this.contracts.remove(contract);
    }

    public List<PreAdoptionForm> getPreAdoptionForms() {
        return Collections.unmodifiableList(preAdoptionForms);
    }

    public List<AdoptionContract> getContracts() {
        return Collections.unmodifiableList(contracts);
    }
}

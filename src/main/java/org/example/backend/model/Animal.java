package org.example.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.exception.UnsupportedException;
import org.example.backend.model.enums.AnimalStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String species;
    private int approximateAge;
    private LocalDate admissionDate;
    private boolean isQuarantined;

    @Enumerated(EnumType.STRING)
    private AnimalStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cage_id", nullable = false)
    private Cage cage;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private List<Walk> walks = new ArrayList<>();

    @OneToMany(mappedBy = "animal")
    private List<AdoptionApplication> adoptionApplications = new ArrayList<>();

    public void moveToCage(Cage newCage) {
        throw new UnsupportedException("Opreration unavailable");
    }

    public MedicalRecord addMedicalRecord(LocalDate recordDate, String diagnosisDescription,
                                          String givenMedication, String dosageInfo, String recommendations) {
        MedicalRecord record = new MedicalRecord(this, recordDate, diagnosisDescription,
                givenMedication, dosageInfo, recommendations);
        this.medicalRecords.add(record);
        return record;
    }

    public void removeMedicalRecord(MedicalRecord record) {
        this.medicalRecords.remove(record);
    }

    public Walk addWalk(LocalDate walkDate, LocalTime walkTime, String behavioralNotes) {
        Walk walk = new Walk(this, walkDate, walkTime, behavioralNotes);
        this.walks.add(walk);
        return walk;
    }

    public void removeWalk(Walk walk) {
        this.walks.remove(walk);
    }

    public List<MedicalRecord> getMedicalRecords() {
        return Collections.unmodifiableList(medicalRecords);
    }

    public List<Walk> getWalks() {
        return Collections.unmodifiableList(walks);
    }
}

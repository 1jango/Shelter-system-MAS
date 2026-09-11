package org.example.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.exception.UnsupportedException;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private LocalDate recordDate;
    private String diagnosisDescription;
    private String givenMedication;
    private String dosageInfo;
    private String recommendations;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "animal_id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Animal animal;

    MedicalRecord(Animal animal, LocalDate recordDate, String diagnosisDescription,
                  String givenMedication, String dosageInfo, String recommendations) {
        this.animal = Objects.requireNonNull(animal, "MedicalRecord cannot exist without an Animal");
        this.recordDate = recordDate;
        this.diagnosisDescription = diagnosisDescription;
        this.givenMedication = givenMedication;
        this.dosageInfo = dosageInfo;
        this.recommendations = recommendations;
    }

    public void updateDosage(String dosageInfo) {
        throw new UnsupportedException("Operation unavailible");
    }
}

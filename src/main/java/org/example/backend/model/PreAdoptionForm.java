package org.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PreAdoptionForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private double apartmentSize;
    private boolean hasOtherAnimals;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "application_id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private AdoptionApplication application;

    PreAdoptionForm(AdoptionApplication application, double apartmentSize, boolean hasOtherAnimals) {
        this.application = Objects.requireNonNull(application, "PreAdoptionForm cannot exist without an AdoptionApplication");
        this.apartmentSize = apartmentSize;
        this.hasOtherAnimals = hasOtherAnimals;
    }
}

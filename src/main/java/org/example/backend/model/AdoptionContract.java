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
public class AdoptionContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private LocalDate signingDate;
    private String adoptionTerms;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "application_id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private AdoptionApplication application;

    AdoptionContract(AdoptionApplication application, LocalDate signingDate, String adoptionTerms) {
        this.application = Objects.requireNonNull(application, "AdoptionContract cannot exist without an AdoptionApplication");
        this.signingDate = signingDate;
        this.adoptionTerms = adoptionTerms;
    }

    public void finalizeAdoption() {
        throw new UnsupportedException("Operation unavailble");
    }
}

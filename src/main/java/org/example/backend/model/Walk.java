package org.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Walk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private LocalDate walkDate;
    private LocalTime walkTime;
    private String behavioralNotes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "animal_id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Animal animal;

    @ManyToMany(mappedBy = "walks")
    private List<Volunteer> volunteers = new ArrayList<>();

    Walk(Animal animal, LocalDate walkDate, LocalTime walkTime, String behavioralNotes) {
        this.animal = Objects.requireNonNull(animal, "Walk cannot exist without an Animal");
        this.walkDate = walkDate;
        this.walkTime = walkTime;
        this.behavioralNotes = behavioralNotes;
    }
}

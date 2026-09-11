package org.example.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.exception.UnsupportedException;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Client extends Person {
    private String idCardNumber;
    private boolean isBlocked;

    @OneToMany(mappedBy = "client")
    private List<AdoptionApplication> applications = new ArrayList<>();

    public void submitApplication(Animal animal, PreAdoptionForm preAdoptionForm) {
        throw new UnsupportedException("Operation unavailible");
    }
}

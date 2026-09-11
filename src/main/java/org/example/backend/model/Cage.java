package org.example.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cageNumber;
    private String sector;
    private int maxCapacity;

    @OneToMany(mappedBy = "cage")
    private List<Animal> animals = new ArrayList<>();
}
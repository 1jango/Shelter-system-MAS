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
public class Volunteer extends Person {
    public static final int BENEFICENT_THRESHOLD = 150;

    private int totalHours;

    @Transient
    private boolean isBeneficent;

    @ManyToMany
    @JoinTable(
            name = "volunteer_walk",
            joinColumns = @JoinColumn(name = "volunteer_id"),
            inverseJoinColumns = @JoinColumn(name = "walk_id")
    )
    private List<Walk> walks = new ArrayList<>();

    public boolean isBeneficent() {
        throw new UnsupportedException("unavailible");
    }

    public void addHours(int hours) {
        throw new UnsupportedException("unavailible");
    }

    public void registerWalk() {
        throw new UnsupportedException("unavailible");
    }
}

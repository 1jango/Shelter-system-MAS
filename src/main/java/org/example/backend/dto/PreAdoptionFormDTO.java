package org.example.backend.dto;

import lombok.Data;

@Data
public class PreAdoptionFormDTO {
    private Long id;
    private int version;
    private double apartmentSize;
    private boolean hasOtherAnimals;
}

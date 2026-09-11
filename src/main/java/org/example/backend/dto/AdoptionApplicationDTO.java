package org.example.backend.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class AdoptionApplicationDTO {
    private Long id;
    private LocalDate submissionDate;
    private String status;

    private Long clientId;
    private String clientFullName;
    private String clientEmail;

    private Long animalId;
    private String animalName;
    private String animalSpecies;

    private Long employeeId;
    private String employeeFullName;

    private Double apartmentSize;
    private Boolean hasOtherAnimals;

    private List<PreAdoptionFormDTO> preAdoptionForms;
}
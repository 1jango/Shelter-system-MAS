package org.example.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.exception.UnsupportedException;
import org.example.backend.model.enums.ContractType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Employee extends Person {
    private LocalDate hireDate;
    private int yearsOfService;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private List<EmploymentContract> employmentContracts = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<AdoptionApplication> adoptionApplications = new ArrayList<>();

    public double calculateSalary() {
        throw new UnsupportedException("Operation unavailible");
    }

    public void verifyApplication(AdoptionApplication application) {
        throw new UnsupportedException("Operation unavailible");
    }

    public void blockClient(Client client) {
        throw new UnsupportedException("Operation unavailible");
    }

    public EmploymentContract addEmploymentContract(String contractNumber, LocalDate startDate,
                                                    LocalDate endDate, ContractType contractType, String FTE) {
        EmploymentContract contract = new EmploymentContract(this, contractNumber, startDate, endDate, contractType, FTE);
        this.employmentContracts.add(contract);
        return contract;
    }

    public void removeEmploymentContract(EmploymentContract contract) {
        this.employmentContracts.remove(contract);
    }

    public List<EmploymentContract> getEmploymentContracts() {
        return Collections.unmodifiableList(employmentContracts);
    }
}

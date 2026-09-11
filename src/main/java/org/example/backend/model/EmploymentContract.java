package org.example.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.exception.UnsupportedException;
import org.example.backend.model.enums.ContractType;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmploymentContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String contractNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private String FTE;

    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Employee employee;

    EmploymentContract(Employee employee, String contractNumber, LocalDate startDate,
                       LocalDate endDate, ContractType contractType, String FTE) {
        this.employee = Objects.requireNonNull(employee, "EmploymentContract cannot exist without an Employee");
        this.contractNumber = contractNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contractType = contractType;
        this.FTE = FTE;
    }

    public boolean isContractValid() {
        throw new UnsupportedException("Operation unavailible");
    }
}

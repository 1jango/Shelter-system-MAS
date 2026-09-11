package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.AdoptionApplicationDTO;
import org.example.backend.dto.PreAdoptionFormDTO;
import org.example.backend.model.*;
import org.example.backend.model.enums.ApplicationStatus;
import org.example.backend.repository.AdoptionApplicationRepository;
import org.example.backend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionApplicationService {

    private final AdoptionApplicationRepository applicationRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<AdoptionApplicationDTO> getAllApplications() {
        return applicationRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public AdoptionApplicationDTO getApplicationById(Long id) {
        AdoptionApplication application = getApplicationEntityById(id);
        return convertToDTO(application);
    }


    @Transactional
    public AdoptionApplicationDTO processApplicationSelection(Long applicationId, Long employeeId) {
        AdoptionApplication application = getApplicationEntityById(applicationId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + employeeId));

        application.setEmployee(employee);
        application.runAutomatedValidation();

        AdoptionApplication savedApplication = applicationRepository.save(application);
        return convertToDTO(savedApplication);
    }


    @Transactional
    public AdoptionApplicationDTO flagApplicationAsIncomplete(Long applicationId) {
        AdoptionApplication application = getApplicationEntityById(applicationId);

        application.setStatus(ApplicationStatus.IN_PROGRESS);

        AdoptionApplication savedApplication = applicationRepository.save(application);
        return convertToDTO(savedApplication);
    }


    @Transactional
    public AdoptionApplicationDTO handleIncompleteForm(Long applicationId, double apartmentSize, boolean hasOtherAnimals) {
        AdoptionApplication application = getApplicationEntityById(applicationId);

        application.addPreAdoptionForm(apartmentSize, hasOtherAnimals);

        application.setStatus(ApplicationStatus.IN_PROGRESS);

        AdoptionApplication savedApplication = applicationRepository.save(application);
        return convertToDTO(savedApplication);
    }


    @Transactional
    public AdoptionApplicationDTO acceptApplication(Long applicationId) {
        AdoptionApplication application = getApplicationEntityById(applicationId);

        application.acceptApplication();

        application.addContract(LocalDate.now(), "Standard terms and conditions applied.");

        AdoptionApplication savedApplication = applicationRepository.save(application);
        return convertToDTO(savedApplication);
    }


    @Transactional
    public AdoptionApplicationDTO rejectApplication(Long applicationId) {
        AdoptionApplication application = getApplicationEntityById(applicationId);

        application.rejectApplication();

        AdoptionApplication savedApplication = applicationRepository.save(application);
        return convertToDTO(savedApplication);
    }


    private AdoptionApplication getApplicationEntityById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Application not found with ID: " + id));
    }


    private AdoptionApplicationDTO convertToDTO(AdoptionApplication app) {
        AdoptionApplicationDTO dto = new AdoptionApplicationDTO();
        dto.setId(app.getId());
        dto.setSubmissionDate(app.getSubmissionDate());
        dto.setStatus(app.getStatus() != null ? app.getStatus().name() : null);

        if (app.getClient() != null) {
            dto.setClientId(app.getClient().getId());
            dto.setClientFullName(app.getClient().getFirstName() + " " + app.getClient().getLastName());
            dto.setClientEmail(app.getClient().getEmail());
        }

        if (app.getAnimal() != null) {
            dto.setAnimalId(app.getAnimal().getId());
            dto.setAnimalName(app.getAnimal().getName());
            dto.setAnimalSpecies(app.getAnimal().getSpecies());
        }

        if (app.getEmployee() != null) {
            dto.setEmployeeId(app.getEmployee().getId());
            dto.setEmployeeFullName(app.getEmployee().getFirstName() + " " + app.getEmployee().getLastName());
        }

        List<PreAdoptionForm> forms = app.getPreAdoptionForms();
        List<PreAdoptionFormDTO> formDtos = new ArrayList<>();
        for (int i = 0; i < forms.size(); i++) {
            PreAdoptionForm form = forms.get(i);
            PreAdoptionFormDTO formDto = new PreAdoptionFormDTO();
            formDto.setId(form.getId());
            formDto.setVersion(i + 1);
            formDto.setApartmentSize(form.getApartmentSize());
            formDto.setHasOtherAnimals(form.isHasOtherAnimals());
            formDtos.add(formDto);
        }
        dto.setPreAdoptionForms(formDtos);

        if (!forms.isEmpty()) {
            PreAdoptionForm latestForm = forms.get(forms.size() - 1);
            dto.setApartmentSize(latestForm.getApartmentSize());
            dto.setHasOtherAnimals(latestForm.isHasOtherAnimals());
        }

        return dto;
    }
}
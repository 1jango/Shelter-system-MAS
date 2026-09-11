package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.AdoptionApplicationDTO;
import org.example.backend.service.AdoptionApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdoptionApplicationController {

    private final AdoptionApplicationService applicationService;

    @GetMapping
    public List<AdoptionApplicationDTO> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public AdoptionApplicationDTO getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    @PostMapping("/{id}/select")
    public AdoptionApplicationDTO selectApplication(@PathVariable Long id, @RequestParam Long employeeId) {
        return applicationService.processApplicationSelection(id, employeeId);
    }

    @PostMapping("/{id}/flag-incomplete")
    public AdoptionApplicationDTO flagIncomplete(@PathVariable Long id) {
        return applicationService.flagApplicationAsIncomplete(id);
    }

    @PutMapping("/{id}/client-update")
    public AdoptionApplicationDTO clientUpdateForm(
            @PathVariable Long id,
            @RequestParam double apartmentSize,
            @RequestParam boolean hasOtherAnimals) {
        return applicationService.handleIncompleteForm(id, apartmentSize, hasOtherAnimals);
    }

    @PostMapping("/{id}/accept")
    public AdoptionApplicationDTO acceptApplication(@PathVariable Long id) {
        return applicationService.acceptApplication(id);
    }

    @PostMapping("/{id}/reject")
    public AdoptionApplicationDTO rejectApplication(@PathVariable Long id) {
        return applicationService.rejectApplication(id);
    }
}
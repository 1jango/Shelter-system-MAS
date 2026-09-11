package org.example.backend.config;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.*;
import org.example.backend.model.enums.ApplicationStatus;
import org.example.backend.model.enums.AnimalStatus;
import org.example.backend.repository.AdoptionApplicationRepository;
import org.example.backend.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DatabaseDataSeeder implements CommandLineRunner {

    private final AdoptionApplicationRepository applicationRepository;
    private final EmployeeRepository employeeRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (applicationRepository.count() > 0) {
            return;
        }

        Employee employee = new Employee();
        employee.setFirstName("Maciej");
        employee.setLastName("Kowalski");
        employee.setEmail("m.kowalski@schronisko.pl");
        employee.setPhoneNumber("123456789");
        employee.setHireDate(LocalDate.of(2022, 3, 15));
        employee.setYearsOfService(4);
        employeeRepository.save(employee);

        Cage cageA = new Cage(); cageA.setCageNumber("A-01"); cageA.setSector("Kwarantanna"); cageA.setMaxCapacity(2); entityManager.persist(cageA);
        Cage cageB = new Cage(); cageB.setCageNumber("B-01"); cageB.setSector("Adopcyjny"); cageB.setMaxCapacity(4); entityManager.persist(cageB);

        Animal max = new Animal(); max.setName("Max"); max.setSpecies("Pies"); max.setStatus(AnimalStatus.READY_FOR_ADOPTION); max.setCage(cageB); entityManager.persist(max);
        Animal luna = new Animal(); luna.setName("Luna"); luna.setSpecies("Kot"); luna.setStatus(AnimalStatus.READY_FOR_ADOPTION); luna.setCage(cageB); entityManager.persist(luna);
        Animal burek = new Animal(); burek.setName("Burek"); burek.setSpecies("Pies"); burek.setStatus(AnimalStatus.READY_FOR_ADOPTION); burek.setCage(cageA); entityManager.persist(burek);
        Animal mruszek = new Animal(); mruszek.setName("Mruszek"); mruszek.setSpecies("Kot"); mruszek.setStatus(AnimalStatus.READY_FOR_ADOPTION); mruszek.setCage(cageB); entityManager.persist(mruszek);
        Animal reksio = new Animal(); reksio.setName("Reksio"); reksio.setSpecies("Pies"); reksio.setStatus(AnimalStatus.READY_FOR_ADOPTION); reksio.setCage(cageA); entityManager.persist(reksio);
        Animal bela = new Animal(); bela.setName("Bela"); bela.setSpecies("Pies"); bela.setStatus(AnimalStatus.READY_FOR_ADOPTION); bela.setCage(cageB); entityManager.persist(bela);

        Client jan = new Client(); jan.setFirstName("Jan"); jan.setLastName("Kowalski"); jan.setEmail("jan.kowalski@gmail.com"); jan.setIdCardNumber("ABC 100001"); jan.setBlocked(false); entityManager.persist(jan);
        Client anna = new Client(); anna.setFirstName("Anna"); anna.setLastName("Nowak"); anna.setEmail("anna.nowak@gmail.com"); anna.setIdCardNumber("ABC 100002"); anna.setBlocked(true); entityManager.persist(anna);
        Client piotr = new Client(); piotr.setFirstName("Piotr"); piotr.setLastName("Wiśniewski"); piotr.setEmail("p.wisniewski@gmail.com"); piotr.setIdCardNumber("ABC 100003"); piotr.setBlocked(false); entityManager.persist(piotr);
        Client maria = new Client(); maria.setFirstName("Maria"); maria.setLastName("Lewandowska"); maria.setEmail("m.lewandowska@gmail.com"); maria.setIdCardNumber("ABC 100004"); maria.setBlocked(false); entityManager.persist(maria);
        Client tomasz = new Client(); tomasz.setFirstName("Tomasz"); tomasz.setLastName("Zieliński"); tomasz.setEmail("t.zielinski@gmail.com"); tomasz.setIdCardNumber("ABC 100005"); tomasz.setBlocked(true); entityManager.persist(tomasz);
        Client katarzyna = new Client(); katarzyna.setFirstName("Katarzyna"); katarzyna.setLastName("Woźniak"); katarzyna.setEmail("k.wozniak@gmail.com"); katarzyna.setIdCardNumber("ABC 100006"); katarzyna.setBlocked(false); entityManager.persist(katarzyna);

        AdoptionApplication app1 = new AdoptionApplication();
        app1.setSubmissionDate(LocalDate.now()); app1.setStatus(ApplicationStatus.NEW); app1.setClient(jan); app1.setAnimal(max);
        app1.addPreAdoptionForm(30.0, false);
        app1.addPreAdoptionForm(45.0, false);
        applicationRepository.save(app1);

        AdoptionApplication app2 = new AdoptionApplication();
        app2.setSubmissionDate(LocalDate.now()); app2.setStatus(ApplicationStatus.NEW); app2.setClient(anna); app2.setAnimal(luna);
        app2.addPreAdoptionForm(28.0, true);
        app2.addPreAdoptionForm(35.0, true);
        applicationRepository.save(app2);

        AdoptionApplication app3 = new AdoptionApplication();
        app3.setSubmissionDate(LocalDate.now().minusDays(2)); app3.setStatus(ApplicationStatus.IN_PROGRESS); app3.setClient(piotr); app3.setAnimal(burek);
        app3.addPreAdoptionForm(25.0, false);
        app3.addPreAdoptionForm(48.0, true);
        app3.addPreAdoptionForm(70.0, false);
        applicationRepository.save(app3);

        AdoptionApplication app4 = new AdoptionApplication();
        app4.setSubmissionDate(LocalDate.now()); app4.setStatus(ApplicationStatus.NEW); app4.setClient(maria); app4.setAnimal(mruszek);
        app4.addPreAdoptionForm(42.0, true);
        app4.addPreAdoptionForm(50.0, false);
        applicationRepository.save(app4);

        AdoptionApplication app5 = new AdoptionApplication();
        app5.setSubmissionDate(LocalDate.now().minusDays(1)); app5.setStatus(ApplicationStatus.NEW); app5.setClient(tomasz); app5.setAnimal(reksio);
        app5.addPreAdoptionForm(95.0, true);
        app5.addPreAdoptionForm(120.0, true);
        applicationRepository.save(app5);

        AdoptionApplication app6 = new AdoptionApplication();
        app6.setSubmissionDate(LocalDate.now()); app6.setStatus(ApplicationStatus.NEW); app6.setClient(katarzyna); app6.setAnimal(bela);
        app6.addPreAdoptionForm(55.0, false);
        app6.addPreAdoptionForm(65.0, false);
        applicationRepository.save(app6);
    }
}

package ma.fst.etatdengagement.Services.Impl;

import ma.fst.etatdengagement.Adapter.EmployeeAdapter;
import ma.fst.etatdengagement.DTO.Employee.EmployeeDto;
import ma.fst.etatdengagement.DTO.Employee.SearchEmployeeDto;
import ma.fst.etatdengagement.Models.*;
import ma.fst.etatdengagement.Repository.EmployeRepository;
import ma.fst.etatdengagement.Repository.EtatEngagementRepository;
import ma.fst.etatdengagement.Repository.GradeRepository;
import ma.fst.etatdengagement.Services.EmployeeService;
import ma.fst.etatdengagement.Services.GradeService;
import ma.fst.etatdengagement.Specification.EmployeeSpecification;
import ma.fst.etatdengagement.tools.Constant.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final ModelMapper modelMapper;
    private final EmployeRepository employeeRepository;
    private final EtatEngagementRepository etatEngagementRepository;
    private final GradeRepository gradeRepository;
    private GradeService gradeService;
    private final EmployeeSpecification employeeSpecification;
    private final EmployeeAdapter employeeAdapter;
    @Autowired
    public EmployeeServiceImpl(ModelMapper modelMapper, EmployeRepository employeeRepository, EtatEngagementRepository etatEngagementRepository, GradeRepository gradeRepository, EmployeeSpecification employeeSpecification, EmployeeAdapter employeeAdapter) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
        this.etatEngagementRepository = etatEngagementRepository;
        this.gradeRepository = gradeRepository;
        this.employeeSpecification=employeeSpecification;

        this.employeeAdapter = employeeAdapter;
    }
    @Override
    public Page<EmployeeDto> searchByCriteria(SearchEmployeeDto searchEmployeeDto){
        System.out.println("Page from DTO: " + searchEmployeeDto.getPage() + ", Limit from DTO: " + searchEmployeeDto.getLimit());
        System.out.println("Page: " + searchEmployeeDto.getPage() + ", Limit: " + searchEmployeeDto.getLimit() + ", withPagination: " + searchEmployeeDto.isWithPagination());
        Pageable pageable = Utils.getPageable(
                searchEmployeeDto.isWithPagination(),
                searchEmployeeDto.getPage(),
                searchEmployeeDto.getLimit()
        );

        Page<Employee> employeePage = employeeRepository.findAll(
                employeeSpecification.searchByCriteria(searchEmployeeDto),
                pageable
        );

        return employeePage.map(employeeAdapter::fromEmployeeToEmployeeDTO);
    };

    // 1. Get all employees
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // 2. Get employee by ID
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // 3. Create a new employee
    @Override
    public Employee createEmployee(Employee employee) {
        System.out.println("createEmployeeService we in");
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }

//         Set the employee reference in conjoint if it exists
        if (employee.getConjoint() != null) {
            employee.getConjoint().setEmployee(employee);  // This is important to set the reference back to employee
            System.out.println("conjoint added succecfuly");
        }

        // Set the employee reference in enfants if they exist
        if (employee.getEnfants() != null && !employee.getEnfants().isEmpty()) {
            for (Enfant enfant : employee.getEnfants()) {
                enfant.setEmployee(employee);  // Set each child's employee reference
                System.out.println("enfant added");
            }
        }
        System.out.println("grade id in employee service is "+employee.getGrade().getId());
        Optional<Grade> gradeOpt = gradeRepository.findById(employee.getGrade().getId());
        if (gradeOpt.isPresent()) {
            employee.setGrade(gradeOpt.get());  // Set the full Grade entity to the Employee
        } else {
            throw new IllegalArgumentException("Invalid Grade ID: " + employee.getGrade().getId());
        }

        // Save the employee (this will also cascade and save the conjoint and enfants)
        Employee savedEmployee = employeeRepository.save(employee);

        // Calculate EtatEngagement for the employee
        EtatEngagement etatEngagement = calculateEtatEngagement(savedEmployee);
        etatEngagement.setEmployee(savedEmployee);

        // Ensure the employee has a list initialized for EtatEngagement
        if (savedEmployee.getEtatEngagement() == null) {
            savedEmployee.setEtatEngagement(new ArrayList<>());
        }

        // Add the new EtatEngagement to the list
        savedEmployee.getEtatEngagement().add(etatEngagement);

        // Save the EtatEngagement
        etatEngagementRepository.save(etatEngagement);

        return savedEmployee;
    }


    private EtatEngagement calculateEtatEngagement(Employee employee) {
        EtatEngagement etatEngagement = new EtatEngagement();

        // Get values from employee's grade
        Grade grade = employee.getGrade();


        if (grade == null) {
            throw new IllegalArgumentException("Employee must have an associated grade.");
        }

        double traitementDeBase = grade.getTraitementBase();

        // Handling potential null values for Indemnites and Retenue
        Indemnites indemnites = grade.getIndemnites();
        double indemniteRepresentation = (indemnites != null) ? indemnites.getIndemniteRepresentation() : 0.0;
        double indemniteFonction = (indemnites != null) ? indemnites.getIndemniteFonction() : 0.0;
        double indemniteSujection = (indemnites != null) ? indemnites.getIndemniteSujection() : 0.0;
        double indemniteTournee = (indemnites != null) ? indemnites.getIndemniteTournee() : 0.0;

        Retenue retenue = grade.getRetenue();
        double retenueRcar = (retenue != null) ? retenue.getRCAR() : 0.0;
        double retenueIgr = (retenue != null) ? retenue.getIGR() : 0.0;
        double retenueSM = (retenue != null) ? retenue.getSM() : 0.0;
        double retenuAMO = (retenue != null) ? retenue.getAMO() : 0.0;
        double montantPa = (traitementDeBase * employee.getPa()) / 100;
        double total = traitementDeBase + montantPa;
        double emolumentsBruts = total + indemniteRepresentation + indemniteFonction + indemniteSujection + indemniteTournee;
        double totalRetenues = retenueRcar + retenueIgr + retenuAMO + retenueSM;
        double brutMensuel = emolumentsBruts / 12; // Divide annual by 12 for monthly
        double netAOrdonner = brutMensuel - totalRetenues; // Net to order is monthly brut minus retenues
        etatEngagement.setMontantPA(montantPa);
        etatEngagement.setMontantTotal(total);
        // Set calculated values
        etatEngagement.setEmolumentsBruts(emolumentsBruts);
        etatEngagement.setTotalRetenues(totalRetenues);
        etatEngagement.setBrutMensuel(brutMensuel);
        etatEngagement.setNetAOrdonner(netAOrdonner);

        return etatEngagement;
    }


    // 4. Update an employee
//    @Override
//    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
//        if (!employeeRepository.existsById(id)) {
//            throw new IllegalArgumentException("Employee with ID " + id + " not found.");
//        }
//
//        employeeDto.setEmployeeId(id);
//        Employee updatedEmployee = fromDto(employeeDto);
//        return toDto(employeeRepository.save(updatedEmployee));
//    }

    // 5. Search employees by CIN
    @Override
    public List<Employee> searchEmployeesByCin(String CIN) {
        return employeeRepository.findByCinContainingIgnoreCase(CIN);
    }

    // 6. Search employees by name
    public List<Employee> searchEmployeesByName(String nom) {
        return employeeRepository.findByNomContainingIgnoreCase(nom);
    }

    // 7. Delete an employee
    @Override
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Mapping methods (if not using a dedicated mapper)
//    private EmployeeDto toDto(Employee employee) {
//        if (employee == null) {
//            return null;
//        }
//        EmployeeDto dto = new EmployeeDto();
//        dto.setEmployeeId(employee.getEmployeeId());
//        dto.setCin(employee.getCin());
//        dto.setNom(employee.getNom());
//        dto.setPrenom(employee.getPrenom());
//        // Set other fields as needed
//        return dto;
//    }

//    private Employee fromDto(EmployeeDto employeeDto) {
//        if (employeeDto == null) {
//            return null;
//        }
//        Employee employee = new Employee();
//        employee.setEmployeeId(employeeDto.getEmployeeId());
//        employee.setCin(employeeDto.getCin());
//        employee.setNom(employeeDto.getNom());
//        employee.setPrenom(employeeDto.getPrenom());
//        // Set other fields as needed
//        return employee;
//    }

}
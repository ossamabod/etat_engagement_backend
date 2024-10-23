package ma.fst.etatdengagement.Specification;

import jakarta.persistence.criteria.Predicate;
import ma.fst.etatdengagement.DTO.Employee.EmployeeDto;
import ma.fst.etatdengagement.DTO.Employee.SearchEmployeeDto;
import ma.fst.etatdengagement.Models.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeSpecification {

    public Specification<Employee> searchByCriteria(SearchEmployeeDto employeeDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // Check if employeeDtoId is provided, if so, add it to the query
            if (employeeDto.getEmployeeId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("employeeId"), employeeDto.getEmployeeId()));
            }
            // Check if CIN (identity card) is provided, add a 'like' predicate for it
            if (StringUtils.isNotEmpty(employeeDto.getCin())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("cin")), "%" + employeeDto.getCin().toLowerCase() + "%"));
            }
            // Check if lieuNaissance (place of birth) is provided, add a 'like' predicate for it
//            if (StringUtils.isNotEmpty(employeeDto.getLieuNaissance())) {
//                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("lieuNaissance")), "%" + employeeDto.getLieuNaissance().toLowerCase() + "%"));
//            }

            // Check if prenom (first name) is provided, add a 'like' predicate for it
            if (StringUtils.isNotEmpty(employeeDto.getPrenom())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("prenom")), "%" + employeeDto.getPrenom().toLowerCase() + "%"));
            }
            // Add more criteria as per your model fields (nom, dateNaissance, etc.)
            if (StringUtils.isNotEmpty(employeeDto.getNom())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nom")), "%" + employeeDto.getNom().toLowerCase() + "%"));
            }
//            if (employeeDto.getDateNaissance() != null) {
//                predicates.add(criteriaBuilder.equal(root.get("dateNaissance"), employeeDto.getDateNaissance()));
//            }

//            if (employeeDto.getDateEntree() != null) {
//                predicates.add(criteriaBuilder.equal(root.get("dateEntree"), employeeDto.getDateEntree()));
//            }

//            if (employeeDto.getDateRecrut() != null) {
//                predicates.add(criteriaBuilder.equal(root.get("dateRecrut"), employeeDto.getDateRecrut()));
//            }

//            if (StringUtils.isNotEmpty(employeeDto.getSexe())) {
//                predicates.add(criteriaBuilder.equal(root.get("sexe"), employeeDto.getSexe()));
//            }

            // Add sorting based on employeeDtoId in descending order
            assert query != null;
            query.orderBy(criteriaBuilder.desc(root.get("employeeId")));

            // Return combined predicates
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

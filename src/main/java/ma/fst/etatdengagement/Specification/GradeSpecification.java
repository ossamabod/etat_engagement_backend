package ma.fst.etatdengagement.Specification;

import jakarta.persistence.criteria.Predicate;
import ma.fst.etatdengagement.DTO.Grade.GradeDto;
import ma.fst.etatdengagement.DTO.Grade.SearchGradeDto;
import ma.fst.etatdengagement.Models.Grade;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GradeSpecification {

    public Specification<Grade> searchByCriteria(SearchGradeDto gradeDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // Check if GradeDtoId is provided, if so, add it to the query
            if (gradeDto.getGradeid() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), gradeDto.getGradeid()));
            }
            if (StringUtils.isNotEmpty(gradeDto.getLibelle())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("Libelle")), "%" + gradeDto.getLibelle().toLowerCase() + "%"));
            }
            if (gradeDto.getNiveau() != null) {
                predicates.add(criteriaBuilder.equal(root.get("Niveau"), gradeDto.getNiveau()));
            }


            // Add sorting based on GradeDtoId in descending order
            assert query != null;
            query.orderBy(criteriaBuilder.desc(root.get("id")));

            // Return combined predicates
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

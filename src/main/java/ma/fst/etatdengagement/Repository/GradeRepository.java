package ma.fst.etatdengagement.Repository;

import ma.fst.etatdengagement.Models.Employee;
import ma.fst.etatdengagement.Models.Grade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> , JpaSpecificationExecutor<Grade> {
    Page<Grade> findAll(Pageable pageable);
}
package ma.fst.etatdengagement.Repository;

import ma.fst.etatdengagement.Models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository <Employee,Long>, JpaSpecificationExecutor<Employee> {
        Page<Employee> findAll(Pageable page);
        List<Employee> findByNomContainingIgnoreCase(String nom);
        List<Employee> findByCinContainingIgnoreCase(String CIN);

}

package ma.fst.etatdengagement.Services;

import ma.fst.etatdengagement.DTO.Employee.EmployeeDto;
import ma.fst.etatdengagement.DTO.Employee.SearchEmployeeDto;
import ma.fst.etatdengagement.DTO.Grade.GradeDto;
import ma.fst.etatdengagement.DTO.Grade.SearchGradeDto;
import ma.fst.etatdengagement.Models.Grade;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface GradeService {

    boolean deleteGrade(Long id);
    Page<Grade> getAllGrades();
    Optional<Grade> getGradeById(Long id);
    List<GradeDto> searchByCriteria(SearchGradeDto searchGradeDto);


    Grade createGradeWithDetails(Grade grade);

    Optional<Grade>  updateGradeWithDetails(Long id, Grade grade);

    // Ajoutez d'autres méthodes pour gérer Retenue si nécessaire (update, delete)
}
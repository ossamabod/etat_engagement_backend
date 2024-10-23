package ma.fst.etatdengagement.Conrollers;

import lombok.Data;
import ma.fst.etatdengagement.DTO.Grade.GradeDto;
import ma.fst.etatdengagement.DTO.Grade.SearchGradeDto;
import ma.fst.etatdengagement.DTO.Grade.GradeDto;
import ma.fst.etatdengagement.DTO.Grade.SearchGradeDto;
import ma.fst.etatdengagement.Models.Grade;
import ma.fst.etatdengagement.Models.Retenue;
import ma.fst.etatdengagement.Response.GeneriqueResponse;
import ma.fst.etatdengagement.Services.GradeService;
import ma.fst.etatdengagement.tools.Constant.ErrorConstant;
import ma.fst.etatdengagement.tools.Constant.MessageConstant;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;
    private MessageSource messageSource;
    private ModelMapper modelMapper;

    public GradeController(GradeService gradeService,MessageSource messageSource, ModelMapper modelMapper) {
        this.gradeService = gradeService;
        this.messageSource=messageSource;
        this.modelMapper=modelMapper;
    }
    @GetMapping("getallgrade")
    public ResponseEntity<Grade> getAllGrades() {
        Page<Grade> grades = gradeService.getAllGrades();
        return ResponseEntity.ok((Grade) grades);
    }
    @GetMapping("/getGrade")
    @ResponseBody
    public GeneriqueResponse<List<GradeDto>> SearchByCriteria(SearchGradeDto searchGradeDto){
        try {
            return new GeneriqueResponse<>(messageSource.getMessage(MessageConstant.RESOURCE_FOUND_SUCCESS,null, Locale.FRENCH),
                    gradeService.searchByCriteria(searchGradeDto),
                    null,
                    Boolean.TRUE,
                    HttpStatus.OK.value());
        } catch(Exception e){
            System.out.println(e.getMessage());
            return new GeneriqueResponse<>(messageSource.getMessage(ErrorConstant.ERROR_RESOURCE_NOT_FOND,null, Locale.FRENCH),
                    null,
                    null,
                    Boolean.FALSE,
                    HttpStatus.NOT_FOUND.value());
        }

    }
    // Get Grade by ID
    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable Long id) {

        Optional<Grade> grade = gradeService.getGradeById(id);
        return grade.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create Grade with Indemnites and Retenue
    @PostMapping
    public ResponseEntity<Grade> createGrade(@RequestBody Grade grade) {
        Grade createdGrade = gradeService.createGradeWithDetails(grade);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGrade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable("id") Long id, @RequestBody Grade grade) {
        return gradeService.updateGradeWithDetails(id, grade)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Other methods...

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable("id") Long id) {
        return gradeService.deleteGrade(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

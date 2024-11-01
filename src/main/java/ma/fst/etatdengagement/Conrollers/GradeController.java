package ma.fst.etatdengagement.Conrollers;

import lombok.Data;
import ma.fst.etatdengagement.DTO.Grade.GradeDto;
import ma.fst.etatdengagement.DTO.Grade.SearchGradeDto;
import ma.fst.etatdengagement.Models.Grade;
import ma.fst.etatdengagement.Services.GradeService;
import ma.fst.etatdengagement.Specification.GradeSpecification;
import ma.fst.etatdengagement.tools.Constant.ErrorConstant;
import ma.fst.etatdengagement.tools.Constant.MessageConstant;
import ma.fst.etatdengagement.Response.GeneriqueResponse;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@Data
@RequestMapping("/api/grades")
@RestController
public class GradeController {

    private final GradeService gradeService;
    private MessageSource messageSource;
    private GradeSpecification gradeSpecification;
    private ModelMapper modelMapper;
    @Autowired
    GradeController(GradeService gradeService,MessageSource messageSource,ModelMapper modelMapper){
        this.messageSource=messageSource;
        this.gradeService=gradeService;
        this.modelMapper=modelMapper;

    }
    @GetMapping("/getGrade")
    @ResponseBody
    public GeneriqueResponse<List<GradeDto>> SearchByCriteria(SearchGradeDto searchgradeDto){
        try {
                      return new GeneriqueResponse<>(messageSource.getMessage(MessageConstant.RESOURCE_FOUND_SUCCESS,null, Locale.FRENCH),
                    gradeService.searchByCriteria(searchgradeDto),
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
    @GetMapping("/getGrade/{id}")
    @ResponseBody
    public GeneriqueResponse<List<GradeDto>> SearchById(@PathVariable("id") long id){
        try {
            SearchGradeDto searchgradeDto= new SearchGradeDto(id, null, null, null, 0);
            return new GeneriqueResponse<>(messageSource.getMessage(MessageConstant.RESOURCE_FOUND_SUCCESS,null, Locale.FRENCH),
                    gradeService.searchByCriteria(searchgradeDto),
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


    @PostMapping
    public GeneriqueResponse<Grade> creategrade(@RequestBody Grade grade) {
        try {
            return new GeneriqueResponse<>(messageSource.getMessage(MessageConstant.RESOURCE_CREATED_SUCCESS, null, Locale.FRENCH),
                    gradeService.createGradeWithDetails(grade),
                    null,
                    Boolean.TRUE,
                    HttpStatus.OK.value());
        } catch(Exception e){
            System.out.println(e);
            return new GeneriqueResponse<>(messageSource.getMessage(ErrorConstant.ERROR_RESOURCE_NOT_CREATED,null, Locale.FRENCH),
                    null,
                    null,
                    Boolean.FALSE,
                    HttpStatus.NOT_IMPLEMENTED.value());
        }
    }
    @PutMapping("/{id}")
    public GeneriqueResponse<Optional<Grade>> updategrade(@PathVariable Long id,@RequestBody Grade grade){
        try {
            return new GeneriqueResponse<>(messageSource.getMessage(MessageConstant.RESOURCE_UPDATE_SUCCESS,null,Locale.FRENCH),gradeService.updateGradeWithDetails(id,grade),null,Boolean.TRUE,HttpStatus.OK.value());

        }catch (Exception e){
            return new GeneriqueResponse<>(messageSource.getMessage(ErrorConstant.ERROR_RESOURCE_NOT_UPDATED,null,Locale.FRENCH),null,null,Boolean.FALSE,HttpStatus.NOT_IMPLEMENTED.value());

        }

    }
//
//    @GetMapping("/Search")
//    public ResponseEntity<List<grade>> searchgradesByCin(@RequestParam(value = "cin", required = false) String cin) {
//        List<grade> grades;
//        // Add a check for "undefined" or other invalid strings
//        if (cin == null || cin.isEmpty() || "undefined".equals(cin)) {
//            grades = gradeService.getAllgrades();
//        } else {
//            grades = gradeService.searchgradesByCin(cin);
//        }
//        return ResponseEntity.ok(grades);
//    }
//
////    // Add this method to return an grade by their ID
////    @GetMapping("/{id}")
////    public ResponseEntity<grade> getgradeById(@PathVariable("id") Long id) {
////        grade grade = gradeService.getgradeById(id);
////        if (grade != null) {
////            return ResponseEntity.ok(grade);
////        } else {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
////        }
////    }

    @DeleteMapping
    public GeneriqueResponse<Boolean> deletegrade(@PathVariable Long id){
        try {
            gradeService.deleteGrade(Long.valueOf(id));
            return new GeneriqueResponse<>(messageSource.getMessage(MessageConstant.RESOURCE_DELETED_SUCCESS, null, Locale.FRENCH), Boolean.TRUE, null, Boolean.TRUE, HttpStatus.OK.value());
        } catch (Exception e) {
            return new GeneriqueResponse<>(messageSource.getMessage(ErrorConstant.ERROR_RESOURCE_NOT_DELETED,
                    null, Locale.FRENCH), null, null, Boolean.FALSE, HttpStatus.NO_CONTENT.value());
        }
    }
}

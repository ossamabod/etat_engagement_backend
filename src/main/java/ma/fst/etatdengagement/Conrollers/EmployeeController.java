package ma.fst.etatdengagement.Conrollers;

import lombok.Data;
import ma.fst.etatdengagement.DTO.Employee.EmployeeDto;
import ma.fst.etatdengagement.DTO.Employee.SearchEmployeeDto;
import ma.fst.etatdengagement.Services.EmployeeService;
import ma.fst.etatdengagement.Specification.EmployeeSpecification;
import ma.fst.etatdengagement.tools.Constant.ErrorConstant;
import ma.fst.etatdengagement.tools.Constant.MessageConstant;
import ma.fst.etatdengagement.Models.Employee;
import ma.fst.etatdengagement.Response.GeneriqueResponse;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@Data
@RequestMapping("/Employee")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;
    private MessageSource messageSource;
    private EmployeeSpecification employeeSpecification;
    private ModelMapper modelMapper;
    @Autowired
    EmployeeController(EmployeeService employeeService,MessageSource messageSource,ModelMapper modelMapper){
        this.messageSource=messageSource;
        this.employeeService=employeeService;
        this.modelMapper=modelMapper;

    }
    @GetMapping("/searchbycriteria")
    @ResponseBody
    public GeneriqueResponse<Page<EmployeeDto>> SearchByCriteria(SearchEmployeeDto searchEmployeeDto){
        try {
            Page<EmployeeDto> pageEmployee=employeeService.searchByCriteria(searchEmployeeDto);
            Page<EmployeeDto> pageDtoEmployee=pageEmployee.map(entity->modelMapper.map(entity, EmployeeDto.class));
            return new GeneriqueResponse<>(messageSource.getMessage(MessageConstant.RESOURCE_FOUND_SUCCESS,null, Locale.FRENCH),
                    pageDtoEmployee,
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

    @GetMapping("/{id}")
    public GeneriqueResponse<Page<EmployeeDto>> SearchById(@PathVariable("id") Long id ){
        try {
            SearchEmployeeDto searchEmployeeDto=new SearchEmployeeDto(id,null,null,null);
            Page<EmployeeDto> pageEmployee=employeeService.searchByCriteria(searchEmployeeDto);
            Page<EmployeeDto> pageDtoEmployee=pageEmployee.map(entity->modelMapper.map(entity, EmployeeDto.class));
            return new GeneriqueResponse<>(messageSource.getMessage(MessageConstant.RESOURCE_FOUND_SUCCESS,null, Locale.FRENCH),
                    pageDtoEmployee,
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
    public GeneriqueResponse<Employee> createEmployee(@RequestBody Employee employee) {
     try {
         return new GeneriqueResponse<>(messageSource.getMessage(MessageConstant.RESOURCE_CREATED_SUCCESS, null, Locale.FRENCH),
                 employeeService.createEmployee(employee),
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
        public GeneriqueResponse<Optional<EmployeeDto>> updateEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto){
            System.out.println("id"+id);
        try {
            System.out.println("employeeDto = "+employeeDto);

            return new GeneriqueResponse<>(messageSource.getMessage(MessageConstant.RESOURCE_UPDATE_SUCCESS,null,Locale.FRENCH),employeeService.updateEmployee(id,employeeDto),null,Boolean.TRUE,HttpStatus.OK.value());
        }catch (Exception e){
            System.out.println("error is"+ e);
            return new GeneriqueResponse<>(messageSource.getMessage(ErrorConstant.ERROR_RESOURCE_NOT_UPDATED,null,Locale.FRENCH),null,null,Boolean.FALSE,HttpStatus.NOT_IMPLEMENTED.value());
        }
        }
//
//    @GetMapping("/Search")
//    public ResponseEntity<List<Employee>> searchEmployeesByCin(@RequestParam(value = "cin", required = false) String cin) {
//        List<Employee> employees;
//        // Add a check for "undefined" or other invalid strings
//        if (cin == null || cin.isEmpty() || "undefined".equals(cin)) {
//            employees = employeeService.getAllEmployees();
//        } else {
//            employees = employeeService.searchEmployeesByCin(cin);
//        }
//        return ResponseEntity.ok(employees);
//    }
//
////    // Add this method to return an employee by their ID
////    @GetMapping("/{id}")
////    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
////        Employee employee = employeeService.getEmployeeById(id);
////        if (employee != null) {
////            return ResponseEntity.ok(employee);
////        } else {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
////        }
////    }

    @DeleteMapping
    public GeneriqueResponse<Boolean> deleteEmployee(@PathVariable Long id){
        try {
            employeeService.deleteEmployee(Long.valueOf(id));
            return new GeneriqueResponse<>(messageSource.getMessage(MessageConstant.RESOURCE_DELETED_SUCCESS, null, Locale.FRENCH), Boolean.TRUE, null, Boolean.TRUE, HttpStatus.OK.value());
        } catch (Exception e) {
            return new GeneriqueResponse<>(messageSource.getMessage(ErrorConstant.ERROR_RESOURCE_NOT_DELETED,
                    null, Locale.FRENCH), null, null, Boolean.FALSE, HttpStatus.NO_CONTENT.value());
        }
    }
}

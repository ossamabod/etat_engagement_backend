package ma.fst.etatdengagement.Services;

import ma.fst.etatdengagement.DTO.Employee.EmployeeDto;
import ma.fst.etatdengagement.DTO.Employee.SearchEmployeeDto;
import ma.fst.etatdengagement.Models.Employee;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public List<Employee> getAllEmployees();
    public Employee getEmployeeById(Long id);
    public Employee createEmployee(Employee employee);
    Page<EmployeeDto> searchByCriteria(SearchEmployeeDto searchEmployeeDto);
    Optional<EmployeeDto> updateEmployee(long id, EmployeeDto employeedto);
    //    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
    public List<Employee> searchEmployeesByCin(String CIN);
    public void deleteEmployee(Long id);


}

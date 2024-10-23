package ma.fst.etatdengagement.Services;

import ma.fst.etatdengagement.DTO.Employee.EmployeeDto;
import ma.fst.etatdengagement.DTO.Employee.SearchEmployeeDto;
import ma.fst.etatdengagement.Models.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();
    public Employee getEmployeeById(Long id);
    public Employee createEmployee(Employee employee);
    Page<EmployeeDto> searchByCriteria(SearchEmployeeDto searchEmployeeDto);


    //    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
    public List<Employee> searchEmployeesByCin(String CIN);
    public boolean deleteEmployee(Long id);


}

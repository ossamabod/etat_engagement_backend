package ma.fst.etatdengagement.Mapper;

import ma.fst.etatdengagement.DTO.Employee.EmployeeDto;
import ma.fst.etatdengagement.Models.Employee;
import org.mapstruct.*;
@Mapper(componentModel = "spring",  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
            @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
            void updateEmployeeFromDto(EmployeeDto dto, @MappingTarget Employee entity);
        }




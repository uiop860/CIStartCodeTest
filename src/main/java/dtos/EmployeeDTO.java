/*
 *  #CleanCode
 */
package dtos;

import entities.Employee;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author olive
 */
public class EmployeeDTO {

    private Long id;
    private String name;
    private String address;

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.address = employee.getAddress();
    }
    
    public static List<EmployeeDTO> getDTO(List<Employee> employees){
        List<EmployeeDTO> employeesDTO = new ArrayList<>();
        employees.forEach(e->employeesDTO.add(new EmployeeDTO(e)));
        return employeesDTO; 
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

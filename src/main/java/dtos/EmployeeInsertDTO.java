/*
 *  #CleanCode
 */
package dtos;

/**
 *
 * @author olive
 */
public class EmployeeInsertDTO {
    
    String name;
    String address;
    Long salary;

    public EmployeeInsertDTO(String name, String address, Long salary) {
        this.name = name;
        this.address = address;
        this.salary = salary;
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

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
    
}

/*
 *  #CleanCode
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EmployeeDTO;
import dtos.EmployeeInsertDTO;
import facades.EmployeeFacade;
import facades.FacadeExample;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

//Todo Remove or change relevant parts before ACTUAL use
@Path("emp")
public class EmployeeResource {

    private final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    EmployeeFacade ef = EmployeeFacade.getEmployeeFacade(EMF);

    @Path("byid/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmpByID(@PathParam("id") long id) {
        
        EmployeeDTO employeeDTO = new EmployeeDTO(ef.getEmployeeById(id));
        return new Gson().toJson(employeeDTO);
    }
    
    @Path("byname/{name}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmpByNames(@PathParam("name") String name){
        List<EmployeeDTO> employeesDTO = EmployeeDTO.getDTO(ef.getEmployeesByName(name));
        return new Gson().toJson(employeesDTO);
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllEmp(){
        List<EmployeeDTO> employeesDTO = EmployeeDTO.getDTO(ef.getAllEmployees());
        return new Gson().toJson(employeesDTO);
    }
    
    @Path("higestsalary")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmpWithHigestSalary(){
        List<EmployeeDTO> employeesDTO = EmployeeDTO.getDTO(ef.getEmployeesWithHigestSalary());
        return new Gson().toJson(employeesDTO);
    }
    
    @Path("create/{name}&{address}&{salary}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String createEmp(@PathParam("name") String name, 
                            @PathParam("address") String address, 
                            @PathParam("salary") Long salary){
        EmployeeInsertDTO employeeDTO = new EmployeeInsertDTO(name,address,salary);
        ef.createEmployee(employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getSalary());
        return new Gson().toJson(employeeDTO);
    } 
    
}

package invex.service.employees.actions;

import java.util.List;

import invex.models.entities.EmployeesInput;
import invex.models.entities.orm.Employee;
import invex.models.requests.employee.post.PostEmployeeResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class GetEmployees {
    
    public PostEmployeeResponse getEmployees(EntityManager em) 
    {
        PostEmployeeResponse response = new PostEmployeeResponse();
        List<Employee> employees = em.createNamedQuery("Employee.findAll", Employee.class)
                .getResultList();

        List<EmployeesInput> employeesInputs = employees.stream()
                .map(emp -> {
                    EmployeesInput input = new EmployeesInput();
                    input.setEmployeeId(emp.getId());
                    input.setFirstName(emp.getFirstName());
                    input.setSecondName(emp.getSecondName());
                    input.setLastName(emp.getLastName());
                    input.setSecondLastName(emp.getSecondLastName());
                    input.setAge(emp.getAge());
                    input.setSex(emp.getSex());
                    input.setBirth(emp.getBirth());
                    input.setPosition(emp.getPosition());
                    input.setKind(emp.getKind());
                    return input;
                }).toList();
        
        response.setEmployees(employeesInputs);
        response.setStatus(200L);
        response.setSuccess(true);
        response.setMessage(List.of("Get Employees successful"));
        if (employees.isEmpty()) {
            response.setMessage(List.of("No employees found"));
            response.setStatus(404L);
            response.setSuccess(false);
        }
        return response;
    }
}

package invex.service.employees.actions;

import java.util.List;

import invex.models.entities.EmployeesInput;
import invex.models.entities.orm.Employee;
import invex.models.requests.employee.post.PostEmployeeRequest;
import invex.models.requests.employee.post.PostEmployeeResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class PostEmployees {

  
  public PostEmployeeResponse postEmployees(EntityManager em, PostEmployeeRequest request) {
    for(EmployeesInput employee : request.getEmployees()) {
      Employee emp = new Employee();
      emp.setFirstName(employee.getFirstName());
      emp.setSecondName(employee.getSecondName());
      emp.setLastName(employee.getLastName());
      emp.setSecondLastName(employee.getSecondLastName());
      emp.setAge(employee.getAge());
      emp.setSex(employee.getSex());
      emp.setBirth(employee.getBirth());
      emp.setPosition(employee.getPosition());
      emp.setKind(employee.getKind());
      em.persist(emp);
      employee.setEmployeeId(emp.getId());
      }
    PostEmployeeResponse response = new PostEmployeeResponse();
    response.setMessage(List.of("Employee(s) created successfully"));
    response.setStatus(200L);
    response.setSuccess(true);
    response.setEmployees(request.getEmployees());
    return response;

    }
  }



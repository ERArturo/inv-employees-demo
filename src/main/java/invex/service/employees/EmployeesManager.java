package invex.service.employees;

import invex.models.entities.EmployeesInput;
import invex.models.requests.employee.delete.DeleteEmployeeResponse;
import invex.models.requests.employee.post.PostEmployeeRequest;
import invex.models.requests.employee.post.PostEmployeeResponse;
import invex.models.requests.employee.put.PutEmployeeResponse;
import invex.service.employees.actions.DeleteEmployee;
import invex.service.employees.actions.GetEmployees;
import invex.service.employees.actions.PostEmployees;
import invex.service.employees.actions.PutEmployee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EmployeesManager {

  @Inject
  EntityManager em;
  @Inject
  public PostEmployees postEmployeesAction;
  @Inject
  GetEmployees getEmployeesAction;
  @Inject
  DeleteEmployee deleteEmployeeAction;
  @Inject
  PutEmployee putEmployeeAction;

  @Transactional
  public PostEmployeeResponse postEmployees(PostEmployeeRequest request) {
    return postEmployeesAction.postEmployees(em, request);
  }

  @Transactional
  public PostEmployeeResponse getEmployees() {
    return getEmployeesAction.getEmployees(em);
  }

  @Transactional
  public DeleteEmployeeResponse deleteEmployee(Long id) {
    return deleteEmployeeAction.deleteEmployee(em, id);
  }
  
  @Transactional
  public PutEmployeeResponse putEmployee(EmployeesInput employeesInput){
    return putEmployeeAction.putEmployee(em, employeesInput);

  }
}

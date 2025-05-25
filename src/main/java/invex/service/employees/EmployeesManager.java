package invex.service.employees;

import invex.models.requests.employee.post.PostEmployeeRequest;
import invex.models.requests.employee.post.PostEmployeeResponse;
import invex.service.employees.actions.GetEmployees;
import invex.service.employees.actions.PostEmployees;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EmployeesManager {

  @Inject
  EntityManager em;
  @Inject
  PostEmployees postEmployeesAction;
  @Inject
  GetEmployees getEmployeesAction;

  @Transactional
  public PostEmployeeResponse postEmployees(PostEmployeeRequest request) {
    return postEmployeesAction.postEmployees(em, request);
  }

  @Transactional
  public PostEmployeeResponse getEmployees() {
    return getEmployeesAction.getEmployees(em);
  } 
}

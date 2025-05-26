package invex.service.employees.actions;

import java.util.List;

import invex.models.constants.Queries;
import invex.models.requests.employee.delete.DeleteEmployeeResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class DeleteEmployee {
  public DeleteEmployeeResponse deleteEmployee(EntityManager em, Long id){
    DeleteEmployeeResponse response = new DeleteEmployeeResponse();
    try {
      em.createNamedQuery(Queries.EMPLOYEE_DELETE_BY_ID)
          .setParameter("id", id)
          .executeUpdate();
      response.setMessages(List.of("Employee deleted successfully"));
      response.setStatus(200L);
      response.setSuccess(true);
    } catch (Exception e) {
      response.setMessages(List.of("Error deleting employee: " + e.getMessage()));
      response.setStatus(500L);
      response.setSuccess(false);
    }
    return response;
  }
}

package invex.service.employees.actions;

import java.util.List;

import org.jboss.logging.Logger;

import invex.models.constants.Queries;
import invex.models.entities.EmployeesInput;
import invex.models.requests.employee.put.PutEmployeeResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class PutEmployee {
  Logger logger = Logger.getLogger(PutEmployee.class.getName());
  
  public PutEmployeeResponse putEmployee(EntityManager em, EmployeesInput employeesInput) {
    PutEmployeeResponse response = new PutEmployeeResponse();
    try {
      em.createNamedQuery(Queries.EMPLOYEE_UPDATE_BY_ID)
          .setParameter("firstName", employeesInput.getFirstName())
          .setParameter("secondName", employeesInput.getSecondName())
          .setParameter("lastName", employeesInput.getLastName())
          .setParameter("secondLastName", employeesInput.getSecondLastName())
          .setParameter("age", employeesInput.getAge())
          .setParameter("sex", employeesInput.getSex())
          .setParameter("birth", employeesInput.getBirth())
          .setParameter("position", employeesInput.getPosition())
          .setParameter("id", employeesInput.getEmployeeId())
          .executeUpdate();
      response.setMessages(List.of("Employee updated successfully"));
      response.setStatus(200L);
      response.setSuccess(true);
      logger.info("Employee updated successfully: " + employeesInput.getEmployeeId());

    } catch (Exception e) {
      response.setMessages(List.of("Error updating employee: " + e.getMessage()));
      response.setStatus(500L);
      response.setSuccess(false);
      logger.error("Error updating employee", e);
      return response;
    }
    return response;
  }
}

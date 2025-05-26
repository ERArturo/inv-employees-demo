package invex.models.requests.employee.put;

import java.util.List;

import invex.models.entities.EmployeesInput;
import invex.models.requests.abstracts.AbstractResponse;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PutEmployeeResponse implements AbstractResponse {
  private List<String> messages;
  private Long status;
  private Boolean success;
  private EmployeesInput employee;

  public List<String> getMessages() {
    return messages;
  }

  public void setMessages(List<String> messages) {
    this.messages = messages;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public EmployeesInput getEmployee() {
    return employee;
  }

  public void setEmployee(EmployeesInput employee) {
    this.employee = employee;
  }

}

package invex.models.requests.employee.post;

import java.util.List;

import invex.models.entities.EmployeesInput;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostEmployeeResponse {
    private List<String> messages;
    private Long status;
    private Boolean success;
    private List<EmployeesInput> employees;

    public List<String> getMessage() {
        return messages;
    }

    public void setMessage(List<String> messages) {
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

    public List<EmployeesInput> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeesInput> employees) {
        this.employees = employees;
    }

}

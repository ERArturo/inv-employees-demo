package invex.models.requests.employee.post;

import java.util.List;

import invex.models.entities.Employees;

public class PostEmployeeResponse {
    private String message;
    private Long status;
    private Boolean success;
    private List<Employees> employees;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public List<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
    }

}

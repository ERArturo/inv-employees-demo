package invex.models.requests.employee.post;

import java.util.List;

import invex.models.entities.EmployeesInput;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

@ApplicationScoped
public class PostEmployeeRequest {
    @Valid
    @Size(min = 1, message = "At least one employee is required")
    List<EmployeesInput> employees;

    public List<EmployeesInput> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeesInput> employees) {
        this.employees = employees;
    }
}

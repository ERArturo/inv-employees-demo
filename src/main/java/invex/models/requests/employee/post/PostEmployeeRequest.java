package invex.models.requests.employee.post;

import java.util.List;

import invex.models.entities.Employees;

public class PostEmployeeRequest {
    List<Employees> employees;

    public List<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
    }
}

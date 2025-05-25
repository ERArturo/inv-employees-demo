package invex.service.employees;

import java.util.List;

import invex.models.requests.employee.post.PostEmployeeRequest;
import invex.models.requests.employee.post.PostEmployeeResponse;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeesManager {

  public Uni<PostEmployeeResponse> postEmployees(PostEmployeeRequest request) {
    PostEmployeeResponse response = new PostEmployeeResponse();
    response.setMessage(List.of("Employee(s) created successfully"));
    response.setStatus(200L);
    response.setSuccess(true);
    response.setEmployees(request.getEmployees());
    return Uni.createFrom().item(response);
  }
}

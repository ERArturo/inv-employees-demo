package invex.resources;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import invex.models.requests.employee.post.PostEmployeeRequest;
import invex.models.requests.employee.post.PostEmployeeResponse;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Employees {
  Logger logger = Logger.getLogger(Employees.class.getName());

  @POST
  public Uni<PostEmployeeResponse> createEmployee(String employee) {
    PostEmployeeRequest employeeRequest = new PostEmployeeRequest();

    ObjectMapper objectMapper = new ObjectMapper();
    PostEmployeeResponse response = new PostEmployeeResponse();
    response.setMessage("Employee(s) created successfully");
    response.setStatus(200L);
    response.setSuccess(true);
    try {
      employeeRequest = objectMapper.readValue(employee, PostEmployeeRequest.class);
      response.setEmployees(employeeRequest.getEmployees());
      logger
          .info("Employee(s) created successfully" + employeeRequest.getEmployees().get(0).getFirstName());
    } catch (Exception e) {
      response.setMessage("Error creating employee");
      response.setStatus(500L);
      response.setSuccess(false);
      logger.error("Error creating employee", e);
    }
    return Uni.createFrom().item(response);
  }

}

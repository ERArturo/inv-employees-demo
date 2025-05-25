package invex.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;

import invex.models.requests.employee.post.PostEmployeeRequest;
import invex.models.requests.employee.post.PostEmployeeResponse;
import invex.service.employees.EmployeesManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class Employees {
  Logger logger = Logger.getLogger(Employees.class.getName());

  @Inject
  EmployeesManager manager;

  @POST
  public PostEmployeeResponse createEmployee(PostEmployeeRequest employee) {

    PostEmployeeResponse response = new PostEmployeeResponse();
    List<String> messages = validate(employee);
    if (!messages.isEmpty()) {
      response.setMessage(messages);
      response.setStatus(400L);
      response.setSuccess(false);
      logger.error("Validation errors: " + messages);
      return response;
    }
    try {
      return manager.postEmployees(employee);
    } catch (Exception e) {
      messages.clear();
      messages.add("Error creating employee(s)  " + e.getMessage());
      response.setMessage(messages);
      response.setStatus(500L);
      response.setSuccess(false);
      logger.error("Error creating employee", e);
    }
    return response;
  }

  @GET
  public PostEmployeeResponse getEmployees(){
    try {
      return manager.getEmployees();
    } catch (Exception e) {
      PostEmployeeResponse response = new PostEmployeeResponse();
      response.setMessage(List.of("Error fetching employees: " + e.getMessage()));
      response.setStatus(500L);
      response.setSuccess(false);
      logger.error("Error fetching employees", e);
      return response;
    }
  }

  private List<String> validate(PostEmployeeRequest project) {
    if (project == null) {
      return List.of("Request body cannot be null");
    } else if (project.getEmployees() == null || project.getEmployees().isEmpty()) {
      return List.of("At least one employee is required");
    }
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    return validator.validate(project)
        .stream()
        .map(ConstraintViolation::getMessage)
        .collect(Collectors.toList());
  }

}

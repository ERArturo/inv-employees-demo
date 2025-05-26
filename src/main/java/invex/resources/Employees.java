package invex.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;

import invex.models.entities.EmployeesInput;
import invex.models.requests.employee.delete.DeleteEmployeeResponse;
import invex.models.requests.employee.post.PostEmployeeRequest;
import invex.models.requests.employee.post.PostEmployeeResponse;
import invex.models.requests.employee.put.PutEmployeeResponse;
import invex.service.employees.EmployeesManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
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
      response.setMessages(messages);
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
      response.setMessages(messages);
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
      response.setMessages(List.of("Error fetching employees: " + e.getMessage()));
      response.setStatus(500L);
      response.setSuccess(false);
      logger.error("Error fetching employees", e);
      return response;
    }
  }

  @DELETE
  @Path("/{id}")
  public DeleteEmployeeResponse deleteEmployees(Long id) {
    try {
      return manager.deleteEmployee(id);
    } catch (Exception e) {
      DeleteEmployeeResponse response = new DeleteEmployeeResponse();
      response.setMessages(List.of("Error deleting employees: " + e.getMessage()));
      response.setStatus(500L);
      response.setSuccess(false);
      logger.error("Error deleting employees", e);
      return response;
    }
  }

  @PUT
  public PutEmployeeResponse updateEmployee(EmployeesInput employee) {

    PutEmployeeResponse response = new PutEmployeeResponse();
    List<String> messages = validate(employee);
    if (!messages.isEmpty()) {
      response.setMessages(messages);
      response.setStatus(400L);
      response.setSuccess(false);
      logger.error("Validation errors: " + messages);
      return response;
    }
    try {
      return manager.putEmployee(employee);
    } catch (Exception e) {
      messages.clear();
      messages.add("Error creating employee(s)  " + e.getMessage());
      response.setMessages(messages);
      response.setStatus(500L);
      response.setSuccess(false);
      logger.error("Error creating employee", e);
    }
    return response;
  }

  private List<String> validate(Object obj) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    return validator.validate(obj)
        .stream()
        .map(ConstraintViolation::getMessage)
        .collect(Collectors.toList());
  }

}

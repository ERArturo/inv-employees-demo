package invex.resources;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import invex.models.requests.employee.post.PostEmployeeRequest;
import invex.models.requests.employee.post.PostEmployeeResponse;
import invex.service.employees.EmployeesManager;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;

class EmployeesTest {

    private Employees employees;
    private EmployeesManager manager;

    @BeforeEach
    void setUp() {
        employees = new Employees();
        manager = mock(EmployeesManager.class);
        employees.manager = manager;
    }

    @Test
    void createEmployee_shouldReturn400_whenRequestIsNull() {
        Uni<PostEmployeeResponse> result = employees.createEmployee(null);
        PostEmployeeResponse response = result.await().indefinitely();
        assertEquals(400L, response.getStatus());
        assertFalse(response.getSuccess());
        assertTrue(response.getMessage().contains("Request body cannot be null"));
    }

    @Test
    void createEmployee_shouldReturn400_whenEmployeesListIsEmpty() {
        PostEmployeeRequest req = mock(PostEmployeeRequest.class);
        when(req.getEmployees()).thenReturn(Collections.emptyList());

        Uni<PostEmployeeResponse> result = employees.createEmployee(req);
        PostEmployeeResponse response = result.await().indefinitely();
        assertEquals(400L, response.getStatus());
        assertFalse(response.getSuccess());
        assertTrue(response.getMessage().contains("At least one employee is required"));
    }

    @Test
    void testCreateEmployee_Success() throws Exception {
        // Arrange
        invex.models.entities.Employees emp = new invex.models.entities.Employees();
        emp.setFirstName("John");
        emp.setLastName("Doe");
        emp.setAge(30L);
        emp.setSex("M");
        emp.setBirth("09-09-1994"); // Valid birth date
        emp.setPosition("Developer");
        emp.setKind("employee"); // Assuming kind is a required field
        emp.setEmployeeId(0L); // Assuming employeeId is required and valid

        List<invex.models.entities.Employees> empList = Collections.singletonList(emp);
        PostEmployeeRequest req = new PostEmployeeRequest();
        req.setEmployees(empList);

        // Act
        Uni<PostEmployeeResponse> uniResponse = employees.createEmployee(req);
        PostEmployeeResponse response = uniResponse.await().indefinitely();

        // Assert
        assertTrue(response.getSuccess());
        assertEquals(200L, response.getStatus());
        assertEquals("Employee(s) created successfully", response.getMessage().get(0));
        assertNotNull(response.getEmployees());
        assertEquals("John", response.getEmployees().get(0).getFirstName());
    }

    @Test
    void testCreateEmployee_InvalidFields() {
        // Arrange
        invex.models.entities.Employees emp = new invex.models.entities.Employees();
        emp.setEmployeeId(null); // Invalid ID
        emp.setFirstName("John2");// Invalid first name
        emp.setLastName("Doe");
        emp.setBirth("1994-09-09"); // Invalid birth date
        emp.setSex("A"); // Invalid sex type
        emp.setPosition(null); // Invalid position
        emp.setAge(-5L); // Invalid age
        List<invex.models.entities.Employees> empList = Collections.singletonList(emp);
        PostEmployeeRequest req = new PostEmployeeRequest();
        req.setEmployees(empList);

        // Act
        Uni<PostEmployeeResponse> uniResponse = employees.createEmployee(req);
        PostEmployeeResponse response = uniResponse.await().indefinitely();

        // Assert
        assertFalse(response.getSuccess());
        assertEquals(400L, response.getStatus());
        assertEquals(7, response.getMessage().size());
        assertTrue(response.getMessage().contains("Employee ID cannot be null"));
        assertTrue(response.getMessage().contains("Birth date must be in the format DD-MM-YYYY"));
        assertTrue(response.getMessage().contains("Birth date must be a valid date"));
        assertTrue(response.getMessage().contains("First name must contain only letters"));
        assertTrue(response.getMessage().contains("Sex must be M, F, or O"));
        assertTrue(response.getMessage().contains("Position cannot be null"));
        assertTrue(response.getMessage().contains("Age must be a positive number"));
    }

}
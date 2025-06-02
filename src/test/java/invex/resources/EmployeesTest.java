package invex.resources;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import invex.models.entities.EmployeesInput;
import invex.models.requests.employee.post.PostEmployeeRequest;
import invex.models.requests.employee.post.PostEmployeeResponse;
import invex.service.employees.EmployeesManager;

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
    void CreateEmployeeSuccess() throws Exception {
       // Arrange
        EmployeesInput emp = new EmployeesInput();
        emp.setEmployeeId(0L); 
        emp.setFirstName("John");
        emp.setSecondName("Escobedo");
        emp.setLastName("Doe");
        emp.setSecondLastName("Smith");
        emp.setAge(30L);
        emp.setSex("M");
        emp.setBirth("09-09-1994"); 
        emp.setPosition("Developer");
        emp.setKind("employee"); 
        
        List<EmployeesInput> empList = Collections.singletonList(emp);
        PostEmployeeRequest req = new PostEmployeeRequest();
        req.setEmployees(empList);
        PostEmployeeResponse result = new PostEmployeeResponse();
        result.setSuccess(true);
        result.setStatus(200L);
        result.setMessages(List.of("Employee(s) created successfully"));
        result.setEmployees(empList);
        when(manager.postEmployees(any(PostEmployeeRequest.class))).thenReturn(result);

        // Act
        result = employees.createEmployee(req);

        // Assert
        assertTrue(result.getSuccess());
        assertEquals(200L, result.getStatus());
        assertNotNull(result.getMessages());
        assertEquals("Employee(s) created successfully", result.getMessages().get(0));
        assertNotNull(result.getEmployees());
        assertEquals(1, result.getEmployees().size());
        assertEquals("John", result.getEmployees().get(0).getFirstName());
    }

    @Test
    void createEmployeeTestFieldConditions() {
        EmployeesInput emp = new EmployeesInput();
        emp.setEmployeeId(null); // Invalid ID
        emp.setFirstName("John2");// Invalid first name
        emp.setLastName("Doe");
        emp.setBirth("1994-09-09"); // Invalid birth date
        emp.setSex("A"); // Invalid sex type
        emp.setPosition(null); // Invalid position
        emp.setAge(-5L); // Invalid age
        List<EmployeesInput> empList = Collections.singletonList(emp);
        PostEmployeeRequest req = new PostEmployeeRequest();
        req.setEmployees(empList);
        PostEmployeeResponse result = employees.createEmployee(req);

        assertFalse(result.getSuccess());
        assertEquals(400L, result.getStatus());
        assertEquals(9, result.getMessages().size());
        assertTrue(result.getMessages().contains("Position cannot be null"));
        assertTrue(result.getMessages().contains("Second Last name cannot be null"));
        assertTrue(result.getMessages().contains("Sex must be M, F, O or Empty"));
        assertTrue(result.getMessages().contains("Birth date must be in the format DD-MM-YYYY"));
        assertTrue(result.getMessages().contains("Second name cannot be null"));
        assertTrue(result.getMessages().contains("First name must contain only letters"));
        assertTrue(result.getMessages().contains("Birth date must be a valid date"));
        assertTrue(result.getMessages().contains("Employee ID cannot be null"));
        assertTrue(result.getMessages().contains("Age must be a positive number"));
    }

    @Test
    void getEmployeesSuccess() {
        PostEmployeeResponse expectedResponse = new PostEmployeeResponse();
        expectedResponse.setStatus(200L);
        expectedResponse.setSuccess(true);
        expectedResponse.setMessages(List.of("Employees fetched successfully"));
        when(manager.getEmployees()).thenReturn(expectedResponse);

        PostEmployeeResponse result = employees.getEmployees();

        assertEquals(200L, result.getStatus());
        assertTrue(result.getSuccess());
        assertEquals("Employees fetched successfully", result.getMessages().get(0));
        verify(manager, times(1)).getEmployees();
    }

    @Test
    void getEmployeesThrowErrorDB() {
        when(manager.getEmployees()).thenThrow(new RuntimeException("DB error"));

        PostEmployeeResponse result = employees.getEmployees();

        assertEquals(500L, result.getStatus());
        assertFalse(result.getSuccess());
        assertEquals(1, result.getMessages().size());
        assertTrue(result.getMessages().get(0).contains("Error fetching employees: DB error"));
        verify(manager, times(1)).getEmployees();
    }

}

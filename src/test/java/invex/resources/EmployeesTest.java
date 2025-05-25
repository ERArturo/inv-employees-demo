package invex.resources;

import static org.junit.jupiter.api.Assertions.*;
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
    void createEmployeeBodyNull() {
        PostEmployeeResponse result = employees.createEmployee(null);
        assertEquals(400L, result.getStatus());
        assertFalse(result.getSuccess());
        assertTrue(result.getMessage().contains("Request body cannot be null"));
    }

    @Test
    void createEmployeeEmptyList() {
        PostEmployeeRequest req = mock(PostEmployeeRequest.class);
        when(req.getEmployees()).thenReturn(Collections.emptyList());

        PostEmployeeResponse result = employees.createEmployee(req);
        assertEquals(400L, result.getStatus());
        assertFalse(result.getSuccess());
        assertTrue(result.getMessage().contains("At least one employee is required"));
    }

    @Test
    void CreateEmployeeSuccess() throws Exception {

                // "employeeId": 2,
                // "firstName": "Jose",
                // "secondName": "Ramiro",
                // "lastName": "Luis",
                // "secondLastName": "Portillo",
                // "age": null,
                // "sex": "M",
                // "birth": "18-07-1996",
                // "position": "Developer",
                // "kind": "employee"
        EmployeesInput emp = new EmployeesInput();
        emp.setEmployeeId(0L); // Assuming employeeId is required and valid
        emp.setFirstName("John");
        emp.setSecondName("Escobedo");
        emp.setLastName("Doe");
        emp.setSecondLastName("Smith");
        emp.setAge(30L);
        emp.setSex("M");
        emp.setBirth("09-09-1994"); // Valid birth date
        emp.setPosition("Developer");
        emp.setKind("employee"); // Assuming kind is a required field

        List<EmployeesInput> empList = Collections.singletonList(emp);
        PostEmployeeRequest req = new PostEmployeeRequest();
        req.setEmployees(empList);

        PostEmployeeResponse result = employees.createEmployee(req);

        assertTrue(result.getSuccess());
        assertEquals(200L, result.getStatus());
        assertEquals("Employee(s) created successfully", result.getMessage().get(0));
        assertNotNull(result.getEmployees());
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
        assertEquals(7, result.getMessage().size());
        assertTrue(result.getMessage().contains("Employee ID cannot be null"));
        assertTrue(result.getMessage().contains("Birth date must be in the format DD-MM-YYYY"));
        assertTrue(result.getMessage().contains("Birth date must be a valid date"));
        assertTrue(result.getMessage().contains("First name must contain only letters"));
        assertTrue(result.getMessage().contains("Sex must be M, F, or O"));
        assertTrue(result.getMessage().contains("Position cannot be null"));
        assertTrue(result.getMessage().contains("Age must be a positive number"));
    }

    @Test
    void getEmployeesSuccess() {
        PostEmployeeResponse expectedResponse = new PostEmployeeResponse();
        expectedResponse.setStatus(200L);
        expectedResponse.setSuccess(true);
        expectedResponse.setMessage(List.of("Employees fetched successfully"));
        when(manager.getEmployees()).thenReturn(expectedResponse);

        PostEmployeeResponse result = employees.getEmployees();

        assertEquals(200L, result.getStatus());
        assertTrue(result.getSuccess());
        assertEquals("Employees fetched successfully", result.getMessage().get(0));
        verify(manager, times(1)).getEmployees();
    }

    @Test
    void getEmployeesThrowErrorDB() {
        when(manager.getEmployees()).thenThrow(new RuntimeException("DB error"));

        PostEmployeeResponse result = employees.getEmployees();

        assertEquals(500L, result.getStatus());
        assertFalse(result.getSuccess());
        assertEquals(1, result.getMessage().size());
        assertTrue(result.getMessage().get(0).contains("Error fetching employees: DB error"));
        verify(manager, times(1)).getEmployees();
    }

}
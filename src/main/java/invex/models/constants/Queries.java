package invex.models.constants;

public class Queries {
    public static final String EMPLOYEE_FIND_ALL = "Employee.findAll";
    public static final String EMPLOYEE_FIND_ALL_QUERY = "SELECT f FROM Employee f ORDER BY f.id";
    public static final String EMPLOYEE_DELETE_BY_ID = "Employee.deleteById";
    public static final String EMPLOYEE_DELETE_BY_ID_QUERY = "DELETE FROM Employee f WHERE f.id = :id";
    public static final String EMPLOYEE_UPDATE_BY_ID = "Employee.updateById";
    public static final String EMPLOYEE_UPDATE_BY_ID_QUERY = "UPDATE Employee f SET f.firstName = :firstName, f.secondName = :secondName, "
            + "f.lastName = :lastName, f.secondLastName = :secondLastName, f.age = :age, "
            + "f.sex = :sex, f.birth = :birth, f.position = :position WHERE f.id = :id";
}

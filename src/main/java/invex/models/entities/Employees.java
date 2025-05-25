package invex.models.entities;

import java.io.Serializable;

import invex.models.constants.People;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Employees implements Person, Serializable {
  private static final long serialVersionUID = 1L;

  @NotNull(message = "Employee ID cannot be null")
  private int employeeId;
  @NotNull(message = "First name cannot be null")
  @Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only letters")
  @Pattern(regexp = "^[A-Z][a-z]+$", message = "First name must start with a capital letter")
  @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "First name must be at least 3 characters long")
  private String firstName;
  private String secondName;
  @NotNull(message = "Last name cannot be null")
  @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only letters")
  @Pattern(regexp = "^[A-Z][a-z]+$", message = "Last name must start with a capital letter")
  @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "Last name must be at least 3 characters long")
  private String lastName;
  private String secondLastName;
  private int age;
  @Pattern(regexp = "M|F|O", message = "Sex must be M, F, or O")
  private String sex;
  @NotNull(message = "Birth date cannot be null")
  @Pattern(regexp = "^[0-9]{2}-[0-9]{2}-[0-9]{4}$", message = "Birth date must be in the format DD-MM-YYYY")
  @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19|20)\\d{2}$", message = "Birth date must be a valid date")
  private String birth;
  @NotNull(message = "Position cannot be null")
  private String position;
  private String kind = People.EMPLOYEE;

  // Getters and Setters
  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  @Override
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String getSecondName() {
    return secondName;
  }

  @Override
  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  @Override
  public String getSecondLastName() {
    return secondLastName;
  }

  @Override
  public void setSecondLastName(String secondLastName) {
    this.secondLastName = secondLastName;
  }

  @Override
  public int getAge() {
    return age;
  }

  @Override
  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String getSex() {
    return sex;
  }

  @Override
  public void setSex(String sex) {
    this.sex = sex;
  }

  @Override
  public String getBirth() {
    return birth;
  }

  @Override
  public void setBirth(String birth) {
    this.birth = birth;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  @Override
  public String getKind() {
    return kind;
  }

  @Override
  public void setKind(String kind) {
    this.kind = kind;
  }
}

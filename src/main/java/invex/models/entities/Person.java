package invex.models.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import invex.models.constants.People;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "kind")
@JsonSubTypes({ @JsonSubTypes.Type(value = Employees.class, name = People.EMPLOYEE) })
public interface Person {

  public String getFirstName();

  public void setFirstName(String firstName);

  public String getLastName();

  public void setLastName(String lastName);

  public String getSecondName();

  public void setSecondName(String secondName);

  public String getSecondLastName();

  public void setSecondLastName(String secondLastName);

  public Long getAge();

  public void setAge(Long age);

  public String getSex();

  public void setSex(String sex);

  public String getBirth();

  public void setBirth(String birth);

  public String getKind();

  public void setKind(String kind);
}

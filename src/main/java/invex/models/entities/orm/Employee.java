package invex.models.entities.orm;

import invex.models.constants.Queries;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.QueryHint;
import jakarta.persistence.SequenceGenerator;

@Entity
@NamedQuery(name = Queries.EMPLOYEE_FIND_ALL,
      query = Queries.EMPLOYEE_FIND_ALL_QUERY,
      hints = @QueryHint(name = "org.hibernate.cacheable", value = "true") )
@NamedQuery(name = Queries.EMPLOYEE_DELETE_BY_ID,
      query = Queries.EMPLOYEE_DELETE_BY_ID_QUERY,
      hints = @QueryHint(name = "org.hibernate.cacheable", value = "true") )
@NamedQuery(name = Queries.EMPLOYEE_UPDATE_BY_ID,
      query = Queries.EMPLOYEE_UPDATE_BY_ID_QUERY,
      hints = @QueryHint(name = "org.hibernate.cacheable", value = "true") )
public class Employee {

  @Id
  @SequenceGenerator(name = "giftSeq", sequenceName = "gift_id_seq", allocationSize = 1, initialValue = 1)
  @GeneratedValue(generator = "giftSeq")
  private Long id;
  private String firstName;
  private String secondName;
  private String lastName;
  private String secondLastName;
  private Long age;
  private String sex;
  private String birth;
  private String position;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public String getSecondLastName() {
    return secondLastName;
  }

  public void setSecondLastName(String secondLastName) {
    this.secondLastName = secondLastName;
  }

  public Long getAge() {
    return age;
  }

  public void setAge(Long age) {
    this.age = age;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getBirth() {
    return birth;
  }

  public void setBirth(String birth) {
    this.birth = birth;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

}

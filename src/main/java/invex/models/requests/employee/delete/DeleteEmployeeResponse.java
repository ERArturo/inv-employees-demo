package invex.models.requests.employee.delete;

import java.util.List;

import invex.models.requests.abstracts.AbstractResponse;

public class DeleteEmployeeResponse implements AbstractResponse {
    private Long status;
    private Boolean success;
    private List<String> messages;

    public Long getStatus() {
      return status;
    }

    public void setStatus(Long status) {
      this.status = status;
    }

    public Boolean getSuccess() {
      return success;
    }

    public void setSuccess(Boolean success) {
      this.success = success;
    }


    @Override
    public List<String> getMessages() {
      return messages;
    }

    @Override
    public void setMessages(List<String> message) {
    }
    
}

package invex.models.requests.abstracts;

import java.util.List;

public interface AbstractResponse {
    Long getStatus();
    void setStatus(Long status);
    Boolean getSuccess();
    void setSuccess(Boolean success);
    List<String> getMessages();
    void setMessages(List<String> message);
}

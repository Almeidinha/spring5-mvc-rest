package almeida.springframework.spring5mvcrest.api.vi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    private String id;
    private String firstname;
    private String lastname;

    @JsonProperty("customer_url")
    private String customerurl;

}

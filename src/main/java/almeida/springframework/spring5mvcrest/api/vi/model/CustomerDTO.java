package almeida.springframework.spring5mvcrest.api.vi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    private String id;

    @ApiModelProperty(value = "This is the first name", required = true)
    private String firstname;
    private String lastname;

    @JsonProperty("customer_url")
    private String customerurl;

}

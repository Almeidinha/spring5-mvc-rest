package almeida.springframework.spring5mvcrest.api.vi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorsDTO {

    private String id;
    private String name;

    @JsonProperty("vendor_url")
    private String vendorurl;

}
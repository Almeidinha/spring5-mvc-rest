package almeida.springframework.spring5mvcrest.api.vi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorsListDTO {

    private List<VendorsDTO> vendors;
}
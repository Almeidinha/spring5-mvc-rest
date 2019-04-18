package almeida.springframework.spring5mvcrest.api.vi.model;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryDTO {
    private UUID id;
    private String name;
}

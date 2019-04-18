package almeida.springframework.spring5mvcrest.api.vi.mapper;

import almeida.springframework.spring5mvcrest.api.vi.model.CategoryDTO;
import almeida.springframework.spring5mvcrest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}

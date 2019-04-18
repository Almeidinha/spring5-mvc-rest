package almeida.springframework.spring5mvcrest.api.vi.mapper;

import almeida.springframework.spring5mvcrest.api.vi.model.CategoryDTO;
import almeida.springframework.spring5mvcrest.domain.Category;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class CategoryMapperTest {

    public static final String NAME = "Joe";
    public static final UUID ID = UUID.randomUUID();

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws Exception {

        //given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }

}
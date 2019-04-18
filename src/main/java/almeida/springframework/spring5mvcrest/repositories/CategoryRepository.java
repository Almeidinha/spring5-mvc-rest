package almeida.springframework.spring5mvcrest.repositories;

import almeida.springframework.spring5mvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}

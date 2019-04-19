package almeida.springframework.spring5mvcrest.repositories;

import almeida.springframework.spring5mvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    Customer findByFirstname(String name);

}

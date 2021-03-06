package almeida.springframework.spring5mvcrest.repositories;

import almeida.springframework.spring5mvcrest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, String> {

    Vendor findByName(String name);
}

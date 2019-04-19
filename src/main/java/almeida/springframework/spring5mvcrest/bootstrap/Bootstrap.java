package almeida.springframework.spring5mvcrest.bootstrap;

import almeida.springframework.spring5mvcrest.domain.Category;
import almeida.springframework.spring5mvcrest.domain.Customer;
import almeida.springframework.spring5mvcrest.repositories.CategoryRepository;
import almeida.springframework.spring5mvcrest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
    }

    private void loadCustomers() {

        List<Customer> cc = new ArrayList<>();

        Customer mike = new Customer();
        mike.setFirstname("Marcos");
        mike.setLastname("Almeida");
        cc.add(mike);

        Customer nine = new Customer();
        nine.setFirstname("Aline");
        nine.setLastname("Duarte");
        cc.add(nine);

        customerRepository.saveAll(cc);
        System.out.println("Customers Loaded = " + customerRepository.count() );
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Categories loaded = " + categoryRepository.count() );
    }

}

package almeida.springframework.spring5mvcrest.bootstrap;

import almeida.springframework.spring5mvcrest.domain.Category;
import almeida.springframework.spring5mvcrest.domain.Customer;
import almeida.springframework.spring5mvcrest.domain.Vendor;
import almeida.springframework.spring5mvcrest.repositories.CategoryRepository;
import almeida.springframework.spring5mvcrest.repositories.CustomerRepository;
import almeida.springframework.spring5mvcrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
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

    private void loadVendors() {

        Vendor vendor1 = new Vendor();
        vendor1.setName("Western Tasty Fruits Ltd");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Exotic Fruits Company");

        Vendor vendor3 = new Vendor();
        vendor3.setName("Home Fruits");

        Vendor vendor4 = new Vendor();
        vendor4.setName("Fun Fresh Fruits Ltd.");

        Vendor vendor5 = new Vendor();
        vendor5.setName("Nuts for Nuts Company");

        vendorRepository.saveAll(Arrays.asList(vendor1, vendor2, vendor3, vendor4, vendor5));
    }

}

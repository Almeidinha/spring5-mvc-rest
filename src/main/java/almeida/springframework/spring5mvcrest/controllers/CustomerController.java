package almeida.springframework.spring5mvcrest.controllers;

import almeida.springframework.spring5mvcrest.api.vi.model.CustomerDTO;
import almeida.springframework.spring5mvcrest.api.vi.model.CustomerListDTO;
import almeida.springframework.spring5mvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers() {
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK
        );
    }

    /*@GetMapping("/name/{firstname}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String firstname) {
        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerByFirstname(firstname), HttpStatus.OK
        );
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String id) {

        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerById(id), HttpStatus.OK
        );
    }
}

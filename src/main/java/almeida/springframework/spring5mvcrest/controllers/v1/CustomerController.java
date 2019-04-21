package almeida.springframework.spring5mvcrest.controllers.v1;

import almeida.springframework.spring5mvcrest.api.vi.model.CustomerDTO;
import almeida.springframework.spring5mvcrest.api.vi.model.CustomerListDTO;
import almeida.springframework.spring5mvcrest.services.CustomerService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "This is the Customer Controller !!!")
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers() {
        return  new CustomerListDTO(customerService.getAllCustomers());
    }

    /*@GetMapping("/name/{firstname}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String firstname) {
        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerByFirstname(firstname), HttpStatus.OK
        );
    }*/

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomer(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping //@RequestBody: Spring looks at the body request and tryes to create a customerDto from it
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.createNewCustomer(customerDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO){
        return customerService.updateCustomerByDto(id, customerDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void  deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomerById(id);
    }

}

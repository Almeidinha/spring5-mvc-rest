package almeida.springframework.spring5mvcrest.services;

import almeida.springframework.spring5mvcrest.api.vi.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerByFirstname(String name);
    CustomerDTO getCustomerById(String id);
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomerByDto(String id, CustomerDTO customerDTO);
    CustomerDTO patchCustomer(String id, CustomerDTO customerDTO);
    void deleteCustomerById(String id);
}

package almeida.springframework.spring5mvcrest.services;

import almeida.springframework.spring5mvcrest.api.vi.mapper.CustomerMapper;
import almeida.springframework.spring5mvcrest.api.vi.model.CustomerDTO;
import almeida.springframework.spring5mvcrest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map( customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerurl("/api/v1/customer/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerByFirstname(String name) {
        return customerMapper.customerToCustomerDTO(customerRepository.findByFirstname(name));
    }

    @Override
    public CustomerDTO getCustomerById(String id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(RuntimeException::new);
    }
}
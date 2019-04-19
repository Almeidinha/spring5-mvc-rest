package almeida.springframework.spring5mvcrest.api.vi.mapper;

import almeida.springframework.spring5mvcrest.api.vi.model.CustomerDTO;
import almeida.springframework.spring5mvcrest.domain.Customer;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    private final String FIRST_NAME = "Mike";
    private final String LAST_NAME = "Almeida";
    private final String ID = UUID.randomUUID().toString();

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomrDTO() throws Exception {

        // given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRST_NAME);
        customer.setLastname(LAST_NAME);

        // when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        // then
        assertEquals(ID, customerDTO.getId());
        assertEquals(FIRST_NAME, customerDTO.getFirstname());
        assertEquals(LAST_NAME, customerDTO.getLastname());
    }

}
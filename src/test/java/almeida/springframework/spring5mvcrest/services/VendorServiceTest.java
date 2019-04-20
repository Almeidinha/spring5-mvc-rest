package almeida.springframework.spring5mvcrest.services;

import almeida.springframework.spring5mvcrest.api.vi.mapper.VendorsMapper;
import almeida.springframework.spring5mvcrest.api.vi.model.VendorsDTO;
import almeida.springframework.spring5mvcrest.controllers.v1.VendorController;
import almeida.springframework.spring5mvcrest.domain.Vendor;
import almeida.springframework.spring5mvcrest.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class VendorServiceTest {

    public static final String ID = UUID.randomUUID().toString();
    public static final String NAME = "Jimmy LTDA";
    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(VendorsMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void getAllVendors() throws Exception {

        //given
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        //when
        List<VendorsDTO> vendorsDTOS = vendorService.getAllVendors();

        //then
        assertEquals(3, vendorsDTOS.size());

    }

    @Test
    public void getVendorsById() throws Exception {

        //given
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        when(vendorRepository.findById(anyString())).thenReturn(Optional.of(vendor));

        //when
        VendorsDTO vendorDTO = vendorService.getVendorById(ID);

        //then
        assertEquals(ID, vendorDTO.getId());
        assertEquals(NAME, vendorDTO.getName());

    }

    @Test
    public void createNewCustomer() throws Exception {
        String id = UUID.randomUUID().toString();
        //given
        VendorsDTO vendorsDTO = new VendorsDTO();
        vendorsDTO.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorsDTO.getName());
        savedVendor.setId(id);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorsDTO savedDto = vendorService.createNewVendor(vendorsDTO);

        //then
        assertEquals(vendorsDTO.getName(), savedDto.getName());
        assertEquals(VendorController.BASE_URL + "/" + id, savedDto.getVendorurl());
    }

    @Test
    public void updateVendorByDto() throws Exception {
        String id = UUID.randomUUID().toString();

        //given
        VendorsDTO customerDTO = new VendorsDTO();
        customerDTO.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setName(customerDTO.getName());
        savedVendor.setId(id);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorsDTO savedDto = vendorService.updateVendorByDto(id, customerDTO);

        //then
        assertEquals(customerDTO.getName(), savedDto.getName());
        assertEquals(VendorController.BASE_URL + "/" + id, savedDto.getVendorurl());
    }

    @Test
    public void deleteVendorById() throws Exception {
        String id = UUID.randomUUID().toString();

        vendorRepository.deleteById(id);
        verify(vendorRepository, times(1)).deleteById(anyString());
    }

}
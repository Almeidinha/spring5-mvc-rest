package almeida.springframework.spring5mvcrest.controllers.v1;

import almeida.springframework.spring5mvcrest.api.vi.model.VendorsDTO;
import almeida.springframework.spring5mvcrest.api.vi.model.VendorsListDTO;
import almeida.springframework.spring5mvcrest.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.UUID;

import static almeida.springframework.spring5mvcrest.controllers.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {VendorController.class})
public class VendorControllerTest {
    private static final String ID_1 = UUID.randomUUID().toString();
    private static final String ID_2 = UUID.randomUUID().toString();

    @MockBean //provided by Spring Context
            VendorService vendorService;

    @Autowired
    MockMvc mockMvc; //provided by Spring Context

    VendorsDTO vendorDTO_1;
    VendorsDTO vendorDTO_2;

    @Before
    public void setUp() throws Exception {
        vendorDTO_1 = new VendorsDTO();
        vendorDTO_1.setVendorurl(VendorController.BASE_URL + "/" + ID_1);
        vendorDTO_1.setName("Vendor 1");

        vendorDTO_2 = new VendorsDTO();
        vendorDTO_2.setName("Vendor 2");
        vendorDTO_2.setVendorurl(VendorController.BASE_URL + "/" + ID_2);
    }

    @Test
    public void getVendorList() throws Exception {
        VendorsListDTO vendorListDTO = new VendorsListDTO(Arrays.asList(vendorDTO_1, vendorDTO_2));

        given(vendorService.getAllVendors()).willReturn(vendorListDTO.getVendors());

        mockMvc.perform(get(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void getVendorById() throws Exception {

        given(vendorService.getVendorById(anyString())).willReturn(vendorDTO_1);

        mockMvc.perform(get(VendorController.BASE_URL + "/" + ID_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void createNewVendor() throws Exception {

        given(vendorService.createNewVendor(vendorDTO_1)).willReturn(vendorDTO_1);

        mockMvc.perform(post(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO_1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void updateVendor() throws Exception {

        given(vendorService.updateVendorByDto(anyString(), any(VendorsDTO.class))).willReturn(vendorDTO_1);

        mockMvc.perform(put(VendorController.BASE_URL + "/" + ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void patchVendor() throws Exception {
        given(vendorService.updateVendorByDto(anyString(), any(VendorsDTO.class))).willReturn(vendorDTO_1);

        mockMvc.perform(patch(VendorController.BASE_URL + "/" + ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void deleteVendor() throws Exception {
        mockMvc.perform(delete(VendorController.BASE_URL + "/" + ID_1))
                .andExpect(status().isOk());

        then(vendorService).should().deleteVendorById(anyString());

    }
}
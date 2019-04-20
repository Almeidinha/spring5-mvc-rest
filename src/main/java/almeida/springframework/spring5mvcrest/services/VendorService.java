package almeida.springframework.spring5mvcrest.services;

import almeida.springframework.spring5mvcrest.api.vi.model.VendorsDTO;

import java.util.List;

public interface VendorService {

    List<VendorsDTO> getAllVendors();
    VendorsDTO getVendorById(String id);
    VendorsDTO createNewVendor(VendorsDTO vendorsDTO);
    VendorsDTO updateVendorByDto(String id, VendorsDTO vendorsDTO);
    VendorsDTO patchVendor(String id, VendorsDTO vendorsDTO);
    void deleteVendorById(String id);
}

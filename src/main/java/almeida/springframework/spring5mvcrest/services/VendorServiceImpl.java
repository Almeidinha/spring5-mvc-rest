package almeida.springframework.spring5mvcrest.services;

import almeida.springframework.spring5mvcrest.api.vi.mapper.VendorsMapper;
import almeida.springframework.spring5mvcrest.api.vi.model.VendorsDTO;
import almeida.springframework.spring5mvcrest.controllers.v1.VendorController;
import almeida.springframework.spring5mvcrest.domain.Vendor;
import almeida.springframework.spring5mvcrest.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorsMapper vendorsMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorsMapper vendorsMapper, VendorRepository vendorRepository) {
        this.vendorsMapper = vendorsMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorsDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorsDTO vendorsDTO = vendorsMapper.vendorToVendorDTO(vendor);
                    vendorsDTO.setVendorurl(getVendorUrl(vendor.getId()));
                    return vendorsDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorsDTO getVendorById(String id) {
        return vendorRepository.findById(id)
                .map(vendorsMapper::vendorToVendorDTO)
                .map(vendorsDTO -> {
                    vendorsDTO.setVendorurl(getVendorUrl(id));
                    return vendorsDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorsDTO createNewVendor(VendorsDTO vendorsDTO) {
        return saveAndReturnDto(vendorsMapper.vendorDtoToVendor(vendorsDTO));
    }

    @Override
    public VendorsDTO updateVendorByDto(String id, VendorsDTO vendorsDTO) {
        Vendor vendor = vendorsMapper.vendorDtoToVendor(vendorsDTO);
        vendor.setId(id);

        return  saveAndReturnDto(vendor);
    }

    @Override
    public VendorsDTO patchVendor(String id, VendorsDTO vendorsDTO) {
        return vendorRepository.findById(id).map(vendor -> {
            if (vendorsDTO.getName() != null) {
                vendor.setName(vendorsDTO.getName());
            }

            VendorsDTO returnDto = vendorsMapper.vendorToVendorDTO(vendorRepository.save(vendor));
            returnDto.setVendorurl(getVendorUrl(id));

            return returnDto;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(String id) {
        vendorRepository.deleteById(id);
    }

    private VendorsDTO saveAndReturnDto(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorsDTO returnVendorDTO = vendorsMapper.vendorToVendorDTO(vendor);
        returnVendorDTO.setVendorurl(getVendorUrl(savedVendor.getId()));

        return returnVendorDTO;
    }

    private String getVendorUrl(String id) {
        return VendorController.BASE_URL + "/" + id;
    }

}

package almeida.springframework.spring5mvcrest.api.vi.mapper;

import almeida.springframework.spring5mvcrest.api.vi.model.VendorsDTO;
import almeida.springframework.spring5mvcrest.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorsMapper {

    VendorsMapper INSTANCE = Mappers.getMapper(VendorsMapper.class);
    VendorsDTO vendorToVendorDTO(Vendor vendor);
    Vendor vendorDtoToVendor(VendorsDTO vendorsDTO);

}

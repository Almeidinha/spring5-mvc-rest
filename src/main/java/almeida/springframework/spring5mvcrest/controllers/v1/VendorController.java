package almeida.springframework.spring5mvcrest.controllers.v1;

import almeida.springframework.spring5mvcrest.api.vi.model.VendorsDTO;
import almeida.springframework.spring5mvcrest.api.vi.model.VendorsListDTO;
import almeida.springframework.spring5mvcrest.services.VendorService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "This is the Vendor Controller !!!")
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorsListDTO getAllVendors() {
        return new VendorsListDTO(vendorService.getAllVendors());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorsDTO getVendorById(@PathVariable String id) {
        return vendorService.getVendorById(id);
    }

    @PostMapping //@RequestBody: Spring looks at the body request and tryes to create a VendorDto from it
    @ResponseStatus(HttpStatus.CREATED)
    public VendorsDTO createNewVendor(@RequestBody VendorsDTO vendorsDTO){
        return vendorService.createNewVendor(vendorsDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public VendorsDTO updateVendor(@PathVariable String id, @RequestBody VendorsDTO vendorsDTO){
        return vendorService.updateVendorByDto(id, vendorsDTO);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public VendorsDTO patchVendor(@PathVariable String id, @RequestBody VendorsDTO vendorDTO){
        return vendorService.updateVendorByDto(id, vendorDTO);
    }


    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void  deleteVendor(@PathVariable String id) {
        vendorService.deleteVendorById(id);
    }


}

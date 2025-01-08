package tn.micro.service.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.micro.service.cloud.request.CreateAdressRequest;
import tn.micro.service.cloud.response.AdressResponse;
import tn.micro.service.cloud.service.IAddressService;
@CrossOrigin(origins = "http://localhost:4200")  // Applies to all endpoints
@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    // Create a new address
    @PostMapping("/create")
    public ResponseEntity<AdressResponse> createAddress(@RequestBody CreateAdressRequest createAdressRequest) {
        AdressResponse adressResponse = addressService.createAddress(createAdressRequest);
        return ResponseEntity.ok(adressResponse);
    }

    // Get an address by ID
    @GetMapping("/{id}")
    public ResponseEntity<AdressResponse> getAddressById(@PathVariable long id) {
        AdressResponse adressResponse = addressService.getAddressById(id);
        return ResponseEntity.ok(adressResponse);
    }

    @GetMapping("/All")
    public ResponseEntity<List<AdressResponse>> getAllAddresses() {
        List<AdressResponse> addressList = addressService.getAllAddresses();
        return ResponseEntity.ok(addressList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AdressResponse> updateAddress(@PathVariable long id, @RequestBody CreateAdressRequest request) {
        return ResponseEntity.ok(addressService.updateAddress(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok("Address with ID " + id + " has been deleted successfully.");
    }
}

package tn.micro.service.cloud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.micro.service.cloud.entity.Address;
import tn.micro.service.cloud.exceptions.AdressBadRequestException;
import tn.micro.service.cloud.repository.AddressRepository;
import tn.micro.service.cloud.request.CreateAdressRequest;
import tn.micro.service.cloud.response.AdressResponse;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public AdressResponse createAddress(CreateAdressRequest createAdressRequest) {
        // Map the request data to the Address entity
        Address address = new Address();
        address.setStreet(createAdressRequest.getStreet());
        address.setCity(createAdressRequest.getCity());

        // Save the address to the database
        address = addressRepository.save(address);

        // Return the saved address as a response
        return new AdressResponse(address);
    }

    @Override
    public AdressResponse getAddressById(long id) {
        // Find the address by ID and map it to a response
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AdressBadRequestException("Address not found with ID: " + id));
        return new AdressResponse(address);
    }

    @Override
    public List<AdressResponse> getAllAddresses() {
        // Fetch all addresses and map them to a list of responses
        return addressRepository.findAll().stream()
                .map(AdressResponse::new)
                .collect(Collectors.toList());
    }
    public AdressResponse updateAddress(long id, CreateAdressRequest updateAddressRequest) {
        // Check if the address exists
        if (!addressRepository.existsById(id)) {
            throw new AdressBadRequestException("Address not found with ID " + id);
        }

        // Retrieve the existing address
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AdressBadRequestException("Address not found with ID " + id));

        // Update address fields
        address.setStreet(updateAddressRequest.getStreet());
        address.setCity(updateAddressRequest.getCity());

        // Save updated address to the database
        address = addressRepository.save(address);

        return new AdressResponse(address);
    }

    // New Delete Address Method
    public void deleteAddress(long id) {
        // Check if the address exists
        if (!addressRepository.existsById(id)) {
            throw new AdressBadRequestException("Address not found with ID " + id);
        }

        // Perform the delete operation
        addressRepository.deleteById(id);
    }

}

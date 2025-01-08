package tn.micro.service.cloud.service;

import java.util.List;

import tn.micro.service.cloud.request.CreateAdressRequest;
import tn.micro.service.cloud.response.AdressResponse;

public interface IAddressService {
    AdressResponse createAddress(CreateAdressRequest createAdressRequest);
    AdressResponse getAddressById(long id);
    List<AdressResponse> getAllAddresses();
    AdressResponse updateAddress(long id, CreateAdressRequest updateAddressRequest);
    void deleteAddress(long id);
}

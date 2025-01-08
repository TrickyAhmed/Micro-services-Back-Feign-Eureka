package com.omicrone.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.omicrone.request.CreateAddressRequest;
import com.omicrone.response.AddressResponse;

import java.util.List;

@FeignClient(value = "api-gateway")
public interface AddressFeignClient {

    @GetMapping("/address-service/api/address/{id}")
    AddressResponse getById(@PathVariable long id);

    @GetMapping("/address-service/api/address/All")
    List<AddressResponse> getAll();

    @PostMapping("/address-service/api/address/create")
    AddressResponse createAddress(@RequestBody CreateAddressRequest createAddressRequest);

    @PutMapping("/address-service/api/address/update/{id}")
    AddressResponse updateAddress(@PathVariable long id, @RequestBody CreateAddressRequest updateAddressRequest);

    @DeleteMapping("/address-service/api/address/delete/{id}")
    void deleteAddress(@PathVariable long id);
}

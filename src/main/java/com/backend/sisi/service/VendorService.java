package com.backend.sisi.service;


import com.backend.sisi.dto.request.VendorRequest;
import com.backend.sisi.dto.response.VendorResponse;
import com.backend.sisi.entity.Vendor;
import com.backend.sisi.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public List<VendorResponse> getAllVendors() {
        return vendorRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Optional<VendorResponse> getVendorById(Integer id) {
        return vendorRepository.findById(id)
                .map(this::mapToResponse);
    }

    public VendorResponse createVendor(VendorRequest vendorRequest) {
        Vendor vendor = new Vendor();
        vendor.setName(vendorRequest.getName());
        Vendor savedVendor = vendorRepository.save(vendor);
        return mapToResponse(savedVendor);
    }

    public VendorResponse updateVendor(Integer id, VendorRequest vendorRequest) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
        vendor.setName(vendorRequest.getName());
        Vendor updatedVendor = vendorRepository.save(vendor);
        return mapToResponse(updatedVendor);
    }

    public void deleteVendor(Integer id) {
        vendorRepository.deleteById(id);
    }

    private VendorResponse mapToResponse(Vendor vendor) {
        VendorResponse response = new VendorResponse();
        response.setId(vendor.getId());
        response.setName(vendor.getName());
        return response;
    }
}

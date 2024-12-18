package com.backend.sisi.controller;

import com.backend.sisi.dto.request.VendorRequest;
import com.backend.sisi.dto.response.VendorResponse;
import com.backend.sisi.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendors")
@RequiredArgsConstructor
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<VendorResponse>> getAllVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<VendorResponse> getVendorById(@PathVariable Integer id) {
        return vendorService.getVendorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<VendorResponse> createVendor(@RequestBody VendorRequest vendorRequest) {
        return ResponseEntity.ok(vendorService.createVendor(vendorRequest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<VendorResponse> updateVendor(@PathVariable Integer id, @RequestBody VendorRequest vendorRequest) {
        return ResponseEntity.ok(vendorService.updateVendor(id, vendorRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> deleteVendor(@PathVariable Integer id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }
}

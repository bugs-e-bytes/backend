package br.com.creativeexperience.book_now.accommodation.controllers;

import br.com.creativeexperience.book_now.accommodation.dto.AccommodationRequest;
import br.com.creativeexperience.book_now.accommodation.dto.AccommodationResponse;
import br.com.creativeexperience.book_now.accommodation.dto.ImageResponse;
import br.com.creativeexperience.book_now.accommodation.services.AccommodationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/accommodations")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 36000, allowCredentials = "true",
        value = "http://localhost:4200",
        allowedHeaders = {"Authorization", "Content-Type"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
public class AccommodationController {

    private final AccommodationService accommodationService;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'OWNER')")
    @PostMapping
    public ResponseEntity<AccommodationResponse> createAccommodation(
            @RequestBody AccommodationRequest request, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        AccommodationResponse response = accommodationService.createAccommodationForUser(request, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<AccommodationResponse> getAccommodation(@PathVariable Long id, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        AccommodationResponse response = accommodationService.getAccommodationForUser(id, username);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<AccommodationResponse>> listAccommodations(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        List<AccommodationResponse> accommodations = accommodationService.listAccommodationsForUser(username);
        return ResponseEntity.ok(accommodations);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AccommodationResponse>> listAllAccommodations() {
        List<AccommodationResponse> accommodations = accommodationService.listAllAccommodations();
        return ResponseEntity.ok(accommodations);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        accommodationService.deleteAccommodationForUser(id, username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<AccommodationResponse> updateAccommodation(
            @PathVariable Long id,
            @RequestBody AccommodationRequest request,
            Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        AccommodationResponse existingAccommodation = accommodationService
                .getAccommodationForUser(id, username);
        if (existingAccommodation == null) {
            return ResponseEntity.notFound().build();
        }
        AccommodationResponse updatedAccommodation = accommodationService
                .updateAccommodationForUser(id, request, username);
        return ResponseEntity.ok(updatedAccommodation);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'OWNER')")
    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageResponse> addImageToAccommodation(@PathVariable Long id,
                                                                 @RequestParam("image") MultipartFile image,
                                                                 Authentication authentication) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        ImageResponse response = accommodationService.addImageToAccommodation(id, image);
        return ResponseEntity.ok(response);
    }

}

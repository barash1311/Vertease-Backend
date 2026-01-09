package com.vertease.controller.userentry;

import com.vertease.dto.userentry.UserEntryRequest;
import com.vertease.dto.userentry.UserEntryResponse;
import com.vertease.service.UserEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequiredArgsConstructor
public class UserEntryController {
    private final UserEntryService userEntryService;

    @PostMapping
    public ResponseEntity<UserEntryResponse> create(@RequestBody UserEntryRequest request) {
        return ResponseEntity.ok(userEntryService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<UserEntryResponse>> getAll() {
        return ResponseEntity.ok(userEntryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntryResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(userEntryService.getById(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<UserEntryResponse>> getByPatient(@PathVariable String patientId) {
        return ResponseEntity.ok(userEntryService.getByPatient(patientId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntryResponse> update(
            @PathVariable String id,
            @RequestBody UserEntryRequest request) {
        return ResponseEntity.ok(userEntryService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userEntryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

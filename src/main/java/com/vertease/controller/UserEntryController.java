package com.vertease.controller;

import com.vertease.service.UserEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequiredArgsConstructor
public class UserEntryController {
    private final UserEntryService userEntryService;
}

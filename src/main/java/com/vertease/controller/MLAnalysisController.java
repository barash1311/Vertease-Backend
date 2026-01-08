package com.vertease.controller;

import com.vertease.service.MLAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MLAnalysisController {
    private final MLAnalysisService mlAnalysisService;
}

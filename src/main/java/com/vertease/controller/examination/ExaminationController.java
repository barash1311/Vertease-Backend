package com.vertease.controller.examination;

import com.vertease.service.ExaminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExaminationController {
    private final ExaminationService examinationService;
}

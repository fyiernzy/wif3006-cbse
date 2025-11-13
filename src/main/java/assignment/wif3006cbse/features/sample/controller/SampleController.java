package assignment.wif3006cbse.features.sample.controller;

import assignment.wif3006cbse.features.sample.dto.CreateSampleModel;
import assignment.wif3006cbse.features.sample.dto.SampleModel;
import assignment.wif3006cbse.features.sample.service.SampleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/sample")
public class SampleController {

    private final SampleService sampleService;

    @PostMapping
    public SampleModel createSample(@RequestBody @Valid CreateSampleModel createSampleModel) {
        return sampleService.createSample(createSampleModel);
    }

}

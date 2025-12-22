package assignment.wif3006cbse.features.sample.service;

import assignment.wif3006cbse.features.sample.domain.Sample;
import assignment.wif3006cbse.features.sample.domain.SampleRepository;
import assignment.wif3006cbse.features.sample.dto.CreateSampleModel;
import assignment.wif3006cbse.features.sample.dto.SampleModel;
import assignment.wif3006cbse.features.sample.mapper.SampleMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class SampleService {

    private final SampleMapper sampleMapper;
    private final SampleRepository sampleRepository;

    @Transactional
    public SampleModel createSample(@NotNull @Valid CreateSampleModel createSampleModel) {
        Sample sample = sampleMapper.toEntity(createSampleModel);
        return sampleMapper.toModel(sampleRepository.save(sample));
    }
}

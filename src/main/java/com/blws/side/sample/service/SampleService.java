package com.blws.side.sample.service;

import com.blws.side.common.exception.CustomException;
import com.blws.side.sample.entity.Sample;
import com.blws.side.sample.repository.SampleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleService {
    private final SampleRepository sampleRepository;

    public List<Sample> getSampleAll() {
        return sampleRepository.findAll();
    }

    public Sample getSampleById(Long id) {
        return sampleRepository.findById(id)
                .orElseThrow(() -> new CustomException("Sample not found", HttpStatus.BAD_REQUEST));
    }

    public Sample createSample(Sample sample) {
        return sampleRepository.save(sample);
    }

    @Transactional
    public Sample updateSample(Long id, Sample sample) {
        Sample target = sampleRepository.findById(id)
                .orElseThrow(() -> new CustomException("Sample not found", HttpStatus.BAD_REQUEST));
        return sampleRepository.save(target.update(sample));
    }

    @Transactional
    public Sample deleteSample(Long id) {
        Sample sample = sampleRepository.findById(id)
                .orElseThrow(() -> new CustomException("Sample not found", HttpStatus.BAD_REQUEST));
        sampleRepository.deleteById(id);
        return sample;
    }

}

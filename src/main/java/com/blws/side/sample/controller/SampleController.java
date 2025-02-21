package com.blws.side.sample.controller;

import com.blws.side.sample.dto.RequestSampleCreateDTO;
import com.blws.side.sample.dto.RequestSampleUpdateDTO;
import com.blws.side.sample.dto.ResponseSampleDTO;
import com.blws.side.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SampleController {

    private final SampleService sampleService;

    @GetMapping("/sample")
    public List<ResponseSampleDTO> getSampleAll() {
        return sampleService.getSampleAll().stream()
                .map(ResponseSampleDTO::of)
                .toList();
    }

    @GetMapping("/sample/{id}")
    public ResponseSampleDTO getSample(@PathVariable("id") Long id) {
        return ResponseSampleDTO.of(sampleService.getSampleById(id));
    }

    // ResponseDTO 사용
//    @GetMapping("/sample/{id}")
//    public ResponseDTO getSample(@PathVariable("id") Long id) {
//        return ResponseDTO.of(sampleService.getSampleById(id));
//    }

    @PostMapping("/sample")
    public ResponseSampleDTO createSample(@RequestBody RequestSampleCreateDTO requestSampleCreateDTO) {
        return ResponseSampleDTO.of(sampleService.createSample(requestSampleCreateDTO.toEntity()));
    }

    @PutMapping("/sample/{id}")
    public ResponseSampleDTO updateSample(@PathVariable("id") Long id,
                                          @RequestBody RequestSampleUpdateDTO requestSampleUpdateDTO) {
        return ResponseSampleDTO.of(sampleService.updateSample(id, requestSampleUpdateDTO.toEntity()));
    }

    @DeleteMapping("/sample/{id}")
    public ResponseSampleDTO deleteSample(@PathVariable("id")Long id) {
        return ResponseSampleDTO.of(sampleService.deleteSample(id));
    }

}

package com.example.ProgrammingTechnology.controler;

import com.example.ProgrammingTechnology.dto.PayMethodDto;
import com.example.ProgrammingTechnology.mapper.PayMethodMapper;
import com.example.ProgrammingTechnology.repository.PayMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payMethods")
public class PayMethodController {
    private final PayMethodRepository payMethodRepository;
    private final PayMethodMapper mapper;
    @GetMapping
    public ResponseEntity<List<PayMethodDto>> getAll(){
        return ResponseEntity.ok(mapper.toDtoList(payMethodRepository.findAll()));
    }
}

package com.example.rest.controller;

import com.example.rest.exception.InvalidInputException;
import com.example.rest.model.FileRequest;
import com.example.rest.service.CSVService;
import com.example.rest.util.ErrorUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    private final CSVService csvService;
    private final ErrorUtils errorUtils;

    @Autowired
    public FileUploadController(CSVService csvService, ErrorUtils errorUtils) {
        this.csvService = csvService;
        this.errorUtils = errorUtils;
    }

    @PostMapping("/csv")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file,
                                            @Valid @ModelAttribute FileRequest fileRequest,
                                            BindingResult result) {


        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename != null && !originalFilename.endsWith(".csv")) {
                throw new InvalidInputException("Invalid file type. Please upload a .csv file.");
            }

            errorUtils.handleBindingResultErrors(result);
            log.info(String.valueOf(result.getAllErrors()));
            csvService.parseCSVFile(fileRequest);
            log.info("successfully");
        } else {
            throw new InvalidInputException("File cannot be empty.");
        }

       return ResponseEntity.ok("CSV file uploaded and data saved to the database.");
    }
}

package com.example.rest.service.impl;

import com.example.rest.exception.InvalidInputException;
import com.example.rest.model.Item;
import com.example.rest.model.FileRequest;
import com.example.rest.repository.ItemRepository;
import com.example.rest.service.CSVService;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CSVServiceImpl implements CSVService {

    private final ItemRepository repository;

    @Autowired
    public CSVServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public void parseCSVFile(FileRequest fileRequest) {

        boolean hasHeader = true;

        try (Reader reader = new InputStreamReader(fileRequest.getFile().getInputStream())) {

            CSVParser csvParser = new CSVParserBuilder()
                    .withSeparator(',')
                    .withIgnoreQuotations(true)
                    .build();
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withCSVParser(csvParser)
                    .build();

            List<Item> dataList = new ArrayList<>();
            String[] nextRecord;

            if (hasHeader) {
                csvReader.readNext();
            }

            while ((nextRecord = csvReader.readNext()) != null) {
                Item data = new Item();
                data.setSource(nextRecord[0]);
                data.setCodeListCode(nextRecord[1]);
                data.setCode(nextRecord[2]);
                data.setDisplayValue(nextRecord[3]);
                data.setLongDescription(nextRecord[4]);
                data.setFromDate(nextRecord[5]);
                data.setToDate(nextRecord[6]);
                data.setSortingPriority(nextRecord[7]);
                dataList.add(data);
            }
            repository.saveAll(dataList);

        } catch (CsvValidationException e) {
            log.error(e.getMessage());
            throw new InvalidInputException(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new InvalidInputException(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException();
        }
    }
}

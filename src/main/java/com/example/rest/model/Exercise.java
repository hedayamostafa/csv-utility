package com.example.rest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String source;
    private String codeListCode;
    private String code;
    private String displayValue;
    private String longDescription;
    private String fromDate;
    private String toDate;
    private String sortingPriority;
}

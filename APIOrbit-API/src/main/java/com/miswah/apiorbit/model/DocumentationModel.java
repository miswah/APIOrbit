package com.miswah.apiorbit.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name="docs")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "api", nullable = false)
    private ApiModel api;

    @Lob
    @Column(name = "raw_text", columnDefinition="LONGTEXT")
    private String text;
}

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
    @JoinColumn(name = "api_id", nullable = false)
    private ApiModel apiModel;

    @Column(name = "raw_text")
    private String text;
}

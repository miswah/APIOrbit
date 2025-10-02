package com.miswah.apiorbit.model;

import com.miswah.apiorbit.enums.AuthTypeNames;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "auth_type_model")
public class AuthTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "auth_type")
    private AuthTypeNames typeNames;
}

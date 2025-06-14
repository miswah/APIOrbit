package com.miswah.apiorbit.model;

import com.miswah.apiorbit.enums.AuthTypeNames;
import jakarta.persistence.*;

@Entity
@Table(name = "auth_type_model")
public class AuthTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "auth_type")
    private AuthTypeNames typeNames;
}

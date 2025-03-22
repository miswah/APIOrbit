package com.miswah.apiorbit.model;

import com.miswah.apiorbit.enums.AuthTypeNames;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "auth_type_model")
public class AuthTypeModel {

    @Id
    private int id;

    @Column(name = "auth_type")
    private AuthTypeNames typeNames;
}

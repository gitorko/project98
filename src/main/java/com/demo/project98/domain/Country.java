package com.demo.project98.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    String code;
    String name;
    String capital;
}

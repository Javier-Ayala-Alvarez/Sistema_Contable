package com.SistemaContable.Controller.dto;

import lombok.Data;

@Data
public class UsuarioRegistroDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;


}

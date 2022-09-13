//Este es una clase que va a reguardar los datos que trae el fron-end en los registros de usuario

package com.SistemaContable.model.dto;

import lombok.Data;

@Data
public class UsuarioRegistroDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;


}

package com.turismo.CTG.model.dto;


import com.turismo.CTG.model.entity.Estado;
import com.turismo.CTG.model.entity.Perfil;
import com.turismo.CTG.model.entity.TipoIdentificacion;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto implements Serializable {

    private Integer idUsuario;
    private TipoIdentificacion tipoIdentificacion;
    private Integer identificacion;
    private String nombre;
    private String apellidos;
    private String correo;
    private Integer telefono;
    private String nombreUsuario;
    private String contrasena;
    private Estado estado;
    private PerfilDto perfil;

}

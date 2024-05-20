package com.turismo.CTG.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "categorias")
public class Categoria implements Serializable {

    @Id
    @Column(name = "id_categoria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoia;

    @Column(name = "nombre_categoria")
    private String nombreCategoria;
}

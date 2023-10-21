package com.backend.reservetion.Barbershop.domain;

import com.backend.reservetion.Barbershop.dtos.UserDTOs;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Users")
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User { // Entidade User do banco de dados. 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) /// Vai gerar um Id de forma automática
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true) // Anotação que define o email como unico no banco de dados.
    private String email;

    public User(UserDTOs userDTOs){ // Construtor para dar valor aos atributos. Passamos um Dto por parâmetro, pois o valor do atributo vai ser um DTO.
        this.firstName = userDTOs.firstName();
        this.lastName = userDTOs.lastName();
        this.email = userDTOs.email();

    }
}

package com.backend.reservetion.Barbershop.domain;

import com.backend.reservetion.Barbershop.dtos.ActivityDTOs;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity // Indica que a classe é uma entidade que pode ser mapeada com banco de dados.
@Table(name = "activity_table") // Especifica o nome da tabela no banco de dados.
// Gerando métodos getters e setters automáticamente no banco de dados.
@Getter
@Setter
// Construtores para criar objetos da classe com todos os argumentos, ou instanciar objetos da classe sem os argumentos.
@AllArgsConstructor
@NoArgsConstructor
public class Activity { // Tipo de serviço a ser realizado, marcação de horário.
    @Id // Chave primária da tabela do banco de dados.
    @GeneratedValue(strategy = GenerationType.AUTO) // Especifica que o id será gerado de forma automática.
    private Long id;
    @Column(unique = true) // Definindo está data como única. Ou seja, não poderá ser duplicada.
    private LocalDateTime dateTime;
    @Enumerated(EnumType.STRING) // Definindo este atributo como uma enumeração e que será uma String no banco de dados.
    private ActivityType activityType;
    private double valueType;
    @JoinColumn // Usado para definir a coluna no banco de dados que armazena a chave estrangeira para a tabela User.
    @ManyToOne // Indica que a instancia activity está associada a uma instancia User.
    private User user;
    private String userName;
    
    public Activity (ActivityDTOs activityDTOs){ //Construtor. (Valores dos atributos serão os dtos)
        this.user = activityDTOs.user();
        this.dateTime = activityDTOs.dateTime();
        this.activityType = activityDTOs.activityType();
    }

}

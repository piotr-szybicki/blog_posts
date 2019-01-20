package com.orm.udemy.mysql.repos.entities.separate.sequence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "actor_separate_sequence")
@Table(name = "actor_separate_sequence")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Actor {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private Integer age;

    private String lastName;
}

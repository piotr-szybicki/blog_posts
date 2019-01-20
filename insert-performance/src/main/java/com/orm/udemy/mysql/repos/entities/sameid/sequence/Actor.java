package com.orm.udemy.mysql.repos.entities.sameid.sequence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "actor_same_sequence")
@Table(name = "actor_same_sequence")
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

package com.gerry.assesment.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    private String id;
    private String title;
    private String firstName;
    private String surname;
    private LocalDate dateOfBirth;
    private String jobTitle;
    private LocalDateTime created;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return 562048007;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"title\":\"" + title + '\"' +
                ", \"firstName\":\"" + firstName + '\"' +
                ", \"surname\":\"" + surname + '\"' +
                ", \"dateOfBirth\":\"" + dateOfBirth.toString() +"\""+
                ", \"jobTitle\":\"" + jobTitle + '\"' +
                ", \"created\":\"" + created.toString() + "\"" +
                '}';
    }
}

package ru.job4j.hibernate.candidate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    public static Vacancy of(String name, String description) {
        Vacancy v = new Vacancy();
        v.name = name;
        v.description = description;
        return v;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Vacancy{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + '}';
    }
}
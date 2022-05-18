package ru.job4j.hibernate.model.carlazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * 3. LazyInitializationexception [#331987]
 * Mark модель данных описывает марку автомобилей.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 12.05.2022
 */
@Entity
@Table(name = "marks")
public class MarkL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @OneToMany(mappedBy = "mark")
    private List<ModelL> models = new ArrayList<>();

    public static MarkL of(String name) {
        MarkL mark = new MarkL();
        mark.name = name;
        return mark;
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

    public List<ModelL> getModels() {
        return models;
    }

    public void addModel(ModelL model) {
        models.add(model);
    }

    public void setModel(List<ModelL> models) {
        this.models = models;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MarkL mark = (MarkL) o;
        return id == mark.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Mark{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
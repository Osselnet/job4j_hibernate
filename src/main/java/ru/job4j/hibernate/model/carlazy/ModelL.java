package ru.job4j.hibernate.model.carlazy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "models")
public class ModelL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn(name = "mark_id")
    private MarkL mark;

    public static ModelL of(String name, MarkL mark) {
        ModelL model = new ModelL();
        model.name = name;
        model.mark = mark;
        return model;
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

    public MarkL getMark() {
        return mark;
    }

    public void setMark(MarkL mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ModelL model = (ModelL) o;
        return id == model.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Model{" + "id=" + id + ", name='" + name + '\'' + ", mark=" + mark + '}';
    }
}

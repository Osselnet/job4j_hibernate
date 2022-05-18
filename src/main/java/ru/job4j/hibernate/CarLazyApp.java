package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hibernate.model.carlazy.MarkL;
import ru.job4j.hibernate.model.carlazy.ModelL;

import java.util.ArrayList;
import java.util.List;

public class CarLazyApp {
    public static void main(String[] args) {
        List<MarkL> marks = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            Transaction tr = session.beginTransaction();
            loadModel(session);
            marks = session.createQuery("from Mark").list();
            for (MarkL mark : marks) {
                for (ModelL model : mark.getModels()) {
                    System.out.println(model);
                }
            }
            tr.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    private static void loadModel(Session session) {
        MarkL ladaMark = MarkL.of("Lada");
        ModelL ladaNiva = ModelL.of("Niva", ladaMark);
        ModelL ladaVesta = ModelL.of("Vesta", ladaMark);
        ModelL ladaLargus = ModelL.of("Largus", ladaMark);
        ModelL ladaGranta = ModelL.of("Granta", ladaMark);
        ModelL ladaXRay = ModelL.of("xRay", ladaMark);
        ladaMark.addModel(ladaNiva);
        ladaMark.addModel(ladaVesta);
        ladaMark.addModel(ladaLargus);
        ladaMark.addModel(ladaGranta);
        ladaMark.addModel(ladaXRay);
        MarkL kiaMark = MarkL.of("Kia");
        ModelL kiaRio = ModelL.of("Rio", kiaMark);
        ModelL kiaCeed = ModelL.of("Ceed", kiaMark);
        ModelL kiaK5 = ModelL.of("K5", kiaMark);
        kiaMark.addModel(kiaRio);
        kiaMark.addModel(kiaCeed);
        kiaMark.addModel(kiaK5);
        session.save(kiaMark);
        session.save(ladaMark);
    }
}
package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hibernate.model.car.Mark;
import ru.job4j.hibernate.model.car.Model;

public class CarApp {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            Transaction tr = session.beginTransaction();

            Model nivaModel = Model.of("Niva");
            Model vestaModel = Model.of("Vesta");
            Model largusModel = Model.of("Largus");
            Model grantaModel = Model.of("Granta");
            Model xRayModel = Model.of("xRay");

            Mark ladaMark = Mark.of("Lada");
            ladaMark.addCModel(nivaModel);
            ladaMark.addCModel(vestaModel);
            ladaMark.addCModel(largusModel);
            ladaMark.addCModel(grantaModel);
            ladaMark.addCModel(xRayModel);
            session.save(ladaMark);

            tr.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}

package ru.job4j.hibernate.candidate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Run {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Candidate candidate1 = Candidate.of("Данил", "Нет опыта", 500.45);
            Candidate candidate2 = Candidate.of("Егор", "Нет опыта", 400.98);
            Candidate candidate3 = Candidate.of("Виктор", "Опыт 2 года", 1500);
            Candidate candidate4 = Candidate.of("Игорь", "Опыт 5 лет", 5000);

            session.persist(candidate1);
            session.persist(candidate2);
            session.persist(candidate3);
            session.persist(candidate4);

            Vacancy vacancy1 = Vacancy.of("Junior", "Description1");
            Vacancy vacancy2 = Vacancy.of("Middle", "Description2");
            Vacancy vacancy3 = Vacancy.of("Senior", "Description3");
            session.save(vacancy1);
            session.save(vacancy2);
            session.save(vacancy3);

            DBVacancy db = DBVacancy.of("base1");
            db.setVacancies(List.of(vacancy1, vacancy2, vacancy3));
            session.save(db);

            candidate1.setDbVacancy(db);

            session.createQuery(
                    "select distinct c from Candidate c "
                            + "join fetch c.dbVacancy db "
                            + "join fetch db.vacancies v "
                            + "where c.id = :cId", Candidate.class)
                    .setParameter("cId", 5).uniqueResult();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import static javax.ws.rs.client.Entity.entity;

/**
 *
 * @author olive
 */
public class EmployeeFacade {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EmployeeFacade employeeFacade;

    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory factory) {
        if (employeeFacade == null) {
            employeeFacade = new EmployeeFacade();
            emf = factory;
            return employeeFacade;
        }
        return employeeFacade;
    }

    public Employee getEmployeeById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Employee employee = em.find(Employee.class, id);
            em.getTransaction().commit();
            return employee;
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> tq = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class);
            tq.setParameter("name", name);
            List<Employee> employees = tq.getResultList();
            return employees;
        } finally {
            em.close();
        }
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> tq = em.createQuery("SELECT e FROM Employee e", Employee.class);
            List<Employee> employees = tq.getResultList();
            return employees;
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesWithHigestSalary() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> tq = em.createQuery("ELECT e FROM Employee e WHERE e.salary = (SELECT MAX(a.salary) FROM Employee a)", Employee.class);
            List<Employee> employees = tq.getResultList();
            return employees;
        } finally {
            em.close();
        }
    }

    public void createEmployee(String name, String address, BigDecimal salary) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee(name, address, salary));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}

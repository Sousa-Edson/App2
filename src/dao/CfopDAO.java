package dao;
 
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Cfop;

public class CfopDAO {

    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev-up");
        return factory.createEntityManager();
    }

    public Cfop Salvar(Cfop cfop) throws Exception {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            if (cfop.getId() == null) {
                em.persist(cfop); // executa inserir
            } else {
                if (!em.contains(cfop)) {
                    if (em.find(Cfop.class, cfop.getId()) == null) {
                        throw new Exception("Erro ao atualizar!!");
                    }
                }
                cfop = em.merge(cfop); // executa update
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return cfop;
    }

    public Cfop remover(Long id) {
        EntityManager em = getEM();
        Cfop cfop = em.find(Cfop.class, id);
        try {
            em.getTransaction().begin();
            em.remove(cfop);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return cfop;
    }

    public Cfop consultaPorId(Long id) {
        EntityManager em = getEM();
        Cfop cfop = null;
        try {
            cfop = em.find(Cfop.class, id);
        } finally {
            em.close();
        }
        return cfop;
    }

    public List<Cfop> consultarTodos() {
        EntityManager em = getEM();
        List<Cfop> cfops;
        try {
            Query q = em.createNamedQuery("Cfop.consultaTodos");
            cfops = q.getResultList();
        } catch (Exception e) {
            cfops = new ArrayList();
        } finally {
            em.close();
        }
        return cfops;
    }
}

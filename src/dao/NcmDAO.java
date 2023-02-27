package dao;
 
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Ncm;

public class NcmDAO {

    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev-up");
        return factory.createEntityManager();
    }

    public Ncm Salvar(Ncm ncm)  {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            if (ncm.getId() == null) {
                em.persist(ncm); // executa inserir
            } else {
                if (!em.contains(ncm)) {
                    if (em.find(Ncm.class, ncm.getId()) == null) {
                        
                    }
                }
                ncm = em.merge(ncm); // executa update
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return ncm;
    }

    public Ncm remover(Long id) {
        EntityManager em = getEM();
        Ncm ncm = em.find(Ncm.class, id);
        try {
            em.getTransaction().begin();
            em.remove(ncm);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return ncm;
    }

    public Ncm consultaPorId(Long id) {
        EntityManager em = getEM();
        Ncm ncm = null;
        try {
            ncm = em.find(Ncm.class, id);
        } finally {
            em.close();
        }
        return ncm;
    }

     public List<Ncm> consultarTodos(String txt) {
        EntityManager em = getEM();
        List<Ncm> ncms;
        try {
            Query q =em.createQuery("SELECT u FROM Ncm u  WHERE (coalesce((id)) ||' '||coalesce((nome)) ||' '||coalesce((descricao)))LIKE '%"+txt+"%' ORDER BY id ASC " );
            ncms = q.getResultList();
        } catch (Exception e) {
            ncms = new ArrayList();
        } finally {
            em.close();
        }
        return ncms;
    }
}

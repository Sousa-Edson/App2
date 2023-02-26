 
package dao;
 
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Nota;

 
public class NotaDAO {
     public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev-up");
        return factory.createEntityManager();
    }

    public Nota Salvar(Nota nota) throws Exception {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            if (nota.getId() == null) {
                em.persist(nota); // executa inserir
            } else {
                if (!em.contains(nota)) {
                    if (em.find(Nota.class, nota.getId()) == null) {
                        throw new Exception("Erro ao atualizar!!");
                    }
                }
                nota = em.merge(nota); // executa update
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return nota;
    }

    public Nota remover(Long id) {
        EntityManager em = getEM();
        Nota nota = em.find(Nota.class, id);
        try {
            em.getTransaction().begin();
            em.remove(nota);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return nota;
    }

    public Nota consultaPorId(Long id) {
        EntityManager em = getEM();
        Nota nota = null;
        try {
            nota = em.find(Nota.class, id);
        } finally {
            em.close();
        }
        return nota;
    }

    public List<Nota> consultarTodos() {
        EntityManager em = getEM();
        List<Nota> notas;
        try {
            Query q = em.createNamedQuery("Nota.consultaTodos");
            notas = q.getResultList();
        } catch (Exception e) {
            notas = new ArrayList();
        } finally {
            em.close();
        }
        return notas;
    }
}

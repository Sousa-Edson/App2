 
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Unidade;

 
public class UnidadeDAO {
      public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev-up");
        return factory.createEntityManager();
    }

    public Unidade Salvar(Unidade unidade)   {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            if (unidade.getId() == null) {
                em.persist(unidade); // executa inserir
            } else {
                if (!em.contains(unidade)) {
                    if (em.find(Unidade.class, unidade.getId()) == null) {
//                        throw new Exception("Erro ao atualizar!!");
                    }
                }
                unidade = em.merge(unidade); // executa update
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return unidade;
    }
     public Unidade remover(Long id) {
        EntityManager em = getEM();
        Unidade unidade = em.find(Unidade.class, id);
        try {
            em.getTransaction().begin();
            em.remove(unidade);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return unidade;
    }

    public Unidade consultaPorId(Long id) {
        EntityManager em = getEM();
        Unidade unidade = null;
        try {
            unidade = em.find(Unidade.class, id);
        } finally {
            em.close();
        }
        return unidade;
    }

    public List<Unidade> consultarTodos() {
        EntityManager em = getEM();
        List<Unidade> unidades;
        try {
            Query q = em.createNamedQuery("Unidade.consultaTodos");
            unidades = q.getResultList();
        } catch (Exception e) {
            unidades = new ArrayList();
        } finally {
            em.close();
        }
        return unidades;
    }
}

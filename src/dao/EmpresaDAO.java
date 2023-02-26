 
package dao;
 
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Empresa;

 
public class EmpresaDAO {
      public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev-up");
        return factory.createEntityManager();
    }

    public Empresa Salvar(Empresa empresa) throws Exception {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            if (empresa.getId() == null) {
                em.persist(empresa); // executa inserir
            } else {
                if (!em.contains(empresa)) {
                    if (em.find(Empresa.class, empresa.getId()) == null) {
                        throw new Exception("Erro ao atualizar!!");
                    }
                }
                empresa = em.merge(empresa); // executa update
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return empresa;
    }

    public Empresa remover(Long id) {
        EntityManager em = getEM();
        Empresa empresa = em.find(Empresa.class, id);
        try {
            em.getTransaction().begin();
            em.remove(empresa);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return empresa;
    }

    public Empresa consultaPorId(Long id) {
        EntityManager em = getEM();
        Empresa empresa = null;
        try {
            empresa = em.find(Empresa.class, id);
        } finally {
            em.close();
        }
        return empresa;
    }

    public List<Empresa> consultarTodos() {
        EntityManager em = getEM();
        List<Empresa> empresas;
        try {
            Query q = em.createNamedQuery("Empresa.consultaTodos");
            empresas = q.getResultList();
        } catch (Exception e) {
            empresas = new ArrayList();
        } finally {
            em.close();
        }
        return empresas;
    }
}

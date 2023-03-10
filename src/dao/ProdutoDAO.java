package dao;
 
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Produto;

public class ProdutoDAO {

    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev-up");
        return factory.createEntityManager();
    }

    public Produto Salvar(Produto produto)  {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            if (produto.getId() == null) {
                em.persist(produto); // executa inserir
            } else {
                if (!em.contains(produto)) {
                    if (em.find(Produto.class, produto.getId()) == null) {
                       
                    }
                }
                produto = em.merge(produto); // executa update
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return produto;
    }

   public Produto remover(Long id) {
        EntityManager em = getEM();
        Produto produto = em.find(Produto.class, id);
        try {
            em.getTransaction().begin();
            em.remove(produto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return produto;
    }

    public Produto consultaPorId(Long id) {
        EntityManager em = getEM();
        Produto produto = null;
        try {
            produto = em.find(Produto.class, id);
        } finally {
            em.close();
        }
        return produto;
    }
    public List<Produto> consultarTodos(String txt) {
        EntityManager em = getEM();
        List<Produto> produtos;
        try {
            Query q =em.createQuery("SELECT u FROM Produto u  WHERE (coalesce((u.id)) ||' '||coalesce((u.nome)) ) LIKE '%"+txt+"%' ORDER BY id ASC " );
            produtos = q.getResultList();
        } catch (Exception e) {
            produtos = new ArrayList();
        } finally {
            em.close();
        }
        return produtos;
    }
}

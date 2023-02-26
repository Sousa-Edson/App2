 
package dao;
 
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Item;

 
public class ItemDAO {
     public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev-up");
        return factory.createEntityManager();
    }

    public Item Salvar(Item item) throws Exception {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            if (item.getId() == null) {
                em.persist(item); // executa inserir
            } else {
                if (!em.contains(item)) {
                    if (em.find(Item.class, item.getId()) == null) {
                        throw new Exception("Erro ao atualizar!!");
                    }
                }
                item = em.merge(item); // executa update
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return item;
    }

    public Item remover(Long id) {
        EntityManager em = getEM();
        Item item = em.find(Item.class, id);
        try {
            em.getTransaction().begin();
            em.remove(item);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return item;
    }

    public Item consultaPorId(Long id) {
        EntityManager em = getEM();
        Item item = null;
        try {
            item = em.find(Item.class, id);
        } finally {
            em.close();
        }
        return item;
    }

    public List<Item> consultarTodos() {
        EntityManager em = getEM();
        List<Item> items;
        try {
            Query q = em.createNamedQuery("Item.consultaTodos");
            items = q.getResultList();
        } catch (Exception e) {
            items = new ArrayList();
        } finally {
            em.close();
        }
        return items;
    }
}

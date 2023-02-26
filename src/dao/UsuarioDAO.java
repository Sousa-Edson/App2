package dao;
 
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Usuario;

public class UsuarioDAO {

    public EntityManager getEM() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev-up");
        return factory.createEntityManager();
    }

    public Usuario Salvar(Usuario usuario) throws Exception {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            if (usuario.getId() == null) {
                em.persist(usuario); // executa inserir
            } else {
                if (!em.contains(usuario)) {
                    if (em.find(Usuario.class, usuario.getId()) == null) {
                        throw new Exception("Erro ao atualizar!!");
                    }
                }
                usuario = em.merge(usuario); // executa update
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return usuario;
    }

    public Usuario remover(Long id) {
        EntityManager em = getEM();
        Usuario usuario = em.find(Usuario.class, id);
        try {
            em.getTransaction().begin();
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return usuario;
    }

    public Usuario consultaPorId(Long id) {
        EntityManager em = getEM();
        Usuario usuario = null;
        try {
            usuario = em.find(Usuario.class, id);
        } finally {
            em.close();
        }
        return usuario;
    }

    public List<Usuario> consultarTodos() {
        EntityManager em = getEM();
        List<Usuario> usuarios;
        try {
            Query q = em.createNamedQuery("Usuario.consultaTodos");
            usuarios = q.getResultList();
        } catch (Exception e) {
            usuarios = new ArrayList();
        } finally {
            em.close();
        }
        return usuarios;
    }

}

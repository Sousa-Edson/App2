package service;

import dao.CfopDAO;
import java.util.Date;
import java.util.List;
import model.Cfop;
import model.Usuario;

public class CfopService {

    Cfop n = new Cfop();
    CfopDAO dao = new CfopDAO();

    public void salvar() throws Exception {
        Date d = new Date();
        Usuario user = new Usuario();
        user.setId(1L);
        n.setNome("5948");
        n.setDescrição("Simples saida");
        n.setAtivo(true);
        n.setUsuario(user);
        n.setDataHora(d);
        n = dao.Salvar(n);
        System.out.println(n.toString() + "  data hora :  " + d);
    }

    public void deletar(Long id) {
        n.setId(id);
        n = dao.remover(n.getId());
        System.out.println(n.toString());
    }

    public void consultaPorId(Long id) {
        Cfop u = dao.consultaPorId(id);
        System.out.println(u.toString());
        System.out.println(u.getUsuario().getNome());
    }

    public void consultaTodos() {
        List<Cfop> cfops = dao.consultarTodos();
        for (Cfop u : cfops) {
            System.out.println(u.toString());
        }
    }
}

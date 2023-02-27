package service;

import dao.CfopDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Cfop;
import model.Usuario;
import view.CfopForm;

public class CfopService {

    Cfop beans = new Cfop();
    CfopDAO dao = new CfopDAO();

    public Cfop create(CfopForm view) {

        Date d = new Date();
        Usuario user = new Usuario();
        user.setId(1L);
        beans.setNome("" + view.getTxtNcm().getValue());
        beans.setDescricao(view.getTxtDescricao().getText());
        beans.setAtivo(true);
        beans.setUsuario(user);
        beans.setDataHora(d);
        return beans = dao.Salvar(beans);

    }

    public void salvar() throws Exception {
        Date d = new Date();
        Usuario user = new Usuario();
        user.setId(1L);
        beans.setNome("5948");
        beans.setDescricao("Simples saida");
        beans.setAtivo(true);
        beans.setUsuario(user);
        beans.setDataHora(d);
        beans = dao.Salvar(beans);
        System.out.println(beans.toString() + "  data hora :  " + d);
    }

    public void deletar(Long id) {
        beans.setId(id);
        beans = dao.remover(beans.getId());
        System.out.println(beans.toString());
    }

    public void consultaPorId(Long id) {
        Cfop u = dao.consultaPorId(id);
        System.out.println(u.toString());
        System.out.println(u.getUsuario().getNome());
    }

    public List<Cfop> consultaTodos() {
        List<Cfop> cfops = dao.consultarTodos();
        ArrayList dados = new ArrayList();
        for (Cfop u : cfops) {
            dados.add((new Object[]{u.getId(), u.getNome(), u.getDescricao()}));
        }
        return dados;
    }
}

package service;

import dao.CfopDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Cfop;
import model.Usuario;
import view.CfopForm;

public class CfopService {

    Cfop cfop = new Cfop();
    CfopDAO dao = new CfopDAO();

    public Cfop create(CfopForm view) {

        Date d = new Date();
        Usuario user = new Usuario();
        user.setId(1L);
        cfop.setNome("" + view.getTxtNcm().getValue());
        cfop.setDescricao(view.getTxtDescricao().getText());
        cfop.setAtivo(true);
        cfop.setUsuario(user);
        cfop.setDataHora(d);
        cfop.setId(view.getIdCfop());
        return cfop = dao.Salvar(cfop);

    }

    public void salvar() throws Exception {
        Date d = new Date();
        Usuario user = new Usuario();
        user.setId(1L);
        cfop.setNome("5948");
        cfop.setDescricao("Simples saida");
        cfop.setAtivo(true);
        cfop.setUsuario(user);
        cfop.setDataHora(d);
        cfop = dao.Salvar(cfop);
        System.out.println(cfop.toString() + "  data hora :  " + d);
    }

  public Cfop delete(CfopForm view) {
        cfop.setId(view.getIdCfop());
        return cfop = dao.remover(cfop.getId());
    }

     public Cfop consultaPorId(Long id) {
        return  dao.consultaPorId(id);
    }

    public List<Cfop> consultaTodos(String txt) {
        List<Cfop> cfops = dao.consultarTodos(txt);
        ArrayList dados = new ArrayList();
        for (Cfop u : cfops) {
            dados.add((new Object[]{u.getId(), u.getNome(), u.getDescricao()}));
        }
        return dados;
    }

}

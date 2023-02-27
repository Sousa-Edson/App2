package service;

import dao.UnidadeDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Unidade;
import model.Usuario;
import view.UnidadeForm;

public class UnidadeService {

    Unidade u = new Unidade();
    UnidadeDAO dao = new UnidadeDAO();

    public Unidade create(UnidadeForm view) {
        Date d = new Date();
        Usuario user = new Usuario();
        user.setId(1L);
        u.setNome(view.getTxtSigla().getText());
        u.setDescricao(view.getTxtDescricao().getText());
        u.setAtivo(true);
        u.setFragmentado(view.getCkFragmentado().isSelected());
        u.setUsuario(user);
        u.setDataHora(d);
        u.setId(view.getIdUnidade());
        return u = dao.Salvar(u);

    }

    public Unidade delete(UnidadeForm view) {
        u.setId(view.getIdUnidade());
        return u = dao.remover(u.getId());
    }

    /*divide*/
    

    public Unidade consultaPorId(Long id) {
        return  dao.consultaPorId(id);
    }

    public List<Unidade> consultaTodos(String txt) {
        List<Unidade> unidades = dao.consultarTodos(txt);
        ArrayList dados = new ArrayList();
        for (Unidade u : unidades) {
            dados.add((new Object[]{u.getId(), u.getNome(), u.getDescricao(), u.getFragmentado()}));
        } 
        return dados;
    }
     
}

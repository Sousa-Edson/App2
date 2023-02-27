package service;

import dao.NcmDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Ncm;
import model.Usuario;
import view.NcmForm;

public class NcmService {

    Ncm ncm = new Ncm();
    NcmDAO dao = new NcmDAO();

    public Ncm create(NcmForm view) {
        Date d = new Date();
        Usuario user = new Usuario();
        user.setId(1L);
        ncm.setNome("" + view.getTxtNcm().getValue());
        ncm.setDescricao(view.getTxtDescricao().getText());
        ncm.setAtivo(true);
        ncm.setUsuario(user);
        ncm.setDataHora(d);
        ncm.setId(view.getIdNcm());
        return ncm = dao.Salvar(ncm);

    }
 public Ncm delete(NcmForm view) {
        ncm.setId(view.getIdNcm());
        return ncm = dao.remover(ncm.getId());
    }

     public Ncm consultaPorId(Long id) {
        return dao.consultaPorId(id);
    }

   public List<Ncm> consultaTodos(String txt) {
        List<Ncm> ncms = dao.consultarTodos(txt);
        ArrayList dados = new ArrayList();
        for (Ncm u : ncms) {
            dados.add((new Object[]{u.getId(), u.getNome(), u.getDescricao()}));
        }
        return dados;
    }
}

package service;

import conversor.FormataData;
import dao.NotaDAO;
import java.util.Date;
import java.util.List;
import model.Cfop;
import model.Empresa;
import model.Nota;
import model.Usuario;

public class NotaService {

    Nota n = new Nota();
    NotaDAO dao = new NotaDAO();
    FormataData fd = new FormataData();

    public void salvar() throws Exception {
        Date d = new Date();
        Usuario user = new Usuario();
        user.setId(1L);
        Empresa emp = new Empresa();
        emp.setId(1L);
        n.setNumero("001");
        n.setChave("000001000001");
        n.setDataRecebimento(fd.dataFormato("12/01/2023 09:00"));
        n.setTipo(0);
        n.setEmpresa(emp);
        n.setAtivo(true);
        n.setUsuario(user);
        n.setDataHora(d);
        Cfop cfop = new Cfop();
        cfop.setId(1L);
        n.setCfop(cfop);
        n = dao.Salvar(n);
        System.out.println(n.toString() + "  data hora :  " + d);
    }

    public void deletar(Long id) {
        n.setId(id);
        n = dao.remover(n.getId());
        System.out.println(n.toString());
    }

    public void consultaPorId(Long id) {
        Nota u = dao.consultaPorId(id);
        System.out.println(u.toString());
        System.out.println(u.getUsuario().getNome());
        System.out.println(u.getEmpresa().getRazaoSocial());
    }

    public void consultaTodos() {
        List<Nota> notas = dao.consultarTodos();
        for (Nota u : notas) {
            System.out.println(u.toString());
        }
    }
}

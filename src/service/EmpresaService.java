package service;

import dao.EmpresaDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Empresa;
import model.Endereco;
import model.Telefone;
import model.Usuario;

public class EmpresaService {

    Empresa e = new Empresa();
    EmpresaDAO dao = new EmpresaDAO();

    public void salvar() throws Exception {
        Date d = new Date();
        Usuario user = new Usuario();
        user.setId(1L);

        e.setNomeFantasia("Empresa B");
        e.setRazaoSocial("Empresa B LTDA");
        e.setCnpj("04.616.931/0001-70");
        e.setIe("30303030");
        e.setAtivo(true);
        e.setUsuario(user);
        e.setDataHora(d);
        Endereco endereco = new Endereco();
        endereco.setRua("Rua dois");
        endereco.setComplemento("Casa verde");
        endereco.setBairro("Isaura");
        endereco.setCidade("Pq Santana");
        endereco.setEstado("SP");
        endereco.setCep("06100-010");
        e.setEndereco(endereco);
        List<Telefone> t = new ArrayList();
        Telefone telefone = new Telefone();
        telefone.setTelefone("(011)4168-3085");
        telefone.setEmpresa(e);
        t.add(telefone);
        e.setTelefones(t);
        e = dao.Salvar(e);
        System.out.println(e.toString() + "  data hora :  " + d);
    }

    public void deletar(Long id) {
        e.setId(id);
        e = dao.remover(e.getId());
        System.out.println(e.toString());
    }

    public void consultaPorId(Long id) {
        Empresa u = dao.consultaPorId(id);
        System.out.println(u.toString());
        System.out.println(u.getUsuario().getNome());
        System.out.println(u.getEndereco().getRua());
    }

    public void consultaTodos() {
        List<Empresa> empresas = dao.consultarTodos();
        for (Empresa u : empresas) {
            System.out.println(u.toString());
        }
    }
}

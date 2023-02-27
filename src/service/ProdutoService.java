package service;

import conversor.Conversor;
import dao.ProdutoDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Ncm;
import model.Produto;
import model.Unidade;
import model.Usuario;
import view.ProdutoForm;

public class ProdutoService {
  Produto produto = new Produto();
    ProdutoDAO dao = new ProdutoDAO();
     Conversor conversor;


    public Produto create(ProdutoForm view) {
        Date d = new Date();
        Usuario user = new Usuario();
        user.setId(1L);
        Unidade unidade = new Unidade();
        unidade.setId(2L);
        Ncm ncm = new Ncm();
        ncm.setId(2L);
        produto.setNome(view.getTxtDescricao().getText());
          produto.setObservacao(view.getTxtInformacao().getText());
          produto.setValor(conversor.formataMilhar.toStringForDouble(view.getTxtValor().getText()));
        produto.setAtivo(true);
        produto.setUsuario(user);
        produto.setUnidade(unidade);
        produto.setNcm(ncm);
        produto.setDataHora(d);
        produto.setId(view.getIdProduto());
        return produto = dao.Salvar(produto);

    }

    public Produto delete(ProdutoForm view) {
        produto.setId(view.getIdProduto());
        return produto = dao.remover(produto.getId());
    }

    public Produto consultaPorId(Long id) {
        return dao.consultaPorId(id);
    }

    public List<Produto> consultaTodos(String txt) {
        List<Produto> produtos = dao.consultarTodos(txt);
        ArrayList dados = new ArrayList();
        for (Produto u : produtos) {
            dados.add((new Object[]{u.getId(), "", u.getNome(), u.getUnidade(), conversor.formataDinheiro.toDoubleForStringMoney(u.getValor())}));
        } 
        System.out.println(produtos.toString());
        return dados;
    }
}

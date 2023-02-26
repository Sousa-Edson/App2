package service;

import dao.ItemDAO;
import enums.StatusEnum;
import java.util.Date;
import java.util.List;
import model.Item;
import model.Nota;
import model.Produto;
import model.Usuario;

public class ItemService {

    Item item = new Item();
    ItemDAO dao = new ItemDAO();

    public void salvar() throws Exception {
        Date d = new Date();
        Usuario user = new Usuario();
        user.setId(1L);
        item.setUsuario(user);
        item.setDataHora(d);
        item.setComplemento(" envelopado");
        Produto produto = new Produto();
        produto.setId(1L);
        item.setProduto(produto);
        item.setQuantidade(100.0);
        item.setStatus(StatusEnum.ENVIADO);
        item.setAtivo(true);
        Nota nota = new Nota();
        nota.setId(1L);
        item.setNota(nota);
        item = dao.Salvar(item);
        System.out.println(item.toString() + "  data hora :  " + d);
    }

    public void deletar(Long id) {
        item.setId(id);
        item = dao.remover(item.getId());
        System.out.println(item.toString());
    }

    public void consultaPorId(Long id) {
        Item u = dao.consultaPorId(id);
        System.out.println(u.toString());
        System.out.println(u.getUsuario().getNome());
        System.out.println(u.getProduto().getNome());
    }

    public void consultaTodos() {
        List<Item> items = dao.consultarTodos();
        for (Item u : items) {
            System.out.println(u.toString());
        }
    }
}

package controller;

import conversor.Conversor;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.ModeloTabela;
import model.Ncm;
import model.Produto;
import model.Unidade;
import service.NcmService;
import service.ProdutoService;
import service.UnidadeService;
import view.ProdutoForm;

public class ProdutoController {

    private final ProdutoForm view;
    ProdutoService service = new ProdutoService();
    Conversor conversor;

    public ProdutoController(ProdutoForm view) {
        this.view = view;
    }

    public void clear() {
        view.getTxtDescricao().setText(null);
        view.getTxtInformacao().setText(null);
        view.getTxtPesquisa().setText(null);
        view.getTxtValor().setText("0");
        view.getTxtDescricao().requestFocus();
        view.getBtnSalvar().setEnabled(false);
        view.getBtnSalvar().setText("Salvar");
        view.setIdProduto(0L);
        view.getBtnExcluir().setVisible(false);
        loadTable("");
    }

    public void create() {
        Produto u = service.create(view);
        if (u.getId() >= 1) {
            clear();
            u.setId(null);
        }
    }

    public void destroy(Long id) {
        Produto u = service.delete(view);
        if (u.getId() >= 1) {
            clear();
            u.setId(null);
        }
    }

    public void loadTable(String texto) {
        String[] colunas = new String[]{"Id", "Tipo", "Descrição", "Un", "Valor R$"};
        ArrayList dados;

        dados = (ArrayList) service.consultaTodos(texto);

        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        view.getTabela().setModel(modelo);
        RowSorter<TableModel> sorter = new TableRowSorter<>(modelo);
        view.getTabela().setRowSorter(sorter);
        view.getTabela().getColumnModel().getColumn(0).setResizable(true);
        view.getTabela().getColumnModel().getColumn(1).setResizable(true);
        view.getTabela().getColumnModel().getColumn(2).setResizable(true);
        view.getTabela().getTableHeader().setReorderingAllowed(false);
        view.getTabela().getColumnModel().getColumn(3).setResizable(true);
        view.getTabela().getTableHeader().setReorderingAllowed(false);
        view.getTabela().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        view.getTabela().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public void pressKeys() {
        if (view.getTxtDescricao().getText().length() >= 2 && view.getTxtValor().getText().length() >= 1) {
            view.getBtnSalvar().setEnabled(true);
        } else {
            view.getBtnSalvar().setEnabled(false);
        }
    }

    public void loadRecord(Long id) {
        Produto p = service.consultaPorId(id);
        view.setIdProduto(id);
        view.getTxtDescricao().setText(p.getNome());
        view.getTxtInformacao().setText(p.getObservacao());
        view.getTxtValor().setText(conversor.formataDinheiro.toDoubleForStringMoney(p.getValor()));
//            PreencheUnidade(p.getUnidade());
//            PreencheNcm(p.getNcm());
//            PreencheTipo(p.getTipo());
        view.getTxtDataHora().setText("" + p.getDataHora());
        view.getTxtNomeUsuario().setText(p.getUsuario().getNome());

        pressKeys();
        view.getBtnSalvar().setText("Atualizar");
    }
// public String DataAtual(Date dt) {
//        return "Atualizado em: " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(dt);
//    }

    public void PreencheUnidade(int id) {
        view.getCbUnidade().removeAllItems();
        UnidadeService DAOU = new UnidadeService();
        for (Unidade u : DAOU.buscaTodos("")) {
            view.getCbUnidade().addItem(u);
        }
    }

    public void PreencheNcm(int id) {
        view.getCbNcm().removeAllItems();
        NcmService DAOU = new NcmService();
        for (Ncm u : DAOU.buscaTodos("")) {
            view.getCbNcm().addItem(u);
        }
    }

    public void PreencheTipo(int id) {
//        view.getCbTipo().removeAllItems();
//        TipoDao DAOU = new TipoDao();
//        for (TipoBeans u : DAOU.PreencheTipo(id)) {
//            view.getCbTipo().addItem(u);
//        }
    }

}

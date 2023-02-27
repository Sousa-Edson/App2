package controller;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.ModeloTabela;
import model.Unidade;
import service.UnidadeService;
import view.UnidadeForm;

public class UnidadeController {

    private final UnidadeForm view;

    UnidadeService service = new UnidadeService();

    public UnidadeController(UnidadeForm view) {
        this.view = view;
    }

    public void clear() {
        view.getTxtSigla().setText(null);
        view.getTxtDescricao().setText(null);
        view.getTxtPesquisa().setText(null);
        view.getCkFragmentado().setSelected(false);
        view.getTxtSigla().requestFocus();
        view.getBtnSalvar().setEnabled(false);
        view.getBtnSalvar().setText("Salvar");
        view.setIdUnidade(0L);
        view.getBtnExcluir().setVisible(false);
        view.getTxtDataHora().setText(" ");
        view.getTxtNomeUsuario().setText(" ");
        loadTable("");

    }

    public void create() throws Exception {
        Unidade u = service.create(view);
        if (u.getId() >= 1) {
            clear();
            u.setId(null);
        }
    }

    public void destroy(Long id) {
        Unidade u = service.delete(view);
        if (u.getId() >= 1) {
            clear();
            u.setId(null);
        }
    }

    public void loadTable(String texto) {
        String[] colunas = new String[]{"Id", "Sigla", "Descrição", "Fragmentado"};
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
        if (view.getTxtSigla().getText().length() >= 2 && view.getTxtDescricao().getText().length() > 2) {
            view.getBtnSalvar().setEnabled(true);
        } else {
            view.getBtnSalvar().setEnabled(false);
        }
    }

    public void loadRecord(Long id) {
//        System.out.println(  service.consultaPorId(id));
        Unidade u = service.consultaPorId(id);
//        for (Unidade u : service.consultaPorId(id)) {
        view.setIdUnidade(id);
        view.getTxtSigla().setText(u.getNome());
        view.getTxtDescricao().setText(u.getDescricao());
        view.getCkFragmentado().setSelected(u.getFragmentado());

        view.getTxtDataHora().setText("" + u.getDataHora());
        view.getTxtNomeUsuario().setText("teste");
//        }
        pressKeys();
        view.getBtnSalvar().setText("Atualizar");
    }

}

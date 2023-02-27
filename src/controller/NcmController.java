package controller;

import static java.nio.file.Files.delete;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.ModeloTabela;
import model.Ncm;
import service.NcmService;

import view.NcmForm;

public class NcmController {

    private final NcmForm view;
    NcmService service = new NcmService();

    public NcmController(NcmForm view) {
        this.view = view;

    }

    public void clear() {
        view.getTxtNcm().setValue(0);
        view.getTxtDescricao().setText(null);
        view.getTxtPesquisa().setText(null);
        view.getTxtNcm().requestFocus();
        view.getBtnSalvar().setEnabled(false);
        view.getBtnSalvar().setText("Salvar");
        view.setIdNcm(0L);
        view.getBtnExcluir().setVisible(false);
        view.getTxtDataHora().setText(" ");
        view.getTxtNomeUsuario().setText(" ");
        loadTable("", false);

    }

    public void create() {
        Ncm u = service.create(view);
        if (u.getId() >= 1) {
            clear();
            u.setId(null);
        }
    }

    public void destroy(Long id) {
        Ncm u = service.delete(view);
        if (u.getId() >= 1) {
            clear();
            u.setId(null);
        }
    }

    public void loadTable(String texto, boolean verdade) {
        String[] colunas = new String[]{"Id", "Ncm", "Descrição"};
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
        view.getTabela().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        view.getTabela().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public void pressKeys() {
        if (view.getTxtDescricao().getText().length() > 2) { //view.getTxtNcm().getText().length() >= 2 && 
            view.getBtnSalvar().setEnabled(true);
        } else {
            view.getBtnSalvar().setEnabled(false);
        }
    }

    public void loadRecord(Long id) {
        Ncm u = service.consultaPorId(id);
        view.setIdNcm(id);
        view.getTxtNcm().setValue(Integer.parseInt(u.getNome().trim()));
        view.getTxtDescricao().setText(u.getDescricao());
        view.getTxtDataHora().setText("" + u.getDataHora());
        view.getTxtNomeUsuario().setText(u.getUsuario().getNome());
        pressKeys();
        view.getBtnSalvar().setText("Atualizar");
    }

//    public String DataAtual(Date dt) {
//        return "Atualizado em: " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(dt);
//    }
}

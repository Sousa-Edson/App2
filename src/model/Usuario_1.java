package model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
    @NamedQuery(name = "Usuario.consultaTodos",
            query = "SELECT u FROM Usuario u ")

})

public class Usuario_1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String login;
    private String senha;

    private Boolean ativo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hora")
    private Date dataHora;

    @OneToMany(mappedBy = "usuario")
    private List<Unidade> unidades;

    @OneToMany(mappedBy = "usuario")
    private List<Ncm> ncms;

    @OneToMany(mappedBy = "usuario")
    private List<Empresa> empresas;

    @OneToMany(mappedBy = "usuario")
    private List<Cfop> cfops;

    @OneToMany(mappedBy = "usuario")
    private List<Produto> produtos;

    @OneToMany(mappedBy = "usuario")
    private List<Nota> notas;

    @OneToMany(mappedBy = "usuario")
    private List<Item> itens;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public List<Unidade> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidade> unidades) {
        this.unidades = unidades;
    }

    public List<Ncm> getNcms() {
        return ncms;
    }

    public void setNcms(List<Ncm> ncms) {
        this.ncms = ncms;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public List<Cfop> getCfops() {
        return cfops;
    }

    public void setCfops(List<Cfop> cfops) {
        this.cfops = cfops;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", login=" + login + ", senha=" + senha + ", dataHora=" + dataHora + '}';
    }

}

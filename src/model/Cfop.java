package model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cfop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Boolean ativo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hora")
    private Date dataHora;
    @ManyToOne()
    private Usuario usuario;

//    @OneToMany(mappedBy = "cfop")
//    private List<Nota> notas;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

   

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

//    public List<Nota> getNotas() {
//        return notas;
//    }
//
//    public void setNotas(List<Nota> notas) {
//        this.notas = notas;
//    }

    @Override
    public String toString() {
        return "Cfop{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", ativo=" + ativo + ", dataHora=" + dataHora + '}';
    }

   
}

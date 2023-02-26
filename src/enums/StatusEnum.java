package enums;

public enum StatusEnum {
    FAZENDO(1, "A fazer"),
    FEITO(2, "Pronto"),
    AGUARDANDO(3, "Aguardando"),
    ENVIADO(4, "Enviado");

    private final int id;
    private final String descricao;

    private StatusEnum(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static StatusEnum getFAZENDO() {
        return FAZENDO;
    }

    public static StatusEnum getFEITO() {
        return FEITO;
    }

    public static StatusEnum getAGUARDANDO() {
        return AGUARDANDO;
    }

    public static StatusEnum getENVIADO() {
        return ENVIADO;
    }

}

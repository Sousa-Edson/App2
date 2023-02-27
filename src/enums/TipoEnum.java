package enums;

public enum TipoEnum {
    DIVERSOS(1, "Diversos"),
    ENVELOPE(2, "Envelope"),
    REVISTA(3, "Revista"),
    FOLHETO(4, "Folheto");

    private final int id;
    private final String descricao;

    private TipoEnum(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static TipoEnum getDIVERSOS() {
        return DIVERSOS;
    }

    public static TipoEnum getENVELOPE() {
        return ENVELOPE;
    }

    public static TipoEnum getREVISTA() {
        return REVISTA;
    }

    public static TipoEnum getFOLHETO() {
        return FOLHETO;
    }

}

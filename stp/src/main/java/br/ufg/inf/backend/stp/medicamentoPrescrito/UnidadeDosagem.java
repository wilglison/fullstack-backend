package br.ufg.inf.backend.stp.medicamentoPrescrito;

public enum UnidadeDosagem {
    MILIGRAMA("mg"),
    MICROGRAMA("µg"),
    MILILITRO("mL"),
    COMPRIMIDO("comprimido");

    private final String descricao;

    UnidadeDosagem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

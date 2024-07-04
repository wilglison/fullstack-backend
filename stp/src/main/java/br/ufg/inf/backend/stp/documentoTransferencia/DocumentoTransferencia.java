package br.ufg.inf.backend.stp.documentoTransferencia;

import java.util.List;

import br.ufg.inf.backend.stp.medicamento.Medicamento;

public class DocumentoTransferencia {
    private Long id;
    private List<Medicamento> drogasAdministradas;
    private List<String> procedimentosAcondicionamento;
    private List<String> procedimentosRecebimento;

}

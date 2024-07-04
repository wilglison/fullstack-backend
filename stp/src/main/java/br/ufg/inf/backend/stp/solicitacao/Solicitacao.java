package br.ufg.inf.backend.stp.solicitacao;

import java.util.Date;
import java.util.List;

import br.ufg.inf.backend.stp.documentoTransferencia.DocumentoTransferencia;
import br.ufg.inf.backend.stp.especialidade.Especialidade;
import br.ufg.inf.backend.stp.medico.Medico;
import br.ufg.inf.backend.stp.paciente.Paciente;

public class Solicitacao {
    private Long id;
    private Medico medico;
    private Paciente paciente;
    private String motivo;
    private DocumentoTransferencia documento;
    private List<Especialidade> especialidadesRequisitada;
    private Date horarioSolicitacao;
    
}

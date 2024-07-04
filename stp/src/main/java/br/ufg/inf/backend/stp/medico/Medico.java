package br.ufg.inf.backend.stp.medico;

import br.ufg.inf.backend.stp.especialidade.Especialidade;
import br.ufg.inf.backend.stp.unidade_hospitalar.UnidadeHospitalar;

public class Medico {
    private Long id;
    private String nome;
    private String crm;
    private String telefone;
    private UnidadeHospitalar unidadeHospitalar;
    private Especialidade especialidade;
    private Papel papel;
}

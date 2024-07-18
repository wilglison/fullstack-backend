package br.ufg.inf.backend.stp.medico;

import br.ufg.inf.backend.stp.especialidade.Especialidade;
import br.ufg.inf.backend.stp.unidade_hospitalar.UnidadeHospitalar;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Medico {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String crm;
    private String telefone;
    private UnidadeHospitalar unidadeHospitalar;
    private Especialidade especialidade;
    private Papel papel;
}

package br.ufg.inf.backend.stp.medico;

import br.ufg.inf.backend.stp.especialidade.Especialidade;
import br.ufg.inf.backend.stp.unidadeHospitalar.UnidadeHospitalar;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Medico {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String crm;
    private String telefone;
    private Papel papel;

    @ManyToOne
    @JoinColumn(name = "unidade_hospitalar_id")
    private UnidadeHospitalar unidadeHospitalar;

    @ManyToOne
    @JoinColumn(name = "especialidade_id")
    private Especialidade especialidade;
    
}

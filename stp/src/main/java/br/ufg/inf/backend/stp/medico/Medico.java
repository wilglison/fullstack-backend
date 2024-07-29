package br.ufg.inf.backend.stp.medico;

import br.ufg.inf.backend.stp.especialidade.Especialidade;
import br.ufg.inf.backend.stp.unidadeHospitalar.UnidadeHospitalar;
import jakarta.persistence.Column;
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
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String crm;
    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false)
    private Papel papel;

    @ManyToOne
    @JoinColumn(name = "unidade_hospitalar_id", nullable = false)
    private UnidadeHospitalar unidadeHospitalar;

    @ManyToOne
    @JoinColumn(name = "especialidade_id", nullable = false)
    private Especialidade especialidade;
    
}

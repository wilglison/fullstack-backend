package br.ufg.inf.backend.stp.unidadeHospitalar;

import java.util.List;

import br.ufg.inf.backend.stp.endereco.Endereco;
import br.ufg.inf.backend.stp.especialidade.Especialidade;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Data
public class UnidadeHospitalar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String telefone;
	@Email
	private String email;
	private Boolean temUTI;
	private String dadosPessoal;
	private Double latitude;
	private Double longitude;
	private Integer disponibilidadeLeitos;

	@OneToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	@ManyToMany
	@JoinTable(
			name = "unidade_hospitalar_especialidades",
			joinColumns = @JoinColumn(name = "unidade_hospitalar_id"),
			inverseJoinColumns = @JoinColumn(name = "especialidade_id") 
	)
	private List<Especialidade> especialidades;

}

package br.ufg.inf.backend.stp.endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 100, nullable = false)
	private String rua;
	@Column(length = 100)
	private String complemento;
	@Column(length = 50, nullable = false)
	private String bairro;
	@Column(length = 8, columnDefinition = "char(8)", nullable = false)
	private String cep;
	@Column(length = 50, nullable = false)
	private String cidade;
	@Column(length = 2, columnDefinition = "char(8)", nullable = false)
	private String estado;
}

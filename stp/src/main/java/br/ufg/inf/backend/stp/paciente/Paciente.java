package br.ufg.inf.backend.stp.paciente;

import br.ufg.inf.backend.stp.endereco.Endereco;
import br.ufg.inf.backend.stp.prontuario.Prontuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Paciente {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String name;
    private String telefone;
    private String email;
    private Endereco endereco;
    private TipoSanguineo tipoSanguineo;
    private Prontuario prontuario;
}

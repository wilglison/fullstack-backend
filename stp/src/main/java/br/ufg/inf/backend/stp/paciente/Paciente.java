package br.ufg.inf.backend.stp.paciente;

import br.ufg.inf.backend.stp.endereco.Endereco;
import br.ufg.inf.backend.stp.prontuario.Prontuario;

public class Paciente {
    private Long id;
    private String cpf;
    private String name;
    private String telefone;
    private String email;
    private Endereco endereco;
    private TipoSanguineo tipoSanguineo;
    private Prontuario prontuario;
}

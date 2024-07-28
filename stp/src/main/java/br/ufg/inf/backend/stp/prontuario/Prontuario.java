package br.ufg.inf.backend.stp.prontuario;

import java.util.List;

import br.ufg.inf.backend.stp.medicamentoPrescrito.MedicamentoPrescrito;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Prontuario {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Classificacao classificacao;

    @ManyToMany
    @JoinTable(
            name = "prontuario_medicamento",
            joinColumns = @JoinColumn(name = "prontuario_id"),
            inverseJoinColumns = @JoinColumn(name = "medicamento_id")
    )
    private List<MedicamentoPrescrito> medicamentoPrescrito; 
}



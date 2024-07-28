package br.ufg.inf.backend.stp.medicamentoPrescrito;

import br.ufg.inf.backend.stp.medicamento.Medicamento;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class MedicamentoPrescrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String posologia;
    private Double dosagem;
    private UnidadeDosagem unidadeDosagem;
    private String viaAdministracao;

    @ManyToOne
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento;    
}




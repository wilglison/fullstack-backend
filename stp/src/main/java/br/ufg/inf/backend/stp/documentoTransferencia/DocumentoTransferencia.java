package br.ufg.inf.backend.stp.documentoTransferencia;

import java.util.List;

import br.ufg.inf.backend.stp.medicamento.Medicamento;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class DocumentoTransferencia {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private List<Medicamento> drogasAdministradas;
    private List<String> procedimentosAcondicionamento;
    private List<String> procedimentosRecebimento;

}

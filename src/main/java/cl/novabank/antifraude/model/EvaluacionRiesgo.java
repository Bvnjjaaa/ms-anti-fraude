package cl.novabank.antifraude.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "evaluaciones_riesgo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionRiesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El identificador de la transacción es obligatorio")
    @Positive
    @Column(nullable = false)
    private Long transaccionId;

    @NotBlank(message = "El RUT del cliente es obligatorio")
    @Column(nullable = false)
    private String rutCliente;

    @NotNull(message = "El monto es obligatorio")
    @Positive
    @Column(nullable = false)
    private Double monto;

    @Column
    private String nivelRiesgo;

    @Column
    private Boolean aprobado;

    @Column
    private String motivo;

    @Column(name = "fecha_evaluacion")
    private LocalDateTime fechaEvaluacion;

}

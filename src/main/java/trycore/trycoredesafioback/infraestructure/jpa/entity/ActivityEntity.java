package trycore.trycoredesafioback.infraestructure.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "activities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityEntity extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private BigDecimal totalPlannedBudget;
    @Column
    private Long percentageAdvancePlanned;
    @Column
    private Long percentageAdvanceReal;
    @Column
    private BigDecimal actualCostIncurred;
    @ManyToOne(fetch = FetchType.LAZY)
    private ProyectEntity proyect;
}

package trycore.trycoredesafioback.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {
    @NotNull
    private String name;
    @NotNull
    private BigDecimal totalPlannedBudget;
    @NotNull
    private Long percentageAdvancePlanned;
    @NotNull
    private Long percentageAdvanceReal;
    @NotNull
    private BigDecimal actualCostIncurred;
    @NotNull
    private Long proyectId;

}

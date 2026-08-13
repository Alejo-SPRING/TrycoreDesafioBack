package trycore.trycoredesafioback.application.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import trycore.trycoredesafioback.application.dto.ActivityDataDTO;
import trycore.trycoredesafioback.application.dto.CalculateIndicatorsDTO;
import trycore.trycoredesafioback.application.mapper.ActivityDtoMapper;
import trycore.trycoredesafioback.application.mapper.ActivityDtoMapperImpl;
import trycore.trycoredesafioback.application.mapper.CalculateIndicatorsDtoMapper;
import trycore.trycoredesafioback.application.mapper.CalculateIndicatorsDtoMapperImpl;
import trycore.trycoredesafioback.domain.model.Activity;
import trycore.trycoredesafioback.domain.repository.ActivityRepository;
import trycore.trycoredesafioback.domain.repository.ProyectRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActivityServiceTest {
    @Mock
    private ProyectRepository proyectRepository;
    @Mock
    private ActivityRepository activityRepository;
    @Spy
    private CalculateIndicatorsDtoMapper calculateIndicatorsDtoMapper = new CalculateIndicatorsDtoMapperImpl();
    @Spy
    @InjectMocks
    private ActivityDtoMapper activityDtoMapper = new ActivityDtoMapperImpl();

    @InjectMocks
    private ActivityService activityService;

    @BeforeEach
    void init() {
        activityService.init();
    }

    @Test
    void getByProyectId_whenProyectIdExist_thenReturnActivitiesCalculates() {
        List<Activity> activityList = List.of(new Activity(1L, "test", BigDecimal.valueOf(100000), 50L, 10L, BigDecimal.valueOf(500000), 1L, null));
        when(proyectRepository.isExist(anyLong())).thenReturn(true);
        when(activityRepository.findByProyect(anyLong())).thenReturn(activityList);
        List<ActivityDataDTO> activities = assertDoesNotThrow(() -> activityService.getByProyectId(1L));
        CalculateIndicatorsDTO calculateIndicatorsDTOEsxpect = new CalculateIndicatorsDTO(BigDecimal.valueOf(5000000), BigDecimal.valueOf(1000000), BigDecimal.valueOf(500000), BigDecimal.valueOf(-4000000), new BigDecimal("2.0000"), new BigDecimal("0.2000"), new BigDecimal("50000.0000"), new BigDecimal("2.0000"));
        Assertions.assertThat(calculateIndicatorsDTOEsxpect).usingRecursiveComparison().isEqualTo(activities.get(0).getCalculateIndicators());
    }
}

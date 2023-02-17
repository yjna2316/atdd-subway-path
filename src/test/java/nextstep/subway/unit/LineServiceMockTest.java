package nextstep.subway.unit;

import nextstep.subway.applicaion.LineService;
import nextstep.subway.applicaion.StationService;
import nextstep.subway.applicaion.dto.SectionRequest;
import nextstep.subway.domain.Line;
import nextstep.subway.domain.LineRepository;
import nextstep.subway.domain.Section;
import nextstep.subway.domain.Station;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LineServiceMockTest {
    @Mock
    private LineRepository lineRepository;
    @Mock
    private StationService stationService;
    @InjectMocks
    private LineService lineService;

    @Test
    void addSection() {
        // given
        Station upStation = new Station(1L, "석촌역");
        Station downStation = new Station(2L, "송파역");
        Line line = new Line("8호선", "pink");

        // lineRepository, stationService stub 설정을 통해 초기값 셋팅
        when(stationService.findById(upStation.getId())).thenReturn(upStation);
        when(stationService.findById(downStation.getId())).thenReturn(downStation);
        when(lineRepository.findById(line.getId())).thenReturn(Optional.of(line));
        SectionRequest sectionRequest = new SectionRequest(upStation.getId(), downStation.getId(), 10);

        // when
        // lineService.addSection 호출
        lineService.addSection(line.getId(), sectionRequest);

        // then
        // line.findLineById 메서드를 통해 검증
        // Q. line.addSection은 도메인 테스트 따로 하고 있고, 아래 메소드들은 무조건 호출되는 구조인데 LineService addSection 부분을 테스트 코드로 검증할 필요가 있을까?
        verify(lineRepository, times(1)).findById(line.getId());
        verify(stationService, times(1)).findById(upStation.getId());
        verify(stationService, times(1)).findById(downStation.getId());
    }
}

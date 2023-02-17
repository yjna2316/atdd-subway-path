package nextstep.subway.unit;

import nextstep.subway.applicaion.LineService;
import nextstep.subway.applicaion.dto.SectionRequest;
import nextstep.subway.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Transactional
public class LineServiceTest {
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private LineService lineService;

    @Test
    void addSection() {
        // given
        // stationRepository와 lineRepository를 활용하여 초기값 셋팅
        Station upStation = stationRepository.save(new Station(1L, "석촌역"));
        Station downStation = stationRepository.save(new Station(2L, "송파역"));
        Line line = lineRepository.save(new Line("8호선", "pink"));
        SectionRequest sectionRequest = new SectionRequest(upStation.getId(), downStation.getId(), 10);

        // when
        // lineService.addSection 호출
        lineService.addSection(line.getId(), sectionRequest);

        // then
        // line.getSections 메서드를 통해 검증
        assertAll(
                () -> assertThat(line.getSections()).hasSize(1),
                () -> assertThat(line.getStations()).containsExactly(upStation, downStation)
        );
    }
}

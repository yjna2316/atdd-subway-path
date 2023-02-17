package nextstep.subway.unit;

import nextstep.subway.domain.Line;
import nextstep.subway.domain.Section;
import nextstep.subway.domain.Station;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LineTest {

    @Test
    void addSection() {
        // given
        Station upStation = new Station("석촌역");
        Station downStation = new Station("송파역");
        Line line = new Line("8호선", "pink");
        Section section = new Section(line, upStation, downStation, 10);

        // when
        line.addSection(section);

        // then
        assertThat(line.getSections().size()).isEqualTo(1);
    }

    @Test
    void getStations() {
        // given
        Station upStation = new Station("석촌역");
        Station downStation = new Station("송파역");
        Line line = new Line("8호선", "pink");
        Section section = new Section(line, upStation, downStation, 10);
        line.addSection(section);

        // when
        List<Station> stations = line.getStations();

        // then
        assertAll(
                () -> assertNotNull(stations),
                () -> assertThat(stations).containsExactly(upStation, downStation)
        );
    }

    @Test
    void removeSection() {
        // given
        Station station1 = new Station("석촌역");
        Station station2 = new Station("송파역");
        Station station3 = new Station("가락시장역");
        Line line = new Line("8호선", "pink");
        Section section1 = new Section(line, station1, station2, 10);
        Section section2 = new Section(line, station2, station3, 10);
        line.addSection(section1);
        line.addSection(section2);

        // when
        line.removeSection(section2);

        // then
        assertAll(
                () -> assertThat(line.getSections().size()).isEqualTo(1),
                () -> assertThat(line.getStations()).containsExactly(station1, station2)
        );
    }
}

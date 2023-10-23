package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 게임종료_후_재시작() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("246", "135", "1", "597", "589", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                },
                1, 3, 5, 5, 8, 9
        );
    }

    @Test
    void 숫자입력_예외_테스트() {
        // 3개 보다 많은 숫자
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1234"))
                        .isInstanceOf(IllegalArgumentException.class)
        );

        // 3개 보다 적은 숫자
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("12"))
                        .isInstanceOf(IllegalArgumentException.class)
        );

        // 중복된 숫자
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("222"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 게임결과판정_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("246", "123", "356", "135");
                    assertThat(output()).contains("낫싱", "1볼 1스트라이크", "2볼", "3스트라이크");
                },
                1, 3, 5, 1, 3, 5, 1, 3, 5, 1, 3, 5
        );
    }


    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}

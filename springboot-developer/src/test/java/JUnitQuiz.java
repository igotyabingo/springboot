import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class JUnitQuiz {

    @DisplayName("문제 1번")
    @Test
    public void junitTest() {
        String name1 = "홍길동";
        String name2 = "홍길동";
        String name3 = "홍길은";

        // 1. 모든 변수가 null이 아닌지 확인
        assertThat(name1).isNotNull();
        assertThat(name2).isNotNull();
        assertThat(name3).isNotNull();
        // 2. name1 == name2인지 확인
        assertThat(name1).isEqualTo(name2);
        // 3. name1 != name3인지 확인
        assertThat(name1).isNotEqualTo(name3);
    }

    @DisplayName("문제 2번")
    @Test
    public void junitTest2() {
        int number1 = 15;
        int number2 = 0;
        int number3 = -5;

        // 1. number1은 양수인지 확인
        assertThat(number1).isPositive();
        // 2. number2는 0인지 확인
        assertThat(number2).isZero();
        // 3. number3는 음수인지 확인
        assertThat(number3).isNegative();
        // 4. number1은 number2보다 큰지 확인
        assertThat(number1).isGreaterThan(number2);
        // 5. number3는 number2보다 작은지 확인
        assertThat(number3).isLessThan(number2);
    }
}

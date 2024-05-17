package stevenlan.bookstore1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Bookstore1ApplicationTests {

	Calculator undertest = new Calculator();

	@Test
	void addNumbers() {
		//given
		int numbera = 20;
		int numberb = 30;

		//when
		int result = undertest.add(numbera, numberb);
		int except = 50;

		//then
		assertThat(result).isEqualTo(except);
	}

	class Calculator{
		int add(int a, int b){
			return a+b;
		}
	}

}

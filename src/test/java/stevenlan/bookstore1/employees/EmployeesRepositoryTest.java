package stevenlan.bookstore1.employees;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EmployeesRepositoryTest {

    @Autowired
    private EmployeesRepository underTest;

    @Test
    void itSouldFindEmployeesByAccount() {
        //given
        String account = "yyy";
        Employees employees = new Employees(
            account,
            "ggg",
            "tom",
            "tom@gmail.com",
            "333"
        );
        underTest.save(employees);
        //when
        Optional<Employees> targetEmployee = underTest.findEmployeesByAccount(account);
        //then
        assertThat(targetEmployee).isEqualTo(employees);
    }
}

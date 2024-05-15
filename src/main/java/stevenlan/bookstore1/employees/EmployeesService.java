package stevenlan.bookstore1.employees;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeesService {

    private final EmployeesRepository employeesRepository;

    
    @Autowired
    public EmployeesService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }



    public List<Employees> getEmployees(){
		
        return employeesRepository.findAll(); 
	}

    public void addNewEmployees(Employees employees){
        Optional<Employees> employeesByAccount = employeesRepository.findEmployeesByAccount(employees.getAccount());
        if(employeesByAccount.isPresent()){
            throw new IllegalStateException("account taken");
        }
        employeesRepository.save(employees);
    };
}

package javaproject.springprj;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceTest userServiceTest;

    @Test
    void testGetEmployeeById_found() {
        employees emp = new employees(1, "John", "Doe", "HR", 50000);
        when(employeeRepository.findById(1)).thenReturn(Optional.of(emp));

        employees result = employeeService.getEmployeeById(1);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("HR", result.getDepartment());
        assertEquals(50000, result.getSalary());
    }

    @Test
    void testGetEmployeeById_notFound() {
        when(employeeRepository.findById(2)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            employeeService.getEmployeeById(2);
        });

        assertEquals("Employee not found", exception.getMessage());
    }


    @Test
    void testCreateEmployee() {
        employees emp = new employees(0, "Alice", "Smith", "Finance", 60000);
        employees savedEmp = new employees(3, "Alice", "Smith", "Finance", 60000);

        when(employeeRepository.save(emp)).thenReturn(savedEmp);

        employees result = employeeService.createEmployee(emp);

        assertNotNull(result);
        assertEquals(3, result.getId());
        assertEquals("Alice", result.getFirstName());
        assertEquals("Smith", result.getLastName());
        assertEquals("Finance", result.getDepartment());
        assertEquals(60000, result.getSalary());
    }
}


import org.testng.annotations.Test;

public class NewJDBCTests extends DBUtilities{
    @Test
    public void test1(){
        String query = "SELECT employees.*\n" +
                "FROM employees\n" +
                "INNER JOIN dept_emp ON employees.emp_no = dept_emp.emp_no\n" +
                "INNER JOIN departments ON dept_emp.dept_no = departments.dept_no\n" +
                "WHERE departments.dept_name = 'Human Resources';";
        printAllData(query);
    }

    @Test
    public void test2(){
        String query = "SELECT AVG(salary) AS average_salary\n" +
                "FROM salaries;";
        printAllData(query);
    }
}

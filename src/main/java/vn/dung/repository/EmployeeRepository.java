package vn.dung.repository;

import vn.dung.entity.Employee;

import java.util.Arrays;
import java.util.List;

public class EmployeeRepository {
    public List<Employee> fetchAll() {
        // sample data
        return Arrays.asList(new Employee(1, "adalya001915", "adalya001915@mail.com", "male"),
                new Employee(2, "shurville0101024", "shurville0101024@mail.com", "female"),
                new Employee(3, "machent0101024", "machent0101024@mail.com", "male"),
                new Employee(4, "tranita001816", "tranita001816@mail.com", "female"),
                new Employee(5, "jenkyn0101024", "jenkyn0101024@mail.com", "female"));
    }
}

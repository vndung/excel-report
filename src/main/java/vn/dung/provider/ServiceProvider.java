package vn.dung.provider;

import vn.dung.generator.ReportGenerator;
import vn.dung.generator.impl.EmployeeReportGenerator;
import vn.dung.repository.EmployeeRepository;

public class ServiceProvider {
    private static EmployeeRepository employeeRepo;
    private static ReportGenerator reportGenerator;

    public static EmployeeRepository getEmployeeRepo() {
        if (employeeRepo == null) {
            employeeRepo = new EmployeeRepository();
        }

        return employeeRepo;
    }

    public static ReportGenerator getEmpReportGenerator() {
        if (reportGenerator == null) {
            reportGenerator = new EmployeeReportGenerator(getEmployeeRepo());
        }

        return reportGenerator;
    }
}

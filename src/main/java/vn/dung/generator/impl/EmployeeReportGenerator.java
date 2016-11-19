package vn.dung.generator.impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vn.dung.entity.Employee;
import vn.dung.generator.ReportGenerator;
import vn.dung.mapping.EmpExcelMapping;
import vn.dung.repository.EmployeeRepository;
import vn.dung.utils.ExcelUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class EmployeeReportGenerator implements ReportGenerator {
    private static final String EMPLOYEE = "Employee";
    private static final int EMP_START_INDEX = 1;

    private EmployeeRepository empRepo;

    public EmployeeReportGenerator(EmployeeRepository employeeRepo) {
        this.empRepo = employeeRepo;
    }

    public void generate(String templateFilePath, String outputPath) throws IOException, InvalidFormatException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(templateFilePath);
        if (Objects.isNull(inputStream)) {
            throw new FileNotFoundException("Could not get template file: " + templateFilePath);
        }

        Workbook workbook = new XSSFWorkbook(inputStream);
        populateEmpDataToExcel(workbook.getSheet(EMPLOYEE));
        ExcelUtils.reCalculateAllFormulas(workbook);
        workbook.write(FileUtils.openOutputStream(new File(outputPath, FilenameUtils.getName(templateFilePath))));
    }

    private void populateEmpDataToExcel(Sheet workbook) {
        List<Employee> employees = empRepo.fetchAll();
        CellStyle cellStyle = workbook.getRow(EMP_START_INDEX).getCell(0).getCellStyle();

        IntStream.range(0, employees.size())
                .forEach(index -> populateOneEmp(employees.get(index),
                                                 workbook.createRow(EMP_START_INDEX + index),
                                                 cellStyle));
    }

    private void populateOneEmp(Employee employee, Row row, CellStyle cellStyle) {
        row.createCell(EmpExcelMapping.ID.getCellIndex()).setCellValue(employee.getId());
        row.createCell(EmpExcelMapping.NAME.getCellIndex()).setCellValue(employee.getName());
        row.createCell(EmpExcelMapping.GENDER.getCellIndex()).setCellValue(employee.getGender());
        row.createCell(EmpExcelMapping.EMAIL.getCellIndex()).setCellValue(employee.getEmail());

        copyStyle(row, cellStyle);
    }

    private void copyStyle(Row row, CellStyle cellStyle) {
        StreamSupport.stream(row.spliterator(), false)
                     .forEach(cell -> cell.setCellStyle(cellStyle));
    }
}

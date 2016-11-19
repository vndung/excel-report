package vn.dung;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import vn.dung.provider.ServiceProvider;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        String outputPath = "./";
        if (args.length >= 1) {
            outputPath = args[0];
        }

        ServiceProvider.getEmpReportGenerator()
                       .generate("EmployeeReport.xlsx", outputPath);
    }
}

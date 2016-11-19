package vn.dung.generator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public interface ReportGenerator {
    void generate(String templateFilePath, String outputPath) throws IOException, InvalidFormatException;
}

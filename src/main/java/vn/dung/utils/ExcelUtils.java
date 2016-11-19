package vn.dung.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.stream.StreamSupport;

public class ExcelUtils {
    private ExcelUtils() {
    }

    public static void reCalculateAllFormulas(Workbook workbook) {
        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

        StreamSupport.stream(workbook.spliterator(), false)
                .flatMap(sheet -> StreamSupport.stream(sheet.spliterator(), false))
                .flatMap(row -> StreamSupport.stream(row.spliterator(), false))
                .filter(cell -> cell.getCellTypeEnum() == CellType.FORMULA)
                .forEach(formulaEvaluator::evaluateFormulaCellEnum);
    }
}

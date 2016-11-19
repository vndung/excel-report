package vn.dung.mapping;

public enum EmpExcelMapping {
    ID(0),
    NAME(1),
    GENDER(2),
    EMAIL(3);

    int cellIndex;

    EmpExcelMapping(int index) {
        cellIndex = index;
    }

    public int getCellIndex() {
        return cellIndex;
    }
}

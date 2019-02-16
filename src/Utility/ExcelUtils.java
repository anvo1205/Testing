package Utility;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	private static XSSFCell Cell;
	private static XSSFRow Row;

	public static void setExcelFile(String path, String sheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(path);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			return "";
		}
	}

	public static void setCellData(String path, String value, int rowNum, int colNum) throws Exception {

		try {
			Row = ExcelWSheet.getRow(rowNum);
			// Cell = Row.getCell(colNum, Row.RETURN_BLANK_AS_NULL);
			Cell = Row.getCell(colNum);
			if (Cell == null) {
				Cell = Row.createCell(colNum);
				Cell.setCellValue(value);
			} else {
				Cell.setCellValue(value);
			}
			FileOutputStream fileOut = new FileOutputStream(path);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}

	public static int getRowIndexByCellValue(String value) {
		try {
			int index = 0;
			for (int i = 0; i < ExcelWSheet.getLastRowNum(); i++) {
				Cell = ExcelWSheet.getRow(i).getCell(0);
				if (Cell.getStringCellValue().equals(value)) {
					index = i;
					break;
				}
			}
			return index;
		} catch (Exception e) {
			throw (e);
		}
	}

	public static int getLastRowIndex() {
		return ExcelWSheet.getLastRowNum();
	}
}

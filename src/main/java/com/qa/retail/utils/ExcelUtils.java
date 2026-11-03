package com.qa.retail.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	private static String excelFilePath = "./src/test/resources/testdata/RetailUserTestData.xlsx";
	
	public static Object[][] getTestData(String sheetName) {
		System.out.println("Reading test data from sheet: " + sheetName);
		// 2: Declare one 2D Object Array -- null
		Object[][] data = null;
		// 3: Establish connection with Excel file
		try {
			FileInputStream ip = new FileInputStream(excelFilePath);
			// 4: Creating workbook in the Java memory
			Workbook book = WorkbookFactory.create(ip);
			// 5: Go to a specific sheet
			Sheet sheet = book.getSheet(sheetName);
			// 6: Initializing the array
			// data = new Object[5][3]; --> Hard-coded and not recommended
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()]; //
			// 7: Filling the array
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					// Filling starts here
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}

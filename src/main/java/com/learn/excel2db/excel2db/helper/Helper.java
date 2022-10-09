package com.learn.excel2db.excel2db.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.learn.excel2db.excel2db.entities.Products;

public class Helper {

	// check file is of excel type
	public static boolean checkExcelFormat(MultipartFile file) {

		String contentType = file.getContentType();
		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		}
		return false;

	}

	// convert the data of excel to list of products
	@SuppressWarnings("unchecked")
	public static List<Products> convertExcelToListOfProduct(InputStream is) {
		List<Products> list = new ArrayList<>();
		try {

			try (XSSFWorkbook workbook = new XSSFWorkbook(is)) {
				XSSFSheet sheet = workbook.getSheet("Sheet1");

				int rowNumber = 0;

				Iterator<?> iterator =
				sheet.iterator();

				while (((Iterator<Row>) iterator).hasNext()) {

					Row row = ((Iterator<Row>) iterator).next();

					if (rowNumber == 0) {
						rowNumber++;
						continue;
					}

					Iterator<Cell> cells = row.iterator();

					int cid = 0;

					Products p = new Products();

					while (cells.hasNext()) {
						Cell cell = cells.next();

						switch (cid) {
						case 1:
							p.setProductName(cell.getStringCellValue());
							break;

						case 2:
							p.setProductDesc(cell.getStringCellValue());
							break;

						case 3:
							p.setProductPrice(cell.getNumericCellValue());
							break;

						default:
							break;
						}
						cid++;
					}
					list.add(p);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

}

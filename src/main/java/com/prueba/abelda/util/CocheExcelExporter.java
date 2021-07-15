package com.prueba.abelda.util;

import com.prueba.abelda.model.Coche;
import com.prueba.abelda.model.ExcelStyle;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CocheExcelExporter {

  private final List<Coche> coches;

  private final XSSFWorkbook xssfWorkbook;

  private final XSSFSheet sheet;

  private Map<ExcelStyle, CellStyle> styles;

  private static Map<Integer, String> cochesColumn;

  static {
    cochesColumn = new HashMap<>();
    cochesColumn.put(0, "ID");
    cochesColumn.put(1, "MODELO");
    cochesColumn.put(2, "COLOR");
    cochesColumn.put(3, "ID_MARCA");
  }

  public CocheExcelExporter(List<Coche> cocheList) {
    this.coches = cocheList;
    this.xssfWorkbook = new XSSFWorkbook();
    this.sheet = xssfWorkbook.createSheet("Coches");
  }

  public void export(HttpServletResponse response) throws IOException {
    writeHeaderRow();
    writeDataRows();

    ServletOutputStream outputStream = response.getOutputStream();
    xssfWorkbook.write(outputStream);
    xssfWorkbook.close();
    outputStream.close();
  }

  private void writeHeaderRow() {
    Row row = sheet.createRow(0);
    CellStyle cellStyle = createGreyCenteredBoldArialWithBorderStyle(xssfWorkbook);

    for (int i = 0; i < cochesColumn.size(); i++) {
      Cell cell = row.createCell(i);
      cell.setCellValue(cochesColumn.get(i));
      cell.setCellStyle(cellStyle);
    }

  }

  private void writeDataRows() {
    int countRows = 1;
    CellStyle styleData = createRightAlignedStyle(xssfWorkbook);
    for (Coche coche : coches) {
      Row row = sheet.createRow(countRows);

      Cell cellId = row.createCell(0);
      cellId.setCellValue(coche.getId());
      cellId.setCellStyle(styleData);

      Cell cellColor = row.createCell(1);
      cellColor.setCellValue(coche.getColor());
      cellColor.setCellStyle(styleData);

      Cell cellModelo = row.createCell(2);
      cellModelo.setCellValue(coche.getModelo());
      cellModelo.setCellStyle(styleData);

      Cell cellMarca = row.createCell(3);
      cellMarca.setCellValue(coche.getMarca().getId());
      cellMarca.setCellStyle(styleData);
      countRows++;
    }

  }

  private CellStyle createGreyCenteredBoldArialWithBorderStyle(Workbook wb) {
    CellStyle style = this.createBorderedStyle(wb);
    Font font = wb.createFont();
    font.setFontName("Arial");
    font.setBold(true);
    style.setAlignment(HorizontalAlignment.CENTER);
    style.setFont(font);
    style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    return style;
  }

  private CellStyle createRightAlignedStyle(Workbook wb) {
    CellStyle style = wb.createCellStyle();
    style.setAlignment(HorizontalAlignment.RIGHT);
    return style;
  }

  private CellStyle createBorderedStyle(Workbook wb) {
    BorderStyle thin = BorderStyle.THIN;
    short black = IndexedColors.BLACK.getIndex();
    CellStyle style = wb.createCellStyle();
    style.setBorderRight(thin);
    style.setRightBorderColor(black);
    style.setBorderBottom(thin);
    style.setBottomBorderColor(black);
    style.setBorderLeft(thin);
    style.setLeftBorderColor(black);
    style.setBorderTop(thin);
    style.setTopBorderColor(black);
    return style;
  }

}

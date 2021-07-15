package com.prueba.abelda.service;

import com.prueba.abelda.model.ExcelStyle;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

public class StylesGenerator {

  public Map prepareStyles(Workbook wb) {
    Font boldArial = this.createBoldArialFont(wb);
    Font redBoldArial = this.createRedBoldArialFont(wb);
    CellStyle rightAlignedStyle = this.createRightAlignedStyle(wb);
    CellStyle greyCenteredBoldArialWithBorderStyle = this.createGreyCenteredBoldArialWithBorderStyle(wb, boldArial);
    CellStyle redBoldArialWithBorderStyle = this.createRedBoldArialWithBorderStyle(wb, redBoldArial);
    CellStyle rightAlignedDateFormatStyle = this.createRightAlignedDateFormatStyle(wb);
    Map excelMap = new HashMap();
    excelMap.put(ExcelStyle.RIGHT_ALIGNED, rightAlignedStyle);
    excelMap.put(ExcelStyle.GREY_CENTERED_BOLD_ARIAL_WITH_BORDER, greyCenteredBoldArialWithBorderStyle);
    excelMap.put(ExcelStyle.RED_BOLD_ARIAL_WITH_BORDER, redBoldArialWithBorderStyle);
    excelMap.put(ExcelStyle.RIGHT_ALIGNED_DATE_FORMAT, rightAlignedDateFormatStyle);
    return excelMap;
  }

  private Font createBoldArialFont(Workbook wb) {
    Font font = wb.createFont();
    font.setFontName("Arial");
    font.setBold(true);
    return font;
  }

  private Font createRedBoldArialFont(Workbook wb) {
    Font font = wb.createFont();
    font.setFontName("Arial");
    font.setBold(true);
    font.setColor(IndexedColors.RED.getIndex());
    return font;
  }

  private CellStyle createRightAlignedStyle(Workbook wb) {
    CellStyle style = wb.createCellStyle();
    style.setAlignment(HorizontalAlignment.RIGHT);
    return style;
  }

  private CellStyle createGreyCenteredBoldArialWithBorderStyle(Workbook wb, Font boldArial) {
    CellStyle style = this.createBorderedStyle(wb);
    style.setAlignment(HorizontalAlignment.CENTER);
    style.setFont(boldArial);
    style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    return style;
  }

  private CellStyle createRedBoldArialWithBorderStyle(Workbook wb, Font redBoldArial) {
    CellStyle style = this.createBorderedStyle(wb);
    style.setFont(redBoldArial);
    return style;
  }

  private CellStyle createRightAlignedDateFormatStyle(Workbook wb) {
    CellStyle style = wb.createCellStyle();
    style.setAlignment(HorizontalAlignment.RIGHT);
    style.setDataFormat((short) 14);
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
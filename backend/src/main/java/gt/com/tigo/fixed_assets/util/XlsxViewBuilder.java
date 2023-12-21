package gt.com.tigo.fixed_assets.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class XlsxViewBuilder extends AbstractXlsxView {

    private static final String HEADER_CONTENT_KEY = "Content-Disposition";
    private static final String HEADER_CONTENT_VALUE = "attachment; filename=\"%s\"";
    private static final String HEADER_ACCESS_KEY = "access-control-expose-headers";
    private static final String HEADER_ACCESS_VALUE = "Filename";
    private static final String HEADER_FILENAME_KEY = "Filename";
    private static final String HEADER_FILENAME_VALUE = "%s";

    private CellStyle getHeaderStyle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setBold(true);
        font.setColor(IndexedColors.BLACK.getIndex());

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        return style;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader(HEADER_CONTENT_KEY, String.format(HEADER_CONTENT_VALUE, model.get("filename").toString()));
        response.setHeader(HEADER_ACCESS_KEY, HEADER_ACCESS_VALUE);
        response.setHeader(HEADER_FILENAME_KEY, String.format(HEADER_FILENAME_VALUE, model.get("filename").toString()));

        CellStyle headerStyle = this.getHeaderStyle(workbook);

        XSSFSheet sheet = (XSSFSheet) workbook.createSheet(model.get("sheetName") != null ? model.get("sheetName").toString() : "Datos");

        int rowCount = 0;
        XSSFRow row = sheet.createRow(rowCount++);

        List<String> columns = (List<String>) model.get("columns");
        int columnCount = 0;

        for (String column : columns) {
            XSSFCell cell = row.createCell(columnCount++);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(column);
        }

        row = sheet.createRow(rowCount++);

        List<Object[]> dataRows = (List<Object[]>) model.get("rows");
        columnCount = 0;

        for (Object[] dataRow : dataRows) {
            for (Object dataCell : dataRow) {
                XSSFCell cell = row.createCell(columnCount++);
                cell.setCellValue(dataCell == null ? "" : dataCell.toString());
            }

            row = sheet.createRow(rowCount++);
            columnCount = 0;
        }

        for (int i = 0; i < columns.size(); i++) {
            sheet.autoSizeColumn(i);
        }
    }

}

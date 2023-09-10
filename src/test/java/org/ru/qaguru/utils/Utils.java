package org.ru.qaguru.utils;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

public class Utils {


    public String csvReader(InputStream csvStream) throws Exception {
        Reader reader = new InputStreamReader(csvStream);
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> content = csvReader.readAll();
        return Arrays.toString(content.get(0));
    }


    public String pdfReader(InputStream stream) throws Exception {
        PDF pdf = new PDF(stream);
        return pdf.text;

    }

    public String xlsxReader(InputStream stream) throws Exception {
        XLS xls = new XLS(stream);
        System.out.println(xls.excel.getPrintArea(1));
        return xls.excel.getSheetAt(0).
                getRow(1)
                .getCell(1)
                .getStringCellValue();
    }


}

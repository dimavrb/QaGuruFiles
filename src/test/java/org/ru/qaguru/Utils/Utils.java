package org.ru.qaguru.Utils;
import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Assertions;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.opencsv.CSVReader;

public class Utils {

    ClassLoader cl = Utils.class.getClassLoader();
    public String csvReader(InputStream csvStream) throws Exception {
        Reader reader = new InputStreamReader(csvStream) ;
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> content = csvReader.readAll();
        final String firstRow = Arrays.toString(content.get(0));
        return firstRow;
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

package org.ru.qaguru;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ru.qaguru.utils.Utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class FileParsingTest {

    private final ClassLoader cl = FileParsingTest.class.getClassLoader();
    private final Utils utils = new Utils();

    @Test
    void unZipFile() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("test.zip")) {
            assert stream != null;
            try (ZipInputStream zis = new ZipInputStream(stream)) {
                ZipEntry entry;
                String name;
                while ((entry = zis.getNextEntry()) != null) {
                    name = entry.getName();
                    List<String> fileInZip = new ArrayList<>();
                    fileInZip.add(name);
                    for (String s : fileInZip) {
                        String extension = s.substring(name.length() - 3);
                        switch (extension) {
                            case "csv" -> {
                                String resultCsv = utils.csvReader(zis);
                                Assertions.assertTrue(resultCsv.contains("Водитель"));
                            }
                            case "pdf" -> {
                                String resultPdf = utils.pdfReader(zis);
                                Assertions.assertTrue(resultPdf.contains("Шпаргалка по Expected Conditions в Selenium"));
                            }
                            case "lsx" -> {
                                String resultXls = utils.xlsxReader(zis);
                                Assertions.assertTrue(resultXls.contains("Е2Е. Создание рекламной записи"));
                            }
                        }
                    }
                }

            }
        }
    }
}


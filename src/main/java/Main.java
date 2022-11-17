import text.extractor.InvoiceTextExtractor;
import text.parser.TextParser;

import java.io.File;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {
        InvoiceTextExtractor invoiceTextExtractor = new InvoiceTextExtractor();
        TextParser textParser = new TextParser();
        try {
            textParser.parseInvoice(invoiceTextExtractor.extractTextFromDoc(new File("/Users/lukegollenstede/Desktop/1ff9ad57d0_1022651TG01.pdf")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

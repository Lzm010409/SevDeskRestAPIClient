package text.extractor;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.filter.TextRegionEventFilter;
import com.itextpdf.kernel.pdf.canvas.parser.listener.FilteredTextEventListener;
import com.itextpdf.kernel.pdf.canvas.parser.listener.ITextExtractionStrategy;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;
import org.jboss.logging.Logger;
import text.coordinates.InvoiceCoordinates;

import java.io.File;
import java.io.IOException;

public class InvoiceTextExtractor implements TextExtractor {

    private final Logger logger = Logger.getLogger(InvoiceTextExtractor.class);

    @Override
    public String extractTextFromDoc(File file) throws IOException {
        try {
            Rectangle rect;
            TextRegionEventFilter regionFilter;
            ITextExtractionStrategy strategy;
            String str;
            StringBuilder builder = new StringBuilder();
            PdfDocument pdfDoc = new PdfDocument(new PdfReader(file));

            logger.log(Logger.Level.INFO, "Auslesen des Textes aus: " + file.getAbsolutePath());

            rect = new Rectangle(InvoiceCoordinates.ANREDE.getX(), InvoiceCoordinates.ANREDE.getY(), InvoiceCoordinates.ANREDE.getWidth(), InvoiceCoordinates.ANREDE.getHeight());
            regionFilter = new TextRegionEventFilter(rect);
            strategy = new FilteredTextEventListener(new LocationTextExtractionStrategy(), regionFilter);
            str = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(1), strategy) + "\n\n";
            builder.append(str);
            rect = new Rectangle(InvoiceCoordinates.NAME.getX(), InvoiceCoordinates.NAME.getY(), InvoiceCoordinates.NAME.getWidth(), InvoiceCoordinates.NAME.getHeight());
            regionFilter = new TextRegionEventFilter(rect);
            strategy = new FilteredTextEventListener(new LocationTextExtractionStrategy(), regionFilter);
            str = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(1), strategy) + "\n\n";
            builder.append(str);
            rect = new Rectangle(InvoiceCoordinates.STREET.getX(), InvoiceCoordinates.STREET.getY(), InvoiceCoordinates.STREET.getWidth(), InvoiceCoordinates.STREET.getHeight());
            regionFilter = new TextRegionEventFilter(rect);
            strategy = new FilteredTextEventListener(new LocationTextExtractionStrategy(), regionFilter);
            str = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(1), strategy) + "\n\n";
            builder.append(str);
            rect = new Rectangle(InvoiceCoordinates.ZIPCITY.getX(), InvoiceCoordinates.ZIPCITY.getY(), InvoiceCoordinates.ZIPCITY.getWidth(), InvoiceCoordinates.ZIPCITY.getHeight());
            regionFilter = new TextRegionEventFilter(rect);
            strategy = new FilteredTextEventListener(new LocationTextExtractionStrategy(), regionFilter);
            str = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(1), strategy) + "\n\n";
            builder.append(str);
            rect = new Rectangle(InvoiceCoordinates.RECHNUNGSDATUM.getX(), InvoiceCoordinates.RECHNUNGSDATUM.getY(), InvoiceCoordinates.RECHNUNGSDATUM.getWidth(), InvoiceCoordinates.RECHNUNGSDATUM.getHeight());
            regionFilter = new TextRegionEventFilter(rect);
            strategy = new FilteredTextEventListener(new LocationTextExtractionStrategy(), regionFilter);
            str = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(1), strategy) + "\n\n";
            builder.append(str);
            rect = new Rectangle(InvoiceCoordinates.GUTACHTENNUMMER.getX(), InvoiceCoordinates.GUTACHTENNUMMER.getY(), InvoiceCoordinates.GUTACHTENNUMMER.getWidth(), InvoiceCoordinates.GUTACHTENNUMMER.getHeight());
            regionFilter = new TextRegionEventFilter(rect);
            strategy = new FilteredTextEventListener(new LocationTextExtractionStrategy(), regionFilter);
            str = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(1), strategy) + "\n\n";
            builder.append(str);
            rect = new Rectangle(InvoiceCoordinates.BETRAG.getX(), InvoiceCoordinates.BETRAG.getY(), InvoiceCoordinates.BETRAG.getWidth(), InvoiceCoordinates.BETRAG.getHeight());
            regionFilter = new TextRegionEventFilter(rect);
            strategy = new FilteredTextEventListener(new LocationTextExtractionStrategy(), regionFilter);
            str = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(1), strategy) + "\n\n";
            builder.append(str);
            rect = new Rectangle(InvoiceCoordinates.ANDERE.getX(), InvoiceCoordinates.ANDERE.getY(), InvoiceCoordinates.ANDERE.getWidth(), InvoiceCoordinates.ANDERE.getHeight());
            regionFilter = new TextRegionEventFilter(rect);
            strategy = new FilteredTextEventListener(new LocationTextExtractionStrategy(), regionFilter);
            str = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(1), strategy) + "\n\n";
            builder.append(str);

            logger.log(Logger.Level.INFO, "Auslesen erfolgreich!");

            return builder.toString();
        } catch (Exception e) {
            logger.log(Logger.Level.ERROR, "Auslesen nicht erfolgreich, folgender Fehler ist aufgetreten: " + e.getMessage());
            return null;
        }
    }
}

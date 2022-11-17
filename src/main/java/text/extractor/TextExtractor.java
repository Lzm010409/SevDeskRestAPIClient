package text.extractor;

import java.io.File;
import java.io.IOException;

public interface TextExtractor {
    public String extractTextFromDoc(File file) throws IOException;
}

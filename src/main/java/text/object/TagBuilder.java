package text.object;

import data.entity.other.TagName;
import data.filepaths.PropertyReader;

import java.util.List;

public class TagBuilder implements ObjectBuilder {


    @Override
    public Object build(List<String> e) {
        List<TagName> tagNames = new PropertyReader().readRechtsanwälte(new PropertyReader().readProperties("RechtsanwälteProperties.txt"));

        TagName tagName = tagNames.get(tagNames.size() - 1);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < e.size(); i++) {
            if (e.get(i).contains("anwalt")) {
                builder.append(e.get(i));
                builder.append(" " + e.get(i + 1));
            }
        }

        String rechtsanwalt = builder.toString();

        for (int i = 0; i < tagNames.size(); i++) {
            if (rechtsanwalt.length() != 0) {
                if (rechtsanwalt.contains(tagNames.get(i).getKürzel())) {
                    tagName = tagNames.get(i);
                    break;


                }
            }
        }

        return tagName;

    }
}

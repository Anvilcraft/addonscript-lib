package anvil.asrepo;

import java.util.List;

public class IndexJSON {

    public List<Entry> addons;

    public static class Entry {
        public String id;
        public String script;
        public Meta meta;

        public static class Meta {
            public String external;
            public String name;
            public String description;
            public String logo;
        }

    }

}

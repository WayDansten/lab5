package stored_classes.enums;

import java.util.HashMap;
import java.util.Map;

public enum View {
    STREET,
    YARD,
    PARK,
    BAD,
    NORMAL;
    public static final Map<String, View> naming = new HashMap<>();
    static {
        naming.put("BAD", BAD);
        naming.put("STREET", STREET);
        naming.put("YARD", YARD);
        naming.put("NORMAL", NORMAL);
        naming.put("PARK", PARK);
    }
}

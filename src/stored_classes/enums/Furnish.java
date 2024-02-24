package stored_classes.enums;

import java.util.HashMap;
import java.util.Map;

public enum Furnish {
    DESIGNER,
    NONE,
    FINE,
    BAD,
    LITTLE;
    public static final Map<String, Furnish> naming = new HashMap<>();
    static {
        naming.put("NONE", NONE);
        naming.put("BAD", BAD);
        naming.put("FINE", FINE);
        naming.put("LITTLE", LITTLE);
        naming.put("DESIGNER", DESIGNER);
    }
}

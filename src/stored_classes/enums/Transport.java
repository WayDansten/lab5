package stored_classes.enums;

import java.util.HashMap;
import java.util.Map;

public enum Transport {
    FEW,
    LITTLE,
    NORMAL,
    ENOUGH;
    public static final Map<String, Transport> naming = new HashMap<>();
    static {
        naming.put("FEW", FEW);
        naming.put("LITTLE", LITTLE);
        naming.put("NORMAL", NORMAL);
        naming.put("ENOUGH", ENOUGH);
    }
}

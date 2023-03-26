package process;

import java.util.Optional;

public class EquipmentAgent {
    Equipment equipment;
    private boolean available = true;

    public boolean isAvailable() {
        return available;
    }

    public void Use() {
        available = false;
    }

    public void Free() {
        available = true;
    }
}

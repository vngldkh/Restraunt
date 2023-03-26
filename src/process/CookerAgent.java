package process;

import java.util.Optional;

public class CookerAgent {
    private Cooker cooker;
    private boolean available = true;

    public boolean isAvailable() {
        return available;
    }

    public void Engage() {
        available = false;
    }

    public void Free() {
        available = true;
    }
}

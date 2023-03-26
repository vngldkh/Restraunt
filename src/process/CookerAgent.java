package process;

import java.util.Optional;

public class CookerAgent {
    private Cooker cooker;
    private boolean available = true;

    public CookerAgent(Cooker cooker) {
        this.cooker = cooker;
    }

    public int getId() {
        return cooker.cookId();
    }

    public void Engage() {
        available = false;
    }

    public void Free() {
        available = true;
    }
}

package equipment;

import equipment.Equipment;

public class EquipmentAgent {
    Equipment equipment;
    private boolean available = true;


    public EquipmentAgent(Equipment equipment) {
        this.equipment = equipment;
    }

    public boolean isNull() {
        return equipment == null;
    }
    public boolean isAvailable() {
        return available;
    }

    public void Use() {
        available = false;
    }

    public void Free() {
        available = true;
    }

    public int getId() {
        return equipment.equip_id();
    }
}

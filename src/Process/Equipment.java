package process;

public class Equipment {
    int equip_type;
    String equip_name;
    Boolean equip_active;

    public Equipment(int equip_type,
                       String equip_name,
                       Boolean equip_active) {

        this.equip_type = equip_type;
        this.equip_name = equip_name;
        this.equip_active = equip_active;
    }
}

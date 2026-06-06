package theGame;
import java.util.*;
class DefaultWeapon extends Weapon {
    public DefaultWeapon(ArrayList<Ammo> reserve) {
        super.setName("Original");
        super.setCapacity(6);
        super.setReloadSpd(60);
        this.reload(reserve);
    }
    
    @Override
    public Ammo shoot() {
        return super.getCartridge().remove(0);
    }

    @Override
    public void special() {
        super.getCartridge().add(Math.min(super.getCartridge().size()-1, 1), this.shoot());
    }

    @Override
    public boolean reload(ArrayList<Ammo> reserve) {
        for (int i = super.getCartridge().size(); i < super.getCapacity() && reserve.size() > 0; i++) {
            super.getCartridge().add(reserve.remove(0));
        }
        return !(reserve.size() == 0 && super.getCartridge().size() != super.getCapacity());
    }
}
package theGame;

import java.util.*;

import javafx.scene.image.Image;

/**
 * A basic projectile ammo that launches a bullet, can apply effects
 * @author Eric Wang
 */
public class RegularAmmo extends Ammo {

    /** The timer for the ammo before it's deleted */
    private int timer;

    /** The number of enemies the ammo can pierce */
    private int pierce;

    /** the small icon in the display */
    private Image smallSprite;

    /** the big sprite when its actually shot */
    private Image bigSprite;

    /** a list of all collidables the ammo is immune to */
    private ArrayList<ICollidable> immunityList = new ArrayList<>();

    /** a list of all timers for all immunities currently active */
    private HashMap<ICollidable, Integer> immunityTimers = new HashMap<>();

    /**
     * Archaic constructor used before fileloading, takes in less stuff
     *
     * @param damage     base damage
     * @param projSpd    projectile speed
     * @param recoil     recoil applied on firing
     * @param effects    space-separated effect string definitions
     * @param size       projectile size
     * @param duration   lifetime duration
     * @param fireDelay  delay between shots
     * @param pierce     number of entities it can pierce
     */
    public RegularAmmo(int damage, double projSpd, double recoil, String effects, double size, double duration, int fireDelay, int pierce) {

        this.damage = damage;
        this.projSpd = projSpd;
        this.recoil = recoil;
        this.upgradeable = "";
        this.xvelocity = 0;
        this.yvelocity = 0;
        this.xpos = 0;
        this.ypos = 0;
        this.size = size;
        this.duration = duration;
        this.fireDelay = fireDelay;
        this.pierce = pierce;

        this.timer = (int) super.getDuration();

        ArrayList<String> tempeff = new ArrayList<>();
        tempeff.addAll(List.of(effects.split(" ")));
        super.effects = tempeff;
    }

    /**
     * Constructs a fully defined RegularAmmo with like everything basically just check the parameters
     *
     * @param id          unique identifier
     * @param name        ammo name
     * @param type        ammo type
     * @param rarity      rarity level
     * @param effects     space-separated encoded effects
     * @param spriteLink  path to large sprite image
     * @param iconLink    path to icon image
     * @param damage      base damage
     * @param projSpd     projectile speed
     * @param recoil      recoil value
     * @param size        projectile size
     * @param duration    lifetime duration
     * @param fireDelay   firing delay
     * @param pierce      pierce count
     */
    public RegularAmmo(int id, String name, String type, int rarity, String effects, String spriteLink, String iconLink, int damage, double projSpd, double recoil, double size, double duration, int fireDelay, int pierce) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.rarity = rarity;

        this.bigSprite = new Image(spriteLink);
        this.smallSprite = new Image(iconLink);

        this.damage = damage;
        this.projSpd = projSpd;
        this.recoil = recoil;
        this.upgradeable = "";

        this.xvelocity = 0;
        this.yvelocity = 0;
        this.xpos = 0;
        this.ypos = 0;

        this.size = size;
        this.duration = duration;
        this.fireDelay = fireDelay;
        this.pierce = pierce;

        this.timer = (int) super.getDuration();

        ArrayList<String> tempeff = new ArrayList<>();
        tempeff.addAll(List.of(effects.split(" ")));
        super.effects = tempeff;
    }

    /**
     * Returns the small icon representation of this ammo.
     *
     * @return icon image
     */
    @Override
    public Image getIcon() {
        return this.smallSprite;
    }

    /**
     * Returns the full-size visual representation of this ammo.
     *
     * @return sprite image
     */
    @Override
    public Image getShape() {
        return this.bigSprite;
    }

    /**
     * Determines whether this projectile is marked for deletion
     *
     * @return true if expired or fully consumed by piercing, false otherwise
     */
    @Override
    public boolean isDeleted() {
        if (this.timer <= 0) {
            resetTimer();
            return true;
        }
        return this.pierce <= -1;
    }

    /**
     * Checks whether this projectile is immune to a given collision target.
     *
     * @param col the collidable object
     * @return true if immune due to type, immunity list, or pierce depletion
     */
    @Override
    public boolean isImmune(ICollidable col) {
        if (col.getClass() == this.friendly) {
            return true;
        }
        if (this.immunityList.contains(col)) {
            return true;
        }
        return this.pierce < 0;
    }

    /**
     * Updates projectile position and handles immunity timers.
     * 
     * Immunity entries expire after a fixed duration.
     */
    @Override
    public void move() {
        for (int i = 0; i < this.immunityList.size(); i++) {
            ICollidable c = this.immunityList.get(i);

            this.immunityTimers.replace(c, this.immunityTimers.get(c) - 1);

            if (this.immunityTimers.get(c) <= 0) {
                this.immunityTimers.remove(c);
                this.immunityList.remove(i);
                i--;
            }
        }

        this.xpos += this.xvelocity;
        this.ypos += this.yvelocity;
    }

    /**
     * Handles collision with another object and decreases the pierce if it actually hits
     *
     * @param col the object collided with
     */
    @Override
    public void collide(ICollidable col) {
        if (!this.immunityList.contains(col) && this.pierce >= 0) {
            this.pierce--;
            this.immunityList.add(col);
            this.immunityTimers.put(col, 15);
        }
    }

    /**
     * Upgrades this ammo instance (currently unimplemented).
     */
    @Override
    public void upgrade() {
        
    }

    /**Clones the current GameAmmo object, and resets the immunity timers so they aren't shared between different instances
     * 
     * @return the cloned object
     */
    @Override
    public Ammo clone() {
        try {
            RegularAmmo shallowCopy = (RegularAmmo)super.clone();
            shallowCopy.resetImmunities();
            return shallowCopy;
        } catch (Exception e) {
            return null;
        }
    }

    /**Method that resets the immunities of the Ammo
     * For use in cloning
     */
    public void resetImmunities() {
        this.immunityList = new ArrayList<ICollidable>();
        this.immunityTimers = new HashMap<ICollidable, Integer>();
    }

    /**
     * Applies scripted effects to the player based on a trigger condition and a numeric value.
     * Supported effects include:
     * Draw - draws cards (ammos) from reserve
     * Exhaust - removes this ammo from discard (basically can only be used once per battle (ever played STS?))
     * Consume - consumes ammo to modify stats
     * Create - generates new ammo instances
     *
     * @param player    the player affected
     * @param condition trigger condition
     */
    @Override
    public void applyEffect(Player player, String condition) {
        for (int i = 0; i < this.effects.size(); i++) {

            String[] total = this.effects.get(i).split("_");
            String cond = total[0];

            if (!condition.equals(cond)) continue;

            String eff = total[1];

            if (eff.equals("Draw")) {
                double val = Double.parseDouble(total[2]);

                for (int j = 0; j < val; j++) {
                    if (!player.getReserve().isEmpty()) {
                        player.getCartridge().add(player.getReserve().remove(0));
                    } else if (player.getDiscardSize() != 0) {
                        player.refreshReserve();
                        player.getCartridge().add(player.getReserve().remove(0));
                    }
                }

            } else if (eff.equals("Exhaust")) {
                player.getDiscard().remove(this);

            } else if (eff.equals("Consume")) {
                double times = Double.parseDouble(total[2]);
                int statnum = Integer.parseInt(total[3]);

                for (int j = 0; j < times; j++) {

                    if (player.getCartridge().isEmpty()) {
                        break;
                    }

                    player.getCartridge().remove(0);

                    for (int z = 0; z < statnum; z++) {
                        String stat = total[4 + 2 * z];
                        double val = Double.parseDouble(total[5 + 2 * z]);

                        if (stat.equals("Dura")) {
                            this.timer += val;
                        } else if (stat.equals("Size")) {
                            this.size += val;
                        } else if (stat.equals("Dmg")) {
                            this.damage += val;
                        }
                    }
                }

            } else if (eff.equals("Create")) {
                double times = Double.parseDouble(total[2]);
                int id = Integer.parseInt(total[3]);

                for (int j = 0; j < times; j++) {
                    player.getCartridge().add((Ammo) player.ALL_AMMO[id].clone());
                }
            }
        }
    }

    /**
     * Decreases the internal lifetime timer by one tick.
     */
    public void timerDown() {
        this.timer--;
    }

    /**
     * Resets the lifetime timer back to its original duration.
     */
    public void resetTimer() {
        this.timer = (int) this.duration;
    }
}
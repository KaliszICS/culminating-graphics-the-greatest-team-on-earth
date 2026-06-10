package theGame;
/**
 * An interface for things that apply or recieve effects
 * Objects must implement a way to affect players with a conditional check to see if they will activate
 */
interface IEffectable {
    void applyEffect(Player player, String condition);
}
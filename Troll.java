import java.util.*; 
import java.io.*; 
public class Troll extends Enemy{

  /** Creates item of type item   */
  private String name = "Troll";
  /** Initializes the Enemy with name and health
   */
  public Troll()
  {
    super("Troll", 10);
  }

  /** Attacks hero with a physical attack dealing 1-4 damage
   *  @param e - the entity that will be attacked 
   *  @return damage dealt to player
   */
  @Override 
  public String attack(Entity e)
  {
    Random rand = new Random();
    int h = rand.nextInt(4) + 1; 
    e.takeDamage(h);
    return "Enemy attacked back with " + h + " Damage!!";
  }
}

import java.util.*; 
import java.io.*; 
public abstract class Enemy extends Entity{
  /** Returns item
   *  @return item that the monster is holding.
   */
  private Item item;

  /** Creates an instance of the Item Generator */
  private ItemGenerator ig = ItemGenerator.getInstance();

  /** 
  *  Creates an Enemy object with specific name and HP
  *  @param n Name of the Enemy
  *  @param mHP Health of the Enemy
  */
  public Enemy(String n, int mHP)
  {
    super(n, mHP);
    item = ig.generateItem();  
  }

  /** Accessor for the Item that the monster holds
   *  @return item that the monster is holding.
   */
  public Item getItem(){
    return item; 
  }
  
  /** Attacks hero with a physical attack dealing 1-4 damage
   *  @param e the entity that is being attacked
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
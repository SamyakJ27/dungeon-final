import java.util.*; 
import java.io.*; 
public class Warrior extends EnemyDecorator
{
  /**
   *creates a warrior enemy type 
   *@param e - the enemy that will decorated as a Warrior 
   */
  public Warrior(Enemy e, Entity h)
  {
    super(e, e.getName() + " Warrior", e.getmaxHP() + 2, h);
    //attack(h);
  }

  /**
   *Override the attack method with a basic attack 
   *@param  e - the entity that will be attacked 
   *@return a string that contains how much damage was done  
   */
  @Override
  public String attack(Entity e)
  {
    Random rand = new Random();
    int h = rand.nextInt(4) + 1; 
    e.takeDamage(h);
    String extraAttack = super.attack(e);
    return "Enemy attacked back with " + h + " Damage!!\n" + extraAttack;
  }
}
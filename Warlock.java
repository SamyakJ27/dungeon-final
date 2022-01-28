import java.util.*; 
import java.io.*; 
public class Warlock extends EnemyDecorator implements Magical
{
  /**
    *Decorates a Magical Enemy as a Warlock  
    *@Param e - the name of the Magical Enemy
    */
  public Warlock(Enemy e, Entity h)
  {
    super(e, e.getName() + " Warlock", e.getmaxHP() + 1, h);
    //attack(h);
  }
  /**
    * chooses a random magical attack to the entity passed and attacks it
    *@Param e - the entity that will take damage
    *@Return a string that shows how the entity was attacked and the damage dealt 
    */
  @Override
  public String attack(Entity e)
  {
    Random rand = new Random();
    int choice = rand.nextInt(3); 
    if (choice == 0)
    {
      return magicMissile(e);
    }
    else if(choice == 1)
    {
      return fireball(e); 
    }
    else if(choice == 2)
    {
      return thunderclap(e);
    }
    return "";
  }

  /**
    *uses a magicMisile on the Entity and deals a damage on it
    *@Param e - the entity that will take Damage 
    *@Return a string that shows the entity was attacked with a magicMissile and the damage dealt
    */
    @Override 
  public String magicMissile(Entity e)
  {
    Random rand = new Random();
    String extraAttack = super.attack(e);
    int h = rand.nextInt(4+1) + 4;
    e.takeDamage(h);
    return getName() + " strikes " + e.getName() + " with Magic Missile for " + h + " Damage.\n" + extraAttack;
  }

  /**
    *uses a fireball on the Entity and deals a damage on it
    *@Param e - the entity that will take Damage 
    *@Return a string that shows the entity was attacked with a fireball and the damage dealt
    */
  @Override 
  public String fireball(Entity e)
  {
    Random rand = new Random();
    int h = rand.nextInt(4+1) + 4;
    String extraAttack = super.attack(e);
    e.takeDamage(h);
    return getName() + " hits " + e.getName() +" with a Fireball for " + h + " Damage.\n" + extraAttack;
  }

  /**
    *uses a thunderclap on the Entity and deals a damage on it
    *@Param e - the entity that will take Damage 
    *@Return a string that shows the entity was attacked with a thunderclap and the damage dealt
    */
  @Override 
  public String thunderclap(Entity e)
  {
    Random rand = new Random();
    int h = rand.nextInt(4+1) + 4;
    String extraAttack = super.attack(e);
    e.takeDamage(h);
    return getName() + " zaps "+ e.getName() +" with Thunderclap for " + h + " Damage.\n" + extraAttack;
  }
}

import java.util.*; 
import java.io.*; 
public class EnemyGenerator{
  private static EnemyGenerator instance = null;
/** holds array list of enemies from file   */
  private ArrayList<Enemy> enemyList; //= new ArrayList<Enemy>(List.of(new Goblin(), new Froglok(), new Orc(),new Troll()));

/** creates Object of ItemGenerator   */
  private ItemGenerator ig = ItemGenerator.getInstance();

/** Reads enemy list and appends enemies to list   
 *  @param ig used to create the item which the enemy will drop when defeated
 */
  private EnemyGenerator() 
  {
    enemyList = new ArrayList<Enemy>(); 
    enemyList.add(new Goblin()); 
    enemyList.add(new Froglok());
    enemyList.add(new Orc()); 
    enemyList.add(new Troll()); 
  }

  /**
  *  Create an instance of the EnemyGenerator object
  *  @return instance of EnemyGenerator object
  */
  public static EnemyGenerator getInstance ()
  {
    if(instance == null)
    {
      instance = new EnemyGenerator();
    }
    return instance;
  }
  
/** creates the enemy for Enemy room    
  * @param level - the level the player is on
  * @return a random Enemy from the enemy list
  */
  public Enemy generateEnemy(int level, Entity h)
  { 
    Random rand = new Random(); 
    int a = rand.nextInt(enemyList.size()); 
    Enemy e = enemyList.get(a); 
    //okay, so this is almost done but i need a way to create the enemy as a new enemy; 
    if(level > 1)
    {
      Factory attempt = new EnemyFactory(); 
      a = rand.nextInt(2) + 1; 
      Enemy enemyy;
      int power = 2;
      if(a%2 == 0)
      {
        enemyy = attempt.createEnemy("Warrior", e, h);
        while(power < level)
        {
          enemyy = attempt.createEnemy("Warrior", enemyy, h); 
          power++;  
        }
      }
      else{
        enemyy = attempt.createEnemy("Warlock", e, h);
        while(power < level)
        {
          enemyy = attempt.createEnemy("Warlock", enemyy, h); 
          power++;  
        }
      }
      return enemyy; 
    }

    //hardcoding this 
    if(e instanceof Troll)
    {
      e = new Troll(); 
    }
    if(e instanceof Froglok)
    {
      e = new Froglok(); 
    }
    if(e instanceof Goblin)
    {
      e = new Goblin();
    }
    if(e instanceof Orc)
    {
      e = new Orc(); 
    }
    //Enemy enemyy = new Warrior(new Troll());
    return e; 
  }

}

public class EnemyFactory extends Factory{

  /**
   * Creates either a warlock or warrior enemy type 
   *@param type - the type of decoration the enemy gets 
   *@param e - the enemy to be decorated 
   *@return the decorated enemy  
   */
  @Override
  public Enemy createEnemy(String type, Enemy e, Entity h)
  {
    if(type.equals("Warlock"))
    {
      return new Warlock(e, h); 
    }
    else if(type.equals("Warrior"))
    {
      return new Warrior(e, h); 
    }
    return null; 
  }
}
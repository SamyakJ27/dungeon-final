

public abstract class Factory{
  /**
   * uses factory design pattern to make an enemy type 
   *@param type - the type of decoration the enemy gets  
   *@param en - the enemy that will be decorated 
   *@return a decorated enemy 
   */
  public Enemy makeEnemy(String type, Enemy en, Entity h)
  {
    Enemy e = createEnemy(type, en, h);
    return e;
  }

  /**
   *Makes a decorated enemy 
   *@param type - the type of decoration hte enemy gets 
   *@param e - the enemy that will be decorated 
   *@return a decorated enemy  
   */
  public abstract Enemy createEnemy(String type, Enemy e, Entity h);
}                                                  
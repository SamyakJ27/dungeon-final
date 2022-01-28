
public abstract class EnemyDecorator extends Enemy {
  /** Enemy object */
  private Enemy enemy; 

  /** Type of enemy */
  private String type; 

  //private int typeHP;
  /** Constructor for the EnemyDecorator pattern
  *   @param e - instance of enemy
  *   @param n - the name of the enemy 
  *   @param mHP - the max HP of the enemy 
  */
  public EnemyDecorator(Enemy e, String n, int mHP, Entity h)
  {
    super(n, mHP);
    //attack(h);
  }

/** 
 *  Attack function to be overridden in subclasses
 *  @param Entity e that will be attacked
 *  @return Object enemy attacking entity
 */
  @Override
  public String attack(Entity e)
  {
    return super.attack(e);
  }
}
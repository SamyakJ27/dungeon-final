
/** 
  * Entity.java - abstract class which Hero and Enemy inherits 
  */
public abstract class Entity
{
  /** Name of the entity */
  private String name; 

  /** Max hp for the entity */
  private int maxHp; 

  /** Current hp for the entity */
  private int hp; 

  /** Initializes an Entity's name and max health and sets the entity hp to it's current max health
  *   @param n    Entity Name.
  *   @param mHp  Max hp.
  */
  public  Entity(String n, int mHp)
  {
    name = n;
    maxHp = mHp;
    hp = maxHp;
  }

  /** Abstract attack method for subclasses to override 
   *  @param e    Object of type Entity.
   */
  public abstract String attack(Entity e);
  
  /** Accesses name
   *  @return the name of the Entity.
   */
  public String getName()
  { 
    return name; 
  } 

  /** Accesses hp
   *  @return current hp of the entity.
   */
  public int getHP()
  {
    return hp; 
  }

  /** Accesses maxHp
   *  @return max hp of the entity
   */
  public int getmaxHP()
  {
    return maxHp; 
  }

  /** 
   *  Heals the entity when using a health potion
   */
  public void heal(int h)
  {
    hp = hp+h;
  }

  /**
   *  Subtracts the entity's hp by the damage the other entity deals
   */
  public void takeDamage(int h)
  {
    hp = hp-h;
  }

  /** Returns entity name, and current hp
   *  @return name, hp, max hp
   */
  public String toString()
  {
    return name + "\nHP: " + hp + "/" + maxHp;
  }
}
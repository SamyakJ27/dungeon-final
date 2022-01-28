
public interface Magical
{
/** String of list of magic attacks   */
public static final String MAGIC_MENU = "1. Magic Missile\n2. Fireball\n3. Thunderclap"; 

/** 
 *  Magic missile function
 *  @param Entity that will be attacked
 */
public String magicMissile(Entity e); 

/** 
 *  Fireball function
 *  @param Entity that will be attacked
 */
public String fireball(Entity e); 

/** 
 *  Thunder Clap function
 *  @param Entity that will be attacked
 */
public String thunderclap(Entity e); 
}
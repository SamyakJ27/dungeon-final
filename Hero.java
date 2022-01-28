
import java.util.*; 
import java.io.*;
import java.util.ArrayList.*;
import java.awt.*;
import java.lang.*;
import java.util.Scanner;

/** Hero class is a representation of a Hero,
 * which extends an Entity.
 */
public class Hero extends Entity implements Magical
{
  /** Creates arraylist of items  */
  private ArrayList<Item> items = new ArrayList<Item>(); 

  /** Creates Object map  */
  private Map map; 

  /** location of the character */
  private Point location; 

  /** gold in the Hero inventory */
  private int gold;

/** Initializes Hero with name and map choice, sets point location to starting point of map
 *  @param n  Hero name
 *  @param m  Map number
 */
  public Hero(String n, Map m, int g)
  {
    super(n, 25);
    map = m;
    location = map.findStart();
    gold = g;
  }

/** This function is used when the hero uses a key */

  public void useKey()
  { 
    for (int i = 0; i < getNumItems(); i++)
    {
      if (items.get(i).getType() == 'k')
      {
        dropItem(i);
      }
    }
  }

/** Checks if the hero is holding a key
 *  @return true if the hero holds a key
 *  @return false if the hero does not have a key
 */
  public boolean hasKey()
  {
    for (int i = 0; i < getNumItems(); i++)
    {
      if (items.get(i).getType() == 'k')
      {
        return true;
      }
    }
    return false;
  }

/** Checks if the hero has armor in the inventory
 *  @return index of item if armor is present
 *  @return -1 if the hero does not have armor
 */
  public int hasArmorItem()
  {
    for (int i = 0; i < getNumItems(); i++)
    {
      if (items.get(i).getType() == 'a')
      {
        return i;
      }
    }
    return -1;
  }

/** Accessor for hero's gold
 *  @return gold in hero's inventory
 *  
 */
  public int getGold()
  {
    return gold;
  }

/** Mutator for hero's gold
 *  changes value when the hero picks up gold
 *  
 */
  public void collectGold(int g)
  {
    gold += g;
  }

/** Mutator for hero's gold
 *  changes value when the hero spends gold
 *  
 */
  public void spendGold(int g)
  {
    gold -= g;
  }

/** Overrides attack method in entity
 *  @param   e enemy that will be damaged
 *  @return String describing what enemy was attacked and how much damage was taken
 */
  @Override 
  public String attack(Entity e)
  {
    int h;
    Random rand = new Random();
    h = rand.nextInt(4) + 1; 
    e.takeDamage(h);
    return getName() + " attacked " + e.getName() + " for " + h + " damage.\n";
  }

/** Overrides magic missile in Magical interface
 * randomly chooses damage between 4 and 8 
 * @param   e enemy that will be damaged
 * @return  damage dealt to enemy
 */
  @Override
  public String magicMissile(Entity e)
  {
    Random rand = new Random();
    int h = rand.nextInt(4+1) + 4;
    e.takeDamage(h);
    return getName() + " attacks " + e.getName() +" with a Magic Missile for "+ h +" damage.\n";
  }

/** Overrides fireball in Magical interface
 * randomly chooses damage between 4 and 8 
 * @param   e enemy that will be damaged
 * @return  damage dealt to enemy
 */
  @Override
  public String fireball(Entity e)
  {
    Random rand = new Random();
    int h = rand.nextInt(4+1) + 4;
    e.takeDamage(h);
    return getName() + " attacks " + e.getName() +" with a Fireball for "+ h + " damage.\n";
  }

/** Overrides thunder clap in Magical interface
 * randomly chooses damage between 4 and 8 
 * @param e enemy that takes damage
 * @return  damage dealt to enemy
 */
  @Override
  public String thunderclap(Entity e)
  {
    Random rand = new Random();
    int h = rand.nextInt(4+1) + 4;
    e.takeDamage(h);
    return getName() + " zaps " + e.getName() +" with Thunderclap for "+ h +" damage.\n";
  }

/** 
 *  @return Hero name with hp and items
 */
  public String toString()
  {
    return super.toString()+itemsToString();
  } 

/** takes items in hero inventory and returns it in a string
 *  @return Hero inventory list
 */
  public String itemsToString()
  {
    String s = "\nInventory:\n"; 
    int itemCount = getNumItems();
    if (itemCount > 0)
    {
      for (int i = 0; i < itemCount; i++)
      {
        s += (i+1) + ": " + items.get(i).getName();
        s += "\n";
      }
    }
    return s;
  }

/**
 *  @return number of items in inventory
 */
  public int getNumItems()
  {
    return items.size(); 
  }

/** picks up item if there is inventory space
 *  @param i item found from enemy corpse or item room
 *  @return true if item was picked up, false if inventory is full
 */
  public boolean pickUpItem(Item i)
  {
    if(getNumItems() < 5)
    {
      items.add(i);
      return true;
    }
    
    return false;
  } 

/** Drinks potion, filling hp and removing potion from inventory  */
  public void drinkPotion()
  {
    heal(getmaxHP()-getHP()); 
    for (int i = 0; i < getNumItems(); i++)
    {
      if (items.get(i).getType() == 'p')
      {
        dropItem(i);
      }
    }
  }

/** Removes item from inventory 
 *  @param index index of the item
 */
  public Item dropItem(int index)
  {
    Item a = items.get(index);
    items.remove(index);
    return a;
  }

/** Checks if hero has a potion
 *  @return true if hero has a potion, false if not.
 */
  public boolean hasPotion()
  {
    for (int i = 0; i < getNumItems(); i++)
    {
      if (items.get(i).getType() == 'p')
      {
        return true;
      }
    }
    return false;
  }

/** Return point of hero
 *  @return location of hero in x, y.
 */
  public Point getLocation()
  {
    return location;
  } 

/** moves the character north
 *  @return f if the hero attempts to move out of the map, n if character moved north
 */
  public char goNorth()
  {
    map.reveal(getLocation()); 
    if((int)location.getX() != 0)
    {
      location.setLocation((int)location.getX() - 1, (int)(location.getY()));
    }
    else
    {
      System.out.println("Edge of Map cannot go there");
      return 'f';  //f is hte char to return if the user cannot do this move. 
    }
    return 'n';
  } 

/** moves the character south
 *  @return f if the hero attempts to move out of the map, s if character moved south
 */
  public char goSouth()
  {
    map.reveal(getLocation()); 
    if((int)location.getX() < 4)
    {
      location.setLocation((int)location.getX() + 1, (int)location.getY());
    }
    else
    {
      System.out.println("Edge of Map cannot go there");
      return 'f';  //f is the char to return if the user cannot do this move.
    }
    return 's';
  }

/** moves the character east
 *  @return f if the hero attempts to move out of the map, e if character moved east
 */
  public char goEast()
  {
    map.reveal(getLocation()); 
    if((int)location.getY() < 4)
    {
      location.setLocation((int)location.getX() , (int)location.getY() + 1);
    }
    else
    {
      System.out.println("Edge of Map cannot go there");
      return 'f';  //f is hte char to return if the user cannot do this move.
    }
    return 'e';
  }

/** moves the character west
 *  @return f if the hero attempts to move out of the map, w if character moved west
 */
  public char goWest()   //its is pronounced "weast" all hail patrick
  {
    map.reveal(getLocation()); 
    if((int)location.getY() != 0)
    {
      location.setLocation((int)location.getX(), (int)location.getY() - 1);
    }
    else 
    {
      System.out.println("Edge of Map cannot go there");
      return 'f';  //f is the char to return if the user cannot do this move.
    }
    
    return 'w'; 
  }
}
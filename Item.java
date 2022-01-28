
public class Item implements Cloneable{
  /** Name of the item  */
  private String name;

  /** Gold value of the item in the store */
  private int value;

  /** Type of item in the inventory to differentiate items */
  private char type;

  /** Initializes the Item with the name  
   *  @param n name
   */
  Item(String n, int v, char t){
    name = n;
    value = v;
    type = t;
  }

  /** Accessor for the Item value
  *  @return the Item's value in gold
  */
  int getValue()
  {
    return value;
  }

  /** 
  *  Accessor for the Item type
  *  @return type of item in char
  */
  char getType()
  {
    return type;
  }

  /**
   *  Accessor for the Item name
   *  @return name of the item
   */
  String getName(){
    return name;
  }

  /** 
  *  Clone function for items
  *  @return new cloned item
  */
  @Override
  public Item clone() throws CloneNotSupportedException
  {
    super.clone();
    return new Item(this.name, this.value, this.type);
  }
}
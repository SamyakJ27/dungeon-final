
import java.util.*; 
import java.io.*;
public class ItemGenerator{

  /** Arraylist of Item Objects   */
  private ArrayList<Item> itemList;

  /** Creates a static ItemGenerator instance which is initially null */
  private static ItemGenerator instance = null;

  /**
    *Instantiates an ItemGenerator object that reads from the item list and and stores it into an arraylist 
    */
  private ItemGenerator(){
    String token2 = "";
    itemList = new ArrayList<Item>();
    try 
    {
      Scanner scanner = new Scanner(new File("ItemList.txt"));
      while (scanner.hasNext()) 
        {
          token2 = scanner.nextLine();
          String str[] = token2.split(",");
          if(str[2].equals("p"))
          {
            int a = Integer.parseInt(str[1]);
            char b = str[2].charAt(0);
            Item i = new Item(str[0], a, b);
            itemList.add(i);
          }
          else if(str[2].equals("k"))
          {
            int a = Integer.parseInt(str[1]);
            char b = str[2].charAt(0);
            Item i = new Item(str[0], a, b);
            itemList.add(i);
          }
          else if(str[2].equals("m"))
          {
            int a = Integer.parseInt(str[1]);
            char b = str[2].charAt(0);
            Item i = new Item(str[0], a, b);
            itemList.add(i);
          }
          else if(str[2].equals("a"))
          {
            int a = Integer.parseInt(str[1]);
            char b = str[2].charAt(0);
            Item i = new Item(str[0], a, b);
            itemList.add(i);
          }
        }
      scanner.close();
    }
    catch (FileNotFoundException e) 
    {
      e.printStackTrace();
    }
  }

  /** 
  *  Creates an instance of the ItemGenerator if it does not exist
  *  @return instance of item generator
  */
  public static ItemGenerator getInstance()
  {
    if(instance == null)
    {
      instance = new ItemGenerator();
    }
    return instance;
  }

  /** 
  *  Accessor for Health Potions
  *  @return the Health potion object
  */
  public Item getPotion()
  {
    return itemList.get(0);
  }

  /** 
  *  Accessor for Keys
  *  @return the Key object
  */
  public Item getKey()
  {
    return itemList.get(1);
  }

  /**
  *  Creates a random object and returns it 
  *  @return a random Item from the Item list
  */
  public Item generateItem()
  {
    Random rand = new Random(); 
    int a = rand.nextInt(itemList.size()); 
    try{
      Item toReturn = itemList.get(a).clone(); //now need to add clone in item.java
      //System.out.println("using clone method");
      return toReturn;
    }
    catch(CloneNotSupportedException e)
    {
      e.printStackTrace();
    }
     
    return itemList.get(a); 
  }
}
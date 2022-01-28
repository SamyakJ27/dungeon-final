
//@Author Samyak Jain
//@Author Joshua Decano 
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.util.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.lang.*;

class Main {
  static final Scanner s = new Scanner(System.in);
  public static void main(String[] args) //throws IOException 
  { 
    ItemGenerator ig = ItemGenerator.getInstance();
    EnemyGenerator eG = EnemyGenerator.getInstance();
    String playerName;
    System.out.print("What is your name, traveler? ");
    playerName = s.nextLine();
    Map p = Map.getInstance();
    p.loadMap(1);
    Hero h = new Hero(playerName, p, 50);
    
    boolean loop = true; 
    int choice = 0;
    int level = 1;
    int levelnum = 1;
    
    //first opening at the store 
    System.out.print("\nWould you like to go to the store? \n1. Yes \n2. Keep Playing \n Your Choice: "); 
    while(!(choice != 1 ^ choice != 2))
    {
      try 
      {
        choice = s.nextInt(); 
        if(choice == 1)
        {
          store(h,ig);
        }
        if(!(choice != 1 ^ choice != 2))
        {
          choice = 0; 
          System.out.println("Enter 1 or 2. Try Again!!"); 
        }
        
      }
      catch(InputMismatchException er)
      {
        s.nextLine(); 
        System.out.println("\nEnter a NUMBER Please!!"); 
      }
    }
    
    while(loop)
    {
      System.out.println(h); 
      p.displayMap(h.getLocation()); 

      System.out.print("\n\n 1. Go North\n 2. Go South\n 3. Go East\n 4. Go West\n 5. Quit \n Your choice: ");
      //try catch to make sure that input is number between 1-5 and not something random.
      try
      {
        choice = s.nextInt();  
      }
      catch(InputMismatchException er)
      {
        s.nextLine(); 
        System.out.println("\nEnter a NUMBER Please!!");
        choice = 0;
      }
      catch(NumberFormatException er)
      {
        System.out.print("\n\n 1. Go North\n 2. Go South\n 3. Go East\n 4. Go West\n 5. Quit \n Your choice: ");
        choice = s.nextInt();
      }
       
      switch(choice)
      {
        case 1: 
          if(h.goNorth() == 'f')
          {
           System.out.println("\nNot an option try again!!\n"); 
           choice = 0; 
           s.reset();
          } 
          else
          {
            char loc = p.getCharAtLoc(h.getLocation());
            if (loc == 'm')
            {
              if(!(monsterRoom(h, p, eG, levelnum)))
              {
                if(h.getHP() > 0)
                {
                  h.goSouth();
                }
                else
                {
                  loop = false;
                }
                
              }
            }
            else if (loc == 'i')
            {
              itemRoom(h, p, ig);
              p.removeCharAtLoc(h.getLocation());
            }
            else if(loc == 's')
            {
              System.out.print("\nWould you like to go to the store? \n1. Yes \n2. Keep Playing \n Your Choice: "); 
              choice = 0;
              while(!(choice != 1 ^ choice != 2))
              {
                try 
                {
                  choice = s.nextInt(); 
                  if(choice == 1)
                  {
                    store(h,ig);
                  }
                  if(!(choice != 1 ^ choice != 2))
                  {
                    choice = 0; 
                    System.out.println("Enter 1 or 2. Try Again!!"); 
                  }
                }
                catch(InputMismatchException er)
                {
                  s.nextLine(); 
                  System.out.println("\nEnter a NUMBER Please!!"); 
                }
              }
            }
            else if(loc == 'f')
            {
              if (h.hasKey() == true)
              {
                System.out.println("You used a Key to open the door to the next area.");
                System.out.println("The Key broke upon opening the door");
                level = (level+1);
                h.useKey();
                if(level > 3)
                {
                  level = 1;
                }
                p.loadMap(level);
                levelnum++;
                System.out.println("\n\n You've reached Level " + levelnum + "!!\n");
                
                //going to the store now 
                System.out.print("\nWould you like to go to the store? \n1. Yes \n2. Keep Playing \n Your Choice: "); 
                choice = 0;
                while(!(choice != 1 ^ choice != 2))
                {
                  try 
                  {
                    choice = s.nextInt(); 
                    if(choice == 1)
                    {
                      store(h,ig);
                    }
                    if(!(choice != 1 ^ choice != 2))
                    {
                      choice = 0; 
                      System.out.println("Enter 1 or 2. Try Again!!"); 
                    }
                  }
                  catch(InputMismatchException er)
                  {
                    s.nextLine(); 
                    System.out.println("\nEnter a NUMBER Please!!"); 
                  }
                }
              }
              else
              {
                System.out.println("You do not have a key to enter the next area, return to the store to purchase one!");
                h.goSouth();
              }
            }
            else 
            {
              System.out.println("There was nothing here.");
            }
          }
          break;
        case 2: 
          if(h.goSouth() == 'f')
          {
           System.out.println("\nNot an option try again!!\n"); 
           choice = 0; 
           s.reset();
          }
          else
          {
            char loc = p.getCharAtLoc(h.getLocation());
            if (loc == 'm')
            {
              if(!(monsterRoom(h, p, eG, levelnum)))
              {
                if(h.getHP() > 0)
                {
                  h.goNorth();
                }
                else
                {
                  loop = false;
                }
              }
            }
            else if (loc == 'i')
            {
              itemRoom(h, p, ig);
              p.removeCharAtLoc(h.getLocation());
            }
            else if(loc == 's')
            {
              System.out.print("\nWould you like to go to the store? \n1. Yes \n2. Keep Playing \n Your Choice: "); 
              choice = 0;
              while(!(choice != 1 ^ choice != 2))
              {
                try 
                {
                  choice = s.nextInt(); 
                  if(choice == 1)
                  {
                    store(h, ig);
                  }
                  if(!(choice != 1 ^ choice != 2))
                  {
                    choice = 0; 
                    System.out.println("Enter 1 or 2. Try Again!!"); 
                  }
                }
                catch(InputMismatchException er)
                {
                  s.nextLine(); 
                  System.out.println("\nEnter a NUMBER Please!!"); 
                }
              }
            }
            else if(loc == 'f')
            {
              if (h.hasKey() == true)
              {
                System.out.println("You used a Key to open the door to the next area.");
                System.out.println("The Key broke upon opening the door");
                level = (level+1);
                h.useKey(); 
                if(level > 3)
                {
                  level = 1;
                }
                p.loadMap(level);
                levelnum++;
                System.out.println("\n\n You've reached Level " + levelnum + "!!\n");
                
                //going to the store now 
                System.out.print("\nWould you like to go to the store? \n1. Yes \n2. Keep Playing \n Your Choice: "); 
                choice = 0;
                while(!(choice != 1 ^ choice != 2))
                {
                  try 
                  {
                    choice = s.nextInt(); 
                    if(choice == 1)
                    {
                      store(h,ig);
                    }
                    if(!(choice != 1 ^ choice != 2))
                    {
                      choice = 0; 
                      System.out.println("Enter 1 or 2. Try Again!!"); 
                    }
                  }
                  catch(InputMismatchException er)
                  {
                    s.nextLine(); 
                    System.out.println("\nEnter a NUMBER Please!!"); 
                  }
                }
              }
              else
              {
                System.out.println("You do not have a key to enter the next area, return to the store to purchase one!");
                h.goNorth();
              }
            }
            else
            {
              System.out.println("There was nothing here.");
            }
          }
           s.reset();
          break;
        case 3: 
          if(h.goEast() == 'f')
          {
           System.out.println("\nNot an option try again!!\n"); 
           choice = 0; 
           s.reset();
          }
          else
          {
            char loc = p.getCharAtLoc(h.getLocation());
            if (loc == 'm')
            {
              if(!(monsterRoom(h, p, eG, levelnum)))
              {
                if(h.getHP() > 0)
                {
                  h.goWest();
                }
                else
                {
                  loop = false;
                }
              }
            }
            else if (loc == 'i')
            {
              itemRoom(h, p, ig);
              p.removeCharAtLoc(h.getLocation());
            }
            else if(loc == 's')
            {
              System.out.print("\nWould you like to go to the store? \n1. Yes \n2. Keep Playing \n Your Choice: "); 
              choice = 0;
              while(!(choice != 1 ^ choice != 2))
              {
                try 
                {
                  choice = s.nextInt(); 
                  if(choice == 1)
                  {
                    store(h, ig);
                  }
                  if(!(choice != 1 ^ choice != 2))
                  {
                    choice = 0; 
                    System.out.println("Enter 1 or 2. Try Again!!"); 
                  }
                }
                catch(InputMismatchException er)
                {
                  s.nextLine(); 
                  System.out.println("\nEnter a NUMBER Please!!"); 
                }
              }
            }
            else if(loc == 'f')
            {
              if (h.hasKey() == true)
              {
                System.out.println("You used a Key to open the door to the next area.");
                System.out.println("The Key broke upon opening the door");
                h.useKey();
                level = (level+1);
                if(level > 3)
                {
                  level = 1;
                }
                p.loadMap(level);
                levelnum++;
                System.out.println("\n\n You've reached Level " + levelnum + "!!\n");
                
                //going to the store now 
                System.out.print("\nWould you like to go to the store? \n1. Yes \n2. Keep Playing \n Your Choice: "); 
                choice = 0;
                while(!(choice != 1 ^ choice != 2))
                {
                  try 
                  {
                    choice = s.nextInt(); 
                    if(choice == 1)
                    {
                      store(h,ig);
                    }
                    if(!(choice != 1 ^ choice != 2))
                    {
                      choice = 0; 
                      System.out.println("Enter 1 or 2. Try Again!!"); 
                    }
                  }
                  catch(InputMismatchException er)
                  {
                    s.nextLine(); 
                    System.out.println("\nEnter a NUMBER Please!!"); 
                  }
                }
              }
              else
              {
                System.out.println("You do not have a key to enter the next area, return to the store to purchase one!");
                h.goWest();
              }
            }
            else
            {
              System.out.println("There was nothing here.");
            }
          }
           
          break; 
        case 4: 
          if(h.goWest() == 'f')
          {
           System.out.println("\nNot an option try again!!\n"); 
           p.displayMap(h.getLocation());
           choice = 0; 
           s.reset();
          }
          else
          {
            char loc = p.getCharAtLoc(h.getLocation());
            if (loc == 'm')
            {
              if(!(monsterRoom(h, p, eG, levelnum)))
              {
                if(h.getHP() > 0)
                {
                  h.goEast();
                }
                else
                {
                  loop = false;
                }
              }
            }
            else if (loc == 'i')
            {
              itemRoom(h, p, ig);
              p.removeCharAtLoc(h.getLocation());
            }
            else if(loc == 's')
            {
              System.out.print("\nWould you like to go to the store? \n1. Yes \n2. Keep Playing \n Your Choice: "); 
              choice = 0;
              while(!(choice != 1 ^ choice != 2))
              {
                try 
                {
                  choice = s.nextInt(); 
                  if(choice == 1)
                  {
                    store(h, ig);
                  }
                  if(!(choice != 1 ^ choice != 2))
                  {
                    choice = 0; 
                    System.out.println("Enter 1 or 2. Try Again!!"); 
                  }
                }
                catch(InputMismatchException er)
                {
                  s.nextLine(); 
                  System.out.println("\nEnter a NUMBER Please!!"); 
                }
              }
            }
            else if(loc == 'f')
            {
              if (h.hasKey() == true)
              {
                System.out.println("You used a Key to open the door to the next area.");
                System.out.println("The Key broke upon opening the door");
                h.useKey(); 
                level = (level+1);
                if(level > 3)
                {
                  level = 1;
                }
                p.loadMap(level);
                levelnum++;
                System.out.println("\n\n You've reached Level " + levelnum + "!!\n");
                
                //going to the store now 
                System.out.print("\nWould you like to go to the store? \n1. Yes \n2. Keep Playing \n Your Choice: "); 
                choice = 0;
                while(!(choice != 1 ^ choice != 2))
                {
                  try 
                  {
                    choice = s.nextInt(); 
                    if(choice == 1)
                    {
                      store(h,ig);
                    }
                    if(!(choice != 1 ^ choice != 2))
                    {
                      choice = 0; 
                      System.out.println("Enter 1 or 2. Try Again!!"); 
                    }
                  }
                  catch(InputMismatchException er)
                  {
                    s.nextLine(); 
                    System.out.println("\nEnter a NUMBER Please!!"); 
                  }
                }
              }
              else
              {
                System.out.println("You do not have a key to enter the next area, return to the store to purchase one!");
                h.goEast();
              }
            }
            else
            {
              System.out.println("There was nothing here.");
            }
          }
           
          break; 
        case 5: 
          loop = false;
          System.out.println("\nExiting...."); 
          break;
        default: 
          System.out.println("\nNot an option try again!!"); 
          choice = 0; 
          s.reset();
          break;
      }
    }
    s.close();
    System.out.println("Thank you for Playing \nGame Over");
  }

  /**
   *enters into a monster room and has a monster for hte hero to fight 
   *@param h - the Hero that will fight hte monster 
   *@param m - the Map to update the hero's location and to reveal the letter there 
   *@param eg - the enemygenerator to generate a monster for hte hero to fight 
   *@param level - the level to know how powerful the enemy will be
   *@return a boolean of true or false to know if the hero dies, succeded, or left the room 
   */
  public static boolean monsterRoom(Hero h, Map m, EnemyGenerator eg, int level)
    {
      int decision = 0;
      Enemy e = eg.generateEnemy(level, h);
      while(!(decision != 1 ^ decision != 2)){
        System.out.println("You've encountered a " + e.getName());
        System.out.println(e.toString());
        System.out.println("1. Fight\n2. Run Away");
        if(h.hasPotion() == true)
        {
          System.out.println("3. Drink Health Potion");
        }
        System.out.print("Your choice: ");
        try
        {
          decision = s.nextInt();
          if(decision == 3 && h.hasPotion())
          {
            break;
          }
          if(decision != 1 && decision != 2)
          {
            System.out.println("\nChoose one of the numbered options shown above. Try again!\n");
          }
        }
        catch(InputMismatchException er)
        {
          s.nextLine(); 
          System.out.println("\nEnter a NUMBER Please!!\n");
        }
      }
      
      if (decision == 1)
      {
        m.removeCharAtLoc(h.getLocation());
        if(e instanceof Warlock)
        {
          Warlock a = (Warlock) e;
          return fight(h, a);
        }
        return fight(h,e);
      
      }
      if (decision == 2)
      {
          return false;
      }
      if(decision == 3 && h.hasPotion() == true)
      {
        h.drinkPotion(); 
        System.out.println(h);
        while(!(decision != 1 ^ decision != 2))
        {
          System.out.println("You've encountered a " + e.getName());
          System.out.println(e.toString());
          System.out.println("1. Fight\n2. Run Away");
          System.out.print("Your choice: ");
          try
          {
            decision = s.nextInt();
            if(decision != 1 && decision != 2)
            {
              System.out.println("\nChoose one of the numbered options shown above. Try again!\n");
            }
          }
          catch(InputMismatchException er)
          {
            s.nextLine(); 
            System.out.println("\nEnter a NUMBER Please!!\n");
          }
        }

        if (decision == 1)
        {
          m.removeCharAtLoc(h.getLocation());
          if(e instanceof Warlock)
          {
            Warlock a = (Warlock) e;
            return fight(h, a);
          }
          return fight(h,e);
        
        }
        if (decision == 2)
        {
            return false;
        }

      }
      return true;
    }
    

    /**
     *Enters the fighting menu for htet hero and enemy to fight until there is a winner 
     @param h - the hero that will fight 
     @param e - the enemy/monster that will fight the hero 
     @return a boolean to see if the hero lost or won 
     */
    public static boolean fight(Hero h, Enemy e)
    {
      int fit = 0;
      int turn = 1;
      boolean magic = false;
      if( e instanceof Warlock)
      {
        Warlock a = (Warlock) e;
        while(h.getHP() > 0 && a.getHP() > 0)
        {
          fit = 0;
          if(turn%2 != 0)
          {
            while(!(fit != 1 ^ fit != 2))
            {
              System.out.println(" 1) Physical Attack\n 2) Magic attack");
              System.out.print(" Your Choice: ");
              try
              {
                fit = s.nextInt();
                if(!(fit != 1 ^ fit != 2))
                {
                  System.out.println("\nChoose between 1 and 2. try again!\n");
                }
              }
              catch(InputMismatchException er)
              {
                s.nextLine();
                System.out.println("\nNot an option. Try again!\n"); 

              }
            }
            
            if(fit == 1)
            {
              System.out.println(h.attack(a));
            }
            else if(fit == 2)
            {
              fit = 0;
              
              while(fit!= 1 ^ fit != 2 ^ fit != 3)
              {
                System.out.println(Magical.MAGIC_MENU);
                try
                {
                  System.out.print("Your Choice: ");
                  fit = s.nextInt();
                  if(fit!= 1 ^ fit != 2 ^ fit != 3)
                  {
                    System.out.println("\nchoose between 1, 2, or 3. Try again!\n");
                  }
                }
                catch(InputMismatchException er)
                {
                  s.nextLine(); 
                  System.out.println("\nNot an option. Try again!\n");
                }

              }

              if(fit == 1)
              {
                System.out.println(h.magicMissile(a));
              }
              else if(fit == 2)
              {
                System.out.println(h.fireball(a));
              }
              else if(fit == 3)
              {
                System.out.println(h.thunderclap(a));
              }
          }
          
        }
        else
          {
            if(h.hasArmorItem() != -1)
            {
              Random rand = new Random();
              int dmg = rand.nextInt(4) + 1; 
              System.out.println(a.getName() + " attacked for " + dmg + " damage");
              System.out.println("Damage was neglected by armor in your inventory");
              h.dropItem(h.hasArmorItem());
              System.out.println("The armor piece was destroyed");
            }
            else
            {
              System.out.println(a.attack(h));
            }
          }
        turn++;
        fit = 0;
        }
      }
      else
      {
        while(h.getHP() > 0 && e.getHP() > 0)
        {
          fit = 0;
          if(turn%2 != 0)
          {
            while(!(fit != 1 ^ fit != 2))
            {
              System.out.println(" 1) Physical Attack\n 2) Magic attack");
              System.out.print(" Your Choice: ");
              try
              {
                fit = s.nextInt();
                if(!(fit != 1 ^ fit != 2))
                {
                  System.out.println("\nChoose between 1 and 2. try again!\n");
                }
              }
              catch(InputMismatchException er)
              {
                s.nextLine();
                System.out.println("\nNot an option. Try again!\n"); 

              }
            }
            
            if(fit == 1)
            {
              System.out.println(h.attack(e));
            }
            else if(fit == 2)
            {
              fit = 0;
              while(fit!= 1 ^ fit != 2 ^ fit != 3)
              {
                System.out.println(Magical.MAGIC_MENU);
                try
                {
                  System.out.print("Your Choice: ");
                  fit = s.nextInt();
                  if(fit!= 1 ^ fit != 2 ^ fit != 3)
                  {
                    System.out.println("\nChoose between 1, 2, or 3. Try again!\n");
                  }
                }
                catch(InputMismatchException er)
                {
                  s.nextLine(); 
                  System.out.println("\nNot an option. Try again!\n");
                }

              }

              if(fit == 1)
              {
                System.out.println(h.magicMissile(e));
              }
              else if(fit == 2)
              {
                System.out.println(h.fireball(e));
              }
              else if(fit == 3)
              {
                System.out.println(h.thunderclap(e));
              }
            }
            
          }
          else
            {
              if(h.hasArmorItem() != -1)
              {
                Random rand = new Random();
                int dmg = rand.nextInt(4) + 1; 
                System.out.println(e.getName() + " attacked for " + dmg + " damage");
                System.out.println("Damage was neglected by armor in your inventory");
                h.dropItem(h.hasArmorItem());
                System.out.println("The armor piece was destroyed");
              }
              else
              {
                System.out.println(e.attack(h));
              }
            }
          turn++;
          fit = 0;
        }
      }
      

      if(e.getHP() <= 0)
      {
        System.out.println("You defeated the " + e.getName());
        if(h.pickUpItem(e.getItem()))
        {
          System.out.println("You received a " + e.getItem().getName() + " from its corpse.");
        }
        else
        {
          
          while(fit != 1 ^ fit != 2 ^ fit != 3 ^ fit != 4 ^ fit != 5 ^ fit != 6)
          {
            System.out.println("You have too mny items!!\n Must drop one of them!! or choose 6 to avoid picking " + e.getItem().getName());
            System.out.print(h.itemsToString());
            System.out.print("6: quit \n Your choice: ");
            try
            {
              fit = s.nextInt();
              if(fit != 1 && fit != 2 && fit != 3 && fit != 4 && fit != 5 && fit != 6)
              {
                System.out.println("\nChoose one of the numbers options above. Try again!\n");
              }
            }
            catch(InputMismatchException er)
            {
              s.nextLine(); 
              System.out.println("\nNot an option. Try again!\n");
            }
          }
          
          switch(fit)
          {
            case 1:
              h.dropItem(0);
              h.pickUpItem(e.getItem());
              break;
            case 2: 
              h.dropItem(1);
              h.pickUpItem(e.getItem());
              break;
            case 3: 
              h.dropItem(2);
              h.pickUpItem(e.getItem());
              break;
            case 4:
              h.dropItem(3);
              h.pickUpItem(e.getItem());
              break;
            case 5:
              h.dropItem(4);
              h.pickUpItem(e.getItem());
              break;
            case 6:
              System.out.println("no Items picked up"); 
              break;
            default:
              System.out.println("Not an option. automatically avoid picking up new item."); 
              break;

          }
        }
      }
      else
      {
        System.out.println("\n\n" + e.getName() + " killed " + h.getName() + "\n");
        return false;
      }

      return true;
    }

    /**
     *a room that has an item for the hero to pick up 
     *@param h - the hero that will pick up the item 
     *@param m - the map that will change the location to show nothing after item picked up 
     *@param ig - the generator to create a random item for the hero 
     */
    public static void itemRoom(Hero h, Map m, ItemGenerator ig)
    {
      Item b = ig.generateItem(); 
      b = ig.generateItem();
      int fit = 0; 
      if(h.pickUpItem(b))
      {
        System.out.println("You found a " + b.getName() + "\n");
      }
      else
        {
          
          while(!(fit != 1 ^ fit != 2 ^ fit != 3 ^ fit != 4 ^ fit != 5 ^ fit != 6))
          {
            System.out.println("You have too mny items!!\n Must drop one of them!! or choose 6 to avoid picking " + b.getName());
            System.out.print(h.itemsToString());
            System.out.print("6: quit \n Your choice: ");
            try
            {
              fit = s.nextInt();
              if(fit != 1 && fit != 2 && fit != 3 && fit != 4 && fit != 5 && fit != 6)
              {
                System.out.println("\nChoose one of the numbers options above. Try again!\n");
              }
            }
            catch(InputMismatchException er)
            {
              s.nextLine(); 
              System.out.println("\nNot an option. Try again!\n");
            }
          }
          
          switch(fit)
          {
            case 1:
              h.dropItem(0);
              h.pickUpItem(b);
              break;
            case 2: 
              h.dropItem(1);
              h.pickUpItem(b);
              break;
            case 3: 
              h.dropItem(2);
              h.pickUpItem(b);
              break;
            case 4:
              h.dropItem(3);
              h.pickUpItem(b);
              break;
            case 5:
              h.dropItem(4);
              h.pickUpItem(b);
              break;
            case 6:
              System.out.println("no new items picked up"); 
              break;
            default:
              System.out.println("Not an option. automatically avoid picking up new item."); 
              break;

          }
        }
      
      
    }

    /**
     *A store that is at the starting menu and allows the hero to buy and sell stuff 
     *@param h - the hero that will make transactions at the store  
     *@param ig - the item generator to allow the hero to buy a certain item 
     */
    public static void store(Hero h, ItemGenerator ig)
    {
      int input = 0; 
      boolean loop = true;
      while(loop)
      {
        
        input = 0;
        while(!(input != 1 ^ input !=2 ^ input != 3 ^ input != 4))
        {
          System.out.println("You have " + h.getGold() + " coins.");
          System.out.println(" Store Menu: \n 1.buy healty potion (25 coins) \n 2.Buy Keys (50 coins) \n 3. sell stuff \n 4. quit"); 
          try
          {
            input = s.nextInt(); 
            if((input == 1 && h.getGold() < 25) || (input == 2 && h.getGold() < 50))
            {
              System.out.print("cannot purchase this item. Not enough coins");
              input = 0; 
            }
            else if(input == 3 && h.getNumItems() <= 0)
            {
              System.out.println("Do not have any items to sell. Try another option");
              input = 0;
            }
            else if(!(input != 1 ^ input!= 2 ^ input!= 3 ^ input != 4))
            {
              System.out.println("Choose numbers 1-4!! try again!");
            }
          }
          catch(InputMismatchException er)
          {
            s.nextLine();
            System.out.println("\nNot an option. Try again!\n"); 
          }
        }
        
        switch(input)
        {
          case 1:
            if(h.getGold() >= 25)
            {
              if((h.getNumItems() >=5))
              {
                System.out.println("Inventory full must sell items to buy.");
              }
              else
              {
                System.out.println("you have successfully bought potion!");
                h.spendGold(25);
                h.pickUpItem(ig.getPotion());
                System.out.println(h.itemsToString());
                System.out.println("You can continue shopping or exit the store by choosing option 4."); 
              }
            }
            break; 
          case 2:
            if(h.getGold() >= 50)
            {
              if((h.getNumItems() >=5))
              {
                System.out.println("Inventory full must sell items to buy.");
              }
              else
              {
                System.out.println("you have successfully bought a Key!");
                h.spendGold(50);
                h.pickUpItem(ig.getKey());
                System.out.println(h.itemsToString());
                System.out.println("You can continue shopping or exit the store by choosing option 4."); 
              }
            }
            break; 
          case 3: 
            int size = h.getNumItems(); 
            System.out.println(h.itemsToString()); 
            input = 0;
            while(!(input>=1 && input <= size))
            {
              try{
                input = s.nextInt(); 
                if(!(input >=1 && input <= size))
                {
                  System.out.println("choose a number between 1 and " + size); 
                }
              }
              catch(InputMismatchException er)
              {
                s.nextLine();
                System.out.println("\nNot an option. Try again!\n"); 
              }
            }
            Item sold = h.dropItem(input-1);
            System.out.println(h.getGold() + " - previous value");  //before value 
            h.collectGold(sold.getValue());
            System.out.println(h.getGold() + " - new value");  //adding the value of item sold
            System.out.println(h.itemsToString());
                System.out.println("You can continue shopping or exit the store by choosing option 4.");
            input = 0;
            break; 
          case 4: 
            System.out.println("See you next time!!"); 
            loop = false;
            break; 
        }
      }
      
       
 }

}
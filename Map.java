//package classes;
import java.awt.*; 
import java.lang.*;
import java.util.Scanner; 
import java.io.*;

public class Map //throws IOException
{
  private static Map instance = null;
  /** 2D array to store the coordinates of the map  */
  private char[][] map;

  /** 2D array to store which tiles the hero has explored   */
  private boolean[][] revealed;

  /** initializes the map, which holds both 2D arrays  
   *  sets all tiles as unrevealed
   */
  private Map()
  {
    map = new char[5][5]; 
    revealed = new boolean[5][5]; 
    for(int i = 0; i < 5; i++)
    {
      for(int j = 0; j < 5; j++)
      {
        revealed[i][j] = false;
      }
    }
  }

  /** 
  *  Creates an instance of the map if it does not previously exist
  *  @return instance of Map object
  */
  public static Map getInstance()
  {
    if(instance == null)
    {
      instance = new Map();
    }
    return instance;
  }

  /** Loads the maps from the corresponding text files
   *  @param mapNum this number decides which map will load
   */
  public void loadMap(int mapNum)
  {
    Scanner mp;
    String mapName = "Map" + mapNum + ".txt"; 
      try
      {
        mp = new Scanner(new File(mapName));
        String line = ""; 
        int i = 0;
        while(mp.hasNext())
        {
          line = mp.nextLine(); 
          String str[] = line.split(" ");
          line = "";
          for(String a : str)
          {
            line += a;
          }
          map[i] = line.toCharArray();
          i++; 
        } 
        for(i = 0; i < 5; i++)
        {
          for(int j = 0; j < 5; j++)
          {
            revealed[i][j] = false;
          }
        }
      }
      catch (FileNotFoundException e) 
    {
      System.out.println("option " + mapNum + " not found!! help!!");
      e.printStackTrace();
    }
  }


/**
 *  Returns the char at the location of the Point p passed in
 *  @param p = location of hero
 *  @return the char where hero is located
 */
  public char getCharAtLoc(Point p)
  {
    int x = (int)p.getX();
    int y = (int)p.getY();
    return map[x][y];
  }

  /** 
  *  Prints map to console
  *  @param p = the Current point that will be denoted by a '*'
  */
  public void displayMap(Point p)
  {
    for(int i = 0; i< 5; i++)
    {
      for(int j = 0; j < 5; j++)
      {
        if((int)p.getX() == i && (int)p.getY() == j)
        {
          System.out.print("* ");
        }
        else
        {
          if(revealed[i][j] == false)
          {
            System.out.print("x ");
          }
          else 
          {
            System.out.print(map[i][j] + " "); 
          }
        }
      }
      System.out.println(); 
    }
  }
  
  /**
  * Locates the starting position of the map that is identiied by the char 's'
  * @return the Point at which the Map has hte char 's' which is the starting point of the map
  */
  public Point findStart()
  {
    for(int i = 0; i< 5; i++)
    {
      for(int j = 0; j< 5; j++)
      {
        if(map[i][j] == 's')
        {
          Point p = new Point(i,j);
          return p;
        }
      }
    }
    Point s = new Point();
    return s;
  }

  /**
  *  Allows hte location of this point to be revealed and show the char instead of an 'x' 
  *  @param p - the Point that will have its location be revealed 
  */
  public void reveal(Point p)
  {
    revealed[(int)p.getX()][(int)p.getY()] = true;
  }

  /**
  *  Changes the char in the map to 'n' 
  *  @param p - the Point that will have its char be changed to 'n'
  */
  public void removeCharAtLoc(Point p)
  {
    map[(int)p.getX()][(int)p.getY()] = 'n'; 
  }
}
import java.util.Scanner;

public class Main {
  
  private static Boolean check(char[][] val){
    //Horizontal
    Boolean end = false;
    int x=5,y,total = 0;
    while(x != -1 && end == false) {
      y = 0;
      while (y<6 && end == false) {
        if(val[x][y] == val[x][y+1] && val[x][y] != 0) {
          total += 1;
        }
        else
          total = 0;
        
        if(total == 3)
          end = true;
          
        y++;
      }
      x--;
    }
    
    return (end == false) ? checkV(val) : end;
  }
  
  private static Boolean checkV(char[][] val) {
    //Vertical
    Boolean end = false;
    int x,y=6,total = 0;
    while(y != -1 && end == false) {
      x = 0;
      while (x<5 && end == false) {
        if(val[x][y] == val[x+1][y] && val[x][y] != 0) {
          total += 1;
        }
        else
          total = 0;
        
        if(total == 3)
          end = true;
          
        x++;
      }
      y--;
    }
    return (end == false) ? checkD1(val) : end;
  }
  
  private static Boolean checkD1(char[][] val) {
    Boolean end = false;
    int total = 0,y=3,x=0,x1,y1;
    //Diagonal /
    for(int cnt=3;cnt<=8 && end == false;cnt++) {
      for(x1=x,y1=y;end == false && x1 < 5 && y1 > 0;x1++,y1--) {
        if(val[x1][y1] != 0 && (val[x1][y1] == val[x1+1][y1-1])) {
          total += 1;
        }
        else
          total = 0;
          
        if(total == 3)
          end = true;
      }
      total = 0;
      y = (y>=6) ? 6 : y+1;
      x= (y==6) ? x+1 : 0;
      
    }
    
    return (end == false) ? checkD2(val) : end;
  }
  
  private static Boolean checkD2(char[][] val) {
    Boolean end = false;
    int total = 0,y=3,x=5,x1,y1;
    //Diagonal \
    for(int cnt=3;cnt<=8 && end == false;cnt++) {
        for(x1=x,y1=y;end == false && x1 > 0 && y1 > 0;x1--,y1--) {
          if(val[x1][y1] != 0 && (val[x1][y1] == val[x1-1][y1-1])) {
            total += 1;
          }
          else
            total = 0;
            
          if(total == 3)
            end = true;
          
      }
      total = 0;
      y = (y>=6) ? 6 : y+1;
      x = (y==6) ? x-1 : 5; 
    }
     return end;
  }
  
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    char[][] connectFour = new char[6][7];
    int player = 1,input=0,cnt;
    char letter;
    String var="",result;
    Boolean statement = false,res = false;
  
    while(res == false && player <= 42)
    {
      cnt=5;
      result = "";
      statement = false;
      if(player % 2 == 0) {
        letter = 'G';
        var = "2 [Green]";
      }
      else {
        letter = 'R';
        var = "1 [Red]";
      }
      
      do {
        System.out.print("Player " + var + " - choose column (1-7):");
        while (in.hasNextInt() == false) {
          System.out.println("Choose number from 1 to 7 only.");
          in.next();
        }
        input = in.nextInt();
      } while (input <= 0 || input >= 8); 
      
      player += 1;
      
      while(statement == false && cnt >= 0) {
        if(connectFour[cnt][input-1] == 0) {
          connectFour[cnt][input-1] = letter;
          statement = true;
        }
        cnt--;
      }
      
      //Display
      for(int x=0;x<6;x++)
      {
        for (int y=0;y<7;y++) {
          if (connectFour[x][y] == 0)
            result += "| ";
          else
            result += "|" + connectFour[x][y];
        }
          result += "|\n";
      }
      
      System.out.print(result);
      
      //Checking for winner
      res = check(connectFour);
      
    }
    if (res == true)
      System.out.print("Player " + var + " has won.");
    else
      System.out.print("Draw.");
  }

  
}

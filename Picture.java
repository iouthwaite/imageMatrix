import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Image.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.JComponent.*;

public class Picture {
  protected BufferedImage img;
  protected int height;
  protected int width;
  public Picture(String filename)
  {
    try{
      img = ImageIO.read(new File(filename));
      width = img.getWidth();
      height = img.getHeight();
    }
    
    catch (IOException e){
      img = null;
    }
  }
  
  public Picture(int width, int height)
  {
    this.width=width;
    this.height=height;
    img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    
  }
  
  
  public Picture(Color[][] a)
  {
    this.width=a.length;
    this.height=a[0].length;
    img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    setArray(a);
  }
  
  
  public Picture (Picture other)
  {
    this.width=other.getWidth();
    this.height=other.getHeight();
    img = new BufferedImage(this.width,this.height,BufferedImage.TYPE_INT_RGB);
    Color[][] a = other.getArray();
    this.setArray(a);
    
  }
  
  
  public int getHeight()
  {
    return height;
  }
  
  public int getWidth()
  {
    return width;
  }
  
  public Color[][] getArray()
  {
    Color[][] a = new Color[width][height];
    int x,y;
    
    for (x=0;x<width;x++)
      for (y=0;y<height;y++)
    {
      int p = img.getRGB(x,y);
      int  r   = (p & 0x00ff0000) >> 16;
      int  g = (p & 0x0000ff00) >> 8;
      int  b  = p & 0x000000ff;
      a[x][y]=new Color(r,g,b);
    }
    return a;
  }
  
  public void setArray(Color[][] a)
  {
    Color c;
    int x,y;
    for (x=0;x<width;x++)
      for (y=0;y<height;y++)
    {
      int cval = a[x][y].getRGB();
      img.setRGB(x,y,cval);
    }
  }
  public void saveImage(String filename)
  {
    try{
      ImageIO.write(img,"png",new File(filename));
    }
    catch (IOException e){
    }
    
  }
  
  
  public void display()
  {
    ImageIcon icon = new ImageIcon();
    icon.setImage(img);
    JOptionPane.showMessageDialog(null,icon);
  }
  
  public void decolor()
  {
    Color[][] a = this.getArray();
    for (int x = 0; x<a.length;x++){
      for (int y = 0; y<a[0].length ;y++) {
        Color tmp = a[x][y];
        int i = (tmp.getRed()+tmp.getGreen()+tmp.getBlue())/3;
        a[x][y] = new Color(i,i,i);
      }
    }
    this.setArray(a);
  }
  
  public void flip()
  {
    Color[][] a = this.getArray();
    for (int y = 0; y<a[0].length/2 ;y++) {
      for (int x = 0; x<a.length;x++){
        Color tmp = a[x][y];
        a[x][y]=a[x][a[0].length-1-y];
        a[x][a[0].length-1-y]=tmp;
      }
    }
    this.setArray(a);
  }
  
  public Picture crop(int x, int y, int w, int h)
  {
    Color[][] b = new Color[w][h];
    Color[][] a  = this.getArray();
    
    // Fill the array with colors from the orignal image;
    for (int i = 0; i < w; i++) {
      for (int j = 0; j < h; j++) {
        //b[i][j] = this.getArray()[x+i][y+j];
        b[i][j] = a[x+i][y+j];
      }
    }
    
    return new Picture(b);
    
  }
  
  
}

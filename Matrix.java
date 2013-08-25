import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Image.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.JComponent.*;

public class Matrix {
  int[][][] red,green,blue;
  int h,w,d;
  
  public Matrix() {
  }
  
  public Matrix(Picture p){
    w = p.height;
    h = p.width;
    d = 256;
    red = new int[h][w][d];
    green = new int[h][w][d];
    blue = new int[h][w][d];
    
    //System.out.println(red.length + " " + red[0].length + " " + red[0][0].length);
    for (int i=0; i<red.length; i++) {
      for (int j=0; j<red[0].length; j++) {
        for (int k=0; k<red[0][0].length; k++) {
          red[i][j][k] = 0;
        }
      }
    }
    
    //System.out.println(green.length + " " + green[0].length + " " + green[0][0].length);
    for (int i=0; i<green.length; i++) {
      for (int j=0; j<green[0].length; j++) {
        for (int k=0; k<green[0][0].length; k++) {
          green[i][j][k] = 0;
        }
      }
    }
    
    //System.out.println(blue.length + " " + blue[0].length + " " + blue[0][0].length);
    for (int i=0; i<blue.length; i++) {
      for (int j=0; j<blue[0].length; j++) {
        for (int k=0; k<blue[0][0].length; k++) {
          blue[i][j][k] = 0;
        }
      }
    }
    
    addPicture(p);
    
  }
  
  public void addPicture(Picture p){
    Color[][] c = p.getArray();
    for (int i=0; i<c.length; i++) {
      for (int j=0; j<c[0].length; j++) {
        
        int r = c[i][j].getRed();
        int b = c[i][j].getBlue();
        int g = c[i][j].getGreen();
        
        red[i][j][r] = 1;
        blue[i][j][b] = 1;
        green[i][j][g] = 1;
      }
    }
  }
  
  //returns the normal image from the matrix
  public Picture getImage() {
    Color[][] col = new Color[h][w];
    Color c;
    int r=0;
    int g=0;
    int b=0;
    for (int i=0; i<green.length; i++) {
      for (int j=0; j<green[0].length; j++) {
        for (int k=0; k<green[0][0].length; k++) {
          if (green[i][j][k]==1)
          {
            g = k;
            for (int m=0; m<red[0][0].length; m++) {
              if (red[i][j][m]==1) {
                r = m;
              }
            }
            for (int n=0; n<blue[0][0].length; n++) {
              if (blue[i][j][n]==1) {
                b = n;
              }
            }
            c = new Color(r,g,b);
            col[i][j] = c;
          }
        }
      }
    }
    Picture pic = new Picture(col);
    return pic;
  }
  
  //returns image flipped about x and y axis
  public Picture getOppositeCornerImage() {
    Color[][] col = new Color[h][w];
    Color c;
    int r=0;
    int g=0;
    int b=0;
    for (int i=green.length-1; i>=0; i--) {
      for (int j=green[0].length-1;j>=0; j--) {
        for (int k=green[0][0].length-1; k>=0; k--) {
          if (green[i][j][k]==1)
          {
            g = k;
            for (int m=red[0][0].length-1; m>=0; m--) {
              if (red[i][j][m]==1) {
                r = m;
              }
            }
            for (int n=blue[0][0].length-1; n>=0; n--) {
              if (blue[i][j][n]==1) {
                b = n;
              }
            }
            c = new Color(r,g,b);
            col[h-i-1][w-j-1] = c;
          }
        }
      }
    }
    Picture pic = new Picture(col);
    return pic;
  }
    
  //returns image flipped about x and y axis and inverse colors
  public Picture getOppositeInversedImage() {
    Color[][] col = new Color[h][w];
    Color c;
    int r=0;
    int g=0;
    int b=0;
    for (int i=green.length-1; i>=0; i--) {
      for (int j=green[0].length-1;j>=0; j--) {
        for (int k=green[0][0].length-1; k>=0; k--) {
          if (green[i][j][k]==1)
          {
            g = k;
            for (int m=red[0][0].length-1; m>=0; m--) {
              if (red[i][j][m]==1) {
                r = m;
              }
            }
            for (int n=blue[0][0].length-1; n>=0; n--) {
              if (blue[i][j][n]==1) {
                b = n;
              }
            }
            c = new Color(255-r,255-g,255-b);
            col[h-i-1][w-j-1] = c;
          }
        }
      }
    }
    Picture pic = new Picture(col);
    return pic;
  }
    
  //returns "red" color matrix image
  public Picture getRedImage() {
    Color[][] col = new Color[h][w];
    Color c;
    int r=0;
    for (int i=0; i<red.length; i++) {
      for (int j=0; j<red[0].length; j++) {
        for (int k=0; k<red[0][0].length; k++) {
          if (red[i][j][k]==1)
          {
            r = k;
            c = new Color(r,0,0);
            col[i][j] = c;
          }
        }
      }
    }
    Picture pic = new Picture(col);
    return pic;
  }
  
  //returns "green" color matrix image
  public Picture getGreenImage() {
    Color[][] col = new Color[h][w];
    Color c;
    int g=0;
    for (int i=0; i<green.length; i++) {
      for (int j=0; j<green[0].length; j++) {
        for (int k=0; k<green[0][0].length; k++) {
          if (green[i][j][k]==1)
          {
            g = k;
            c = new Color(0,g,0);
            col[i][j] = c;
          }
        }
      }
    }
    Picture pic = new Picture(col);
    return pic;
  }
  
  //returns "blue" color matrix image
  public Picture getBlueImage() {
    Color[][] col = new Color[h][w];
    Color c;
    int b=0;
    for (int i=0; i<blue.length; i++) {
      for (int j=0; j<blue[0].length; j++) {
        for (int k=0; k<blue[0][0].length; k++) {
          if (blue[i][j][k]==1)
          {
            b = k;
            c = new Color(0,0,b);
            col[i][j] = c;
          }
        }
      }
    }
    Picture pic = new Picture(col);
    return pic;
  }
  
  //returns image with red taken from xcor, green taken from the ycor, and blue taken from the zcor of the red matrix.
  public Picture getPictureFromRed() {
    Color[][] col = new Color[h][w];
    Color c;
    for (int i=0; i<red.length; i++) {
      for (int j=0; j<red[0].length; j++) {
        for (int k=0; k<red[0][0].length; k++) {
          if (red[i][j][k]==1)
          {
            c = new Color(i%256,j%256,k%256);
            //c = new Color(0,0,0);
            col[i][j] = c;
          }
        }
      }
    }
    Picture pic = new Picture(col);
    return pic;
  }
  
  //returns image with red taken from xcor, green taken from the ycor, and blue taken from the zcor of the green matrix.
  public Picture getPictureFromGreen() {
    Color[][] col = new Color[h][w];
    Color c;
    for (int i=0; i<green.length; i++) {
      for (int j=0; j<green[0].length; j++) {
        for (int k=0; k<green[0][0].length; k++) {
          if (green[i][j][k]==1)
          {
            c = new Color(i%256,j%256,k%256);
            //c = new Color(0,0,0);
            col[i][j] = c;
          }
        }
      }
    }
    Picture pic = new Picture(col);
    return pic;
  }
  
  
  //returns image with red taken from xcor, green taken from the ycor, and blue taken from the zcor of the blue matrix.
  public Picture getPictureFromBlue() {
    Color[][] col = new Color[h][w];
    Color c;
    for (int i=0; i<blue.length; i++) {
      for (int j=0; j<blue[0].length; j++) {
        for (int k=0; k<blue[0][0].length; k++) {
          if (blue[i][j][k]==1)
          {
            c = new Color(i%256,j%256,k%256);
            //c = new Color(0,0,0);
            col[i][j] = c;
          }
        }
      }
    }
    Picture pic = new Picture(col);
    return pic;
  }
  
  //this method scrambles the red, green, and blue values. 
  //The first pixel in the new image contains the number of the place of each original pixels 
  //in their respective red, green, and blue color matricies.
  
  public Picture getScrambledImage() {
    return getScrambledImage(0);
  }
  
  public Picture getScrambledImage2() {
    return getScrambledImage(1);
  }
  
  public Picture getScrambledImage(int selection) {
    Color[][] col = new Color[h][w];
    Random r = new Random();
    int redstart,greenstart,bluestart;
    
    if (w*h < 256) {
      redstart = r.nextInt(w*h);
      greenstart = r.nextInt(w*h);
      bluestart = r.nextInt(w*h);
    }
    else {
      redstart = r.nextInt(256);
      greenstart = r.nextInt(256);
      bluestart = r.nextInt(256);
    }
    
    int redrow = (int)redstart/w;
    int greenrow = (int)greenstart/w;
    int bluerow = (int)bluestart/w;
    int redcol=0;
    int greencol=0;
    int bluecol=0;
    
    int redEnd=0;
    int greenEnd=0;
    int blueEnd=0;
    
    for (int i=0; (i+redrow*w)<=redstart; i++) {
      redcol = i;
    }
    for (int i=0; (i+greenrow*w)<=greenstart; i++) {
      greencol = i;
    }
    for (int i=0; (i+bluerow*w)<=bluestart; i++) {
      bluecol = i;
    }
    
    System.out.println("Red: " + redrow + " " + redcol);
    System.out.println("Green: " + greenrow + " " + greencol);
    System.out.println("Blue: " + bluerow + " " + bluecol);
    
    Color[] values = new Color[w*h];
    for (int i=0; i<values.length; i++) {
      values[i] = new Color(0,0,0);
    }
    int counter = 0;
    
    for (int i=redrow; i<=redrow; i++) {
      for (int j=redcol; j<red[0].length; j++) {
        for (int k=0; k<red[0][0].length; k++) {
          if (red[i][j][k]==1)
          {
            Color c = values[counter];
            values[counter] = new Color(k,0,0);
            counter++;
          }
        }
      }
    }
          
    for (int i=redrow+1; i<red.length; i++) {
      for (int j=0; j<red[0].length; j++) {
        for (int k=0; k<red[0][0].length; k++) {
          if (red[i][j][k]==1)
          {
            Color c = values[counter];
            values[counter] = new Color(k,0,0);
            counter++;
          }
        }
      }
    }
    
    redEnd=counter;
    counter = 0;
    
    for (int i=greenrow; i<=greenrow; i++) {
      for (int j=greencol; j<green[0].length; j++) {
        for (int k=0; k<green[0][0].length; k++) {
          if (green[i][j][k]==1)
          {
            Color c = values[counter];
            values[counter] = new Color(c.getRed(),k,0);
            counter++;
          }
        }
      }
    }
          
    for (int i=greenrow+1; i<green.length; i++) {
      for (int j=0; j<green[0].length; j++) {
        for (int k=0; k<green[0][0].length; k++) {
          if (green[i][j][k]==1)
          {
            Color c = values[counter];
            values[counter] = new Color(c.getRed(),k,0);
            counter++;
          }
        }
      }
    }
    
    greenEnd=counter;
    counter = 0;
    
    for (int i=bluerow; i<=bluerow; i++) {
      for (int j=bluecol; j<blue[0].length; j++) {
        for (int k=0; k<blue[0][0].length; k++) {
          if (blue[i][j][k]==1)
          {
            Color c = values[counter];
            values[counter] = new Color(c.getRed(),c.getGreen(),k);
            counter++;
          }
        }
      }
    }
          
    for (int i=bluerow+1; i<blue.length; i++) {
      for (int j=0; j<blue[0].length; j++) {
        for (int k=0; k<blue[0][0].length; k++) {
          if (blue[i][j][k]==1)
          {
            Color c = values[counter];
            values[counter] = new Color(c.getRed(),c.getGreen(),k);
            counter++;
          }
        }
      }
    }
    
    blueEnd=counter;
    counter=redEnd;
    
    for (int i=0; i<=redrow; i++) {
      for (int j=0; ((j+i*w<redcol+redrow*w)&&(j<redcol)); j++) {
        for (int k=0; k<red[0][0].length; k++) {
          if (red[i][j][k]==1)
          {
            Color c = values[counter];
            values[counter] = new Color(k,0,0);
            counter++;
          }
        }
      }
    }
    
    counter = greenEnd;
    
    for (int i=0; i<=greenrow; i++) {
      for (int j=0; ((j+i*w<greencol+greenrow*w)&&(j<greencol)); j++) {
        for (int k=0; k<green[0][0].length; k++) {
          if (green[i][j][k]==1)
          {
            Color c = values[counter];
            values[counter] = new Color(c.getRed(),k,0);
            counter++;
          }
        }
      }
    }
    
    counter = blueEnd;
    
    for (int i=0; i<=bluerow; i++) {
      for (int j=0; ((j+i*w<bluecol+bluerow*w)&&(j<bluecol)); j++) {
        for (int k=0; k<blue[0][0].length; k++) {
          if (blue[i][j][k]==1)
          {
            Color c = values[counter];
            values[counter] = new Color(c.getRed(),c.getGreen(),k);
            counter++;
          }
        }
      }
    }
    
    counter = 0;
    
    if (selection==0) {
      for (int i=0; i<col.length; i++) {
        for (int j=0; j<col[0].length; j++) {
          col[i][j] = values[counter];
          counter++;
        }
      }
    }
    
    counter=0;
    
    if (selection==1) {
      System.out.println("here");
      int i=0;
      int j=0;
      while (counter<(col.length*col[0].length)) {
        //System.out.println(counter + " " + (col.length*col[0].length));
        col[i][j] = values[counter];
        counter++;
        col[col.length-i-1][col[0].length-j-1]=values[counter];
        counter++;
        i++;
        j++;
        if (i==col.length) {
          i=0;
        }
        if (j==col[0].length) {
          j=0;
        }
      }
    }
    
    counter=0;
    
    if (selection==2) {
      int i=0;
      int j=0;
      while (counter<(col.length*col[0].length)) {
        col[i][j] = values[counter];
        //System.out.println("Row:" + i + " Col:" + j + " Color" + col[i][j]);
        counter++;
        col[col.length-i-1][col[0].length-j-1]=values[counter];
        counter++;
        i++;
        j++;
        if (i==col.length) {
          i=0;
        }
        if (j==col[0].length) {
          j=0;
        }
        if (counter>(col.length*col[0].length)-3)
        {
          for (int i2=0; i2<col.length; i2++) {
            for (int j2=0; j2<col[0].length; j2++) {
              if (col[i2][j2] == null) {
                System.out.println(i2 + " " +j2+ " ");
              }
            }
          }
        }
      }
      Color temp = new Color(redstart,greenstart,bluestart);
      col[0][0] = temp;
    }
    
    
    Picture pic = new Picture(col);
    return pic;
  }
  
  
  
  
 
  
  
}
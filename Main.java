import java.io.*;
import java.util.*;
import java.awt.*;
public class Main
{
    public static void main(String[] args)
    {
//<<<<<<< .mine
//<<<<<<< .mine
 Picture p = new Picture("pipe.jpg");
 // We also have constructors that
 // create an empty wxh image
 // and one that makes an image from a color array

 // pull the image into a color array
 Color[][] a = p.getArray();

 // Do something with the array

 // set the image into an array
 p.setArray(a);

 // Display the picture
 p.display();
//=======
 //Picture p = new Picture("pipe.jpg");
 Picture p2 = new Picture(p);
 p2.decolor();
//=======
 p = new Picture("Larry_the_cat.jpg");
//>>>>>>> .r4886
 p.display();
//<<<<<<< .mine
//>>>>>>> .r4481
//<<<<<<< .mine

 // Save the image
 // p.saveImage("newfilename.png");


//=======
//=======
 p2 = p.crop(10,10,10,10);
//>>>>>>> .r4886
 p2.display();
//>>>>>>> .r4481
    }
}

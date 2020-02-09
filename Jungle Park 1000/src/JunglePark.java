//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: JunglePark
// Files: JunglePark.java
// Course: CS300 Summer 2019
//
// Author: Kunlun Wang
// Email: kwang358@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Yuzheng Zhang
// Partner Email: yzhang975@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Random;

/**
 * JunglePark class creates an application that allows the user to add up to 8 tigers to the jungle
 * park area, and move them arbitrary within the display window using the mouse and remove any of
 * them under some easy operations.
 *
 * @author Yuzheng Zhang, Kunlun Wang
 */
public class JunglePark {
  private static PApplet processing; // PApplet object that represents the graphic
  // interface of the JunglePark application
  private static PImage backgroundImage; // PImage object that represents the
  // background image
  private static Tiger[] tigers; // array storing the current tigers present
  // in the Jungle Park
  private static Random randGen; // Generator of random numbers

  public static void main(String[] args) {
    // Auto-generated method stub
    Utility.startApplication();
  }

  /**
   * Defines the initial environment properties of the application
   * 
   * @param processingObj represents a reference to the graphical interface of the application
   */
  public static void setup(PApplet processingObj) {
    processing = processingObj; // initialize the processing field to the one passed into
    // the input argument parameter
    // Set the color used for the background of the Processing window
    processing.background(245, 255, 250); // Mint cream color
    // initialize and load the image of the background
    backgroundImage = processing.loadImage("images/background.png");
    // Draw the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    //// width [resp. height]: System variable of the processing library that stores the width
    //// [resp. height] of the display window.
    tigers = new Tiger[8];
    // tigers[0] = new Tiger(processing);
    // tigers[0].draw(); // where i is the index of the created tiger in the tigers array.
    // System.out.print(processing.width);
    randGen = new Random(); // create a Random object and store its reference in randGen
  }

  /**
   * Update method continuously draws the application display window and updates its content with
   * respect to any change or any event that affects its appearance.
   */
  public static void update() {
    processing.background(245, 255, 250);
    // clears the window to mint cream and then draws the background image to prevent it from
    // looking
    // like there are multiple tigers
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    // width [resp. height]: System variable of the processing library that stores the width
    // [resp. height] of the display window.
    for (int i = 0; i < tigers.length; i++) {
      if (tigers[i] != null) {
        tigers[i].draw();
      }
    }
  }

  /**
   * isMouseOver method should return true if the mouse is over the image of the Tiger object passed
   * to it as input parameter, and false otherwise. To implement this method , use width and height
   * fields defined within the image of the Tiger object to determine whether the mouse is over it.
   * 
   * @param tiger the element of the array tigers
   * @return isOver the boolean variable to determine whether the mouse is over the picture
   */

  public static boolean isMouseOver(Tiger tiger) {
    boolean isOver = false;
    PImage tigerIcon = tiger.getImage(); // pass the tiger image to tigerIcon
    // store the values of bounds of the tiger image
    float tigerWidthToTheRight = tigerIcon.width / 2 + tiger.getPositionX();
    float tigerWidthToTheLeft = -tigerIcon.width / 2 + tiger.getPositionX();
    float tigerHeightToTheTop = tigerIcon.height / 2 + tiger.getPositionY();
    float tigerHeightToTheBottom = -tigerIcon.height / 2 + tiger.getPositionY();
    // return true if the mouse is inside the bounds of the image and otherwise
    // return false
    if (tiger != null && (float) processing.mouseX <= tigerWidthToTheRight
        && (float) processing.mouseX >= tigerWidthToTheLeft
        && (float) processing.mouseY <= tigerHeightToTheTop
        && (float) processing.mouseY >= tigerHeightToTheBottom) {
      isOver = true;
    }
    return isOver;
  }

  /**
   * mouseDown method should check if the mouse is over one of the tiger objects stored in the
   * tigers array and sets the isDragging field defined this tiger object if any to true.
   */
  public static void mouseDown() {
    for (int i = 0; i < tigers.length; i++) {
      // if the tiger object of index i exists and the mouse is over that tiger picture,
      // set isDragging field to true and break the method
      if (tigers[i] != null && isMouseOver(tigers[i]) == true) {
        tigers[i].setDragging(true);
        break;
      }
    }
  }

  /**
   * mouseUp method should set isDragging field of every tiger object stored in the tigers array to
   * false. No tiger is being dragged when the mouse is released.
   */
  public static void mouseUp() {
    for (int i = 0; i < tigers.length; i++) {
      // if the tiger object of index i exists and the tiger picture was dragged before
      // set isDragging field to false and break the method
      if (tigers[i] != null && tigers[i].isDragging() == true) {
        tigers[i].setDragging(false);
        break;
      }
    }
  }

  /**
   * the keyPressed method search through the tigers array for a null reference, if found replace
   * the first (lowest index) null reference with a new Tiger object located at a random position of
   * the display window when the user press the 't' or 'T' key, and if the 'R' or 'r' key is pressed
   * while the mouse if over a tiger, that tiger will be removed from the Jungle Park (meaning that
   * its reference will be set to null) until another is created in it's place (by pressing the
   * t-key).
   * 
   */
  public static void keyPressed() {
    // when the user press 't' or 'T',
    // replace the first (lowest index) null reference with a new Tiger
    // object located at a random position of the display window
    if (processing.key == 't' || processing.key == 'T') {
      for (int i = 0; i < tigers.length; i++) {
        if (tigers[i] == null) {
          tigers[i] = new Tiger(processing, (float) randGen.nextInt(processing.width),
              (float) randGen.nextInt(processing.height));
          break;
        }
      }
    }
    // if the 'R' or 'r' key is pressed while the mouse
    // if over a tiger, that tiger will be removed from the Jungle Park
    if (processing.key == 'r' || processing.key == 'R') {
      for (int i = 0; i < tigers.length; i++) {
        if (tigers[i] != null && isMouseOver(tigers[i]) == true) {
          tigers[i] = null;
          break;
        }
      }
    }
  }
}
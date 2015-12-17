//Drake Puletz
float surface;

void setup() {   //setup screen
    size(750,750);
    surface = height/3;
    rectMode(CORNERS);
    reset();
    }
void draw() {
    scene();
    display();
    movement();
    }
  
void reset() {      //reset code
    float groupSpace = width/(groupMany+1);
    horizon = height/3;
    points = 0;
    for (int i=0; i<boatsMany; i++ ) {
      boats[i] = new Boat(horizon);
    }
    for (int i=0; i<groupMany; i++ ) {
      group[i] = new Squid(horizon, groupSpace, i);
    }
    for (int i=0; i<bunchMany; i++ ) {
      bunch[i] = new Lobster(horizon, groupSpace, i);
      }
    frameCount = 0;
    }

class Boat {
    float posX, posY;
    float DX;
    float r = random(255);
    float g = random(255);
    float b = random(255);
    int fins=0;

  //Boat data
  Boat(float horizon) {
    posX = random(0, width);
    posY = horizon;
    DX = random(-4, 4);
    int(r);
    int(g);
    int(b);
  }

  //display/draw Boat
void disp(int i) {
    fill(r, g, b);
    rect(posX-20, posY, posX+20, posY-20);
    triangle(posX-10, posY-20, posX, posY-30, posX+10, posY-20);
    fill(0);
  }


  //move Boat
void move() {
    posX += DX;
    if (posX < 0) {
    DX = random(1.5, 4);
    score(fins);
   }
     if (posX > width) DX = random(-1.5, -4);
      }
  }

class Squid {
    float legs = random(1, 30);
    float posX, posY;
    float DY;
    float r = random(255);
    float g = random(255);
    float b = random(255);
    int i;
    float horizon;

  Squid(float horizon, float spacing, int i) {  //squid data
    this.horizon = horizon;
    this.i = i;
    posX = (i+1) * spacing;
    posY = height;
    DY = random(-4, -2);      

    int(legs);
    int(r);
    int(g);
    int(b);
  }

void move() {    //move squid
    posY += DY;
    if (posY < horizon+20)DY = random(2, 4);
    if (posY > height)DY = random(-4, -2);
  }

void disp() {      //display squid
    fill(r, g, b);
    arc(posX, posY, 40, 40, PI, TWO_PI, OPEN);
    for (int l=1; l<legs; l++) {
      line( posX, posY, posX+10, posY);
    }
    fill(0);
    text(i+1, posX, posY);
    }
  }
class Lobster {
    float posX, posY;
    float DX;
    float DY;
    float r = random(255);
    float g = random(255);
    float b = random(255);
    float horizon;
    float spacing;
    float i;

  Lobster(float horizon, float spacing, int i) {   //lobster data
    this.horizon = horizon;
    this.i = i;
    posX = random(10,width);
    posY = random( horizon,height);
    DY = random(-4, -2);
  }
void move() {   //move lobster (didnt work)
    posX = posX + DX;
    posY = posY + DY;
    if (posX < width+20)  DY = random( 2,  4);
    if (posX > width-20)  DY = random(-4, -2);
    if (posY < horizon-10)DX = random( 2,  4);
    if (posY > height-20) DX = random(-2, -4);
  }
void disp() {     //display lobster
    fill(r,g,b);
    rect(posX-20, posY, posX+20, posY-20);
    rect(posX-40,posY, posX+20, posY);
    rect(posX-40,posY-20, posX+20, posY-20);  
    }
  }
  int bunchMany = 4;
  Lobster bunch[] = new Lobster[bunchMany];
  
  int boatsMany = 3;
  Boat boats[] = new Boat[boatsMany];
  
  int groupMany = 5;
  Squid group[] = new Squid[groupMany];
  
  //coord for horizon for Boats
  float horizon;
  int points;

void scene() {      //create the scene
    noStroke();
    background(0, 137, 240);//sky
    fill(0);
    text(points, width-50, 20);
    fill(0, 70, 188);
    rect(0, horizon, width, height);//sea
    }

//containing all display methods
void display() {     // draw triangle equal length apart
    fill(255,0,0);
    triangle( width-20, (height)- 40, width-5, (height)-20, width-5, (height)-60);
    triangle( width-20, (height)-100, width-5, (height)-80, width-5, (height)-120);
    triangle( width-20, (height)-160, width-5, (height)-140, width-5, (height)-180);
    triangle( width-20, (height)-220, width-5, (height)-200, width-5, (height)-240);
    triangle( width-20, (height)-280, width-5, (height)-260, width-5, (height)-300);
    triangle( width-20, (height)-340, width-5, (height)-320, width-5, (height)-360);
  
    fill(0);
    text("Drake Puletz",20,height-20);

    stroke(1);
    for (int i=0; i<boatsMany; i++ ) {
      boats[i].disp(i+1);
    }
    for (int i=0; i<groupMany; i++) {
      group[i].disp();
    }
    for (int i=0; i<bunchMany; i++) {
      bunch[i].disp();
      }
    }

//contains movement values
void movement() {
    for (int i=0; i<boatsMany; i++ ) {
      boats[i].move();
    }
    for (int i=0; i<groupMany; i++) {
      group[i].move();
      }
    }

void score(int s) {
    points+=s;
    }

void mousePressed() {
    reset();
    }

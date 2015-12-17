//Drake Puletz
float surface;

void setup() {
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
void reset() {
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

  //starting Boat data
  Boat(float horizon) {
    posX = random(0, width);
    posY = horizon;
    DX = random(-4, 4);
    int(r);
    int(g);
    int(b);
  }

  //displaying Boat
  void disp(int i) {
    fill(r, g, b);
    rect(posX-20, posY, posX+20, posY-20);
    triangle(posX-10, posY-20, posX, posY-30, posX+10, posY-20);
    fill(0);
  }


  //moving Boat
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

  Squid(float horizon, float spacing, int i) {
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

  void move() {
    posY += DY;
    if (posY < horizon+20)DY = random(2, 4);
    if (posY > height)DY = random(-4, -2);
  }

  void disp() {
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

  Lobster(float horizon, float spacing, int i) {
    this.horizon = horizon;
    this.i = i;
    posX = random(10,width);
    posY = random( horizon,height);
    DY = random(-4, -2);
  }
  void move() {
    posX += DX;
    posY += DY;
    if (posX < width+20)DY = random(2, 4);
    if (posX > width-20)DY = random(-4, -2);
    if (posY < horizon-10)DX = random(2, 4);
    if (posY > height-20)DX = random(-2, -4);
  }
  void disp() {
    fill(r,g,b);
    rect(posX-20, posY, posX+20, posY-20);
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

void scene() {
  noStroke();
  background(0, 137, 240);//sky
  fill(0);
  text(points, width-50, 20);
  fill(0, 70, 188);
  rect(0, horizon, width, height);//sea
}

//containing all display methods
void display() {
  fill(255);
  triangle( width-20, (surface/2)-20, width-5, (surface/2)-10, width-5, (surface/2)-30);
  

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

//containing all movement values
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

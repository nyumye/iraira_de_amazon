

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;
/*import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;*/



class Rp14_04{
  static JFrame myframe = new JFrame("titledeeeeeeeeeeesu");
  static JPanel[] roadPanel = new JPanel[500];
  static JPanel dangerousPanel = new JPanel();
  static JPanel startPanel = new JPanel();
  static JPanel endPanel = new JPanel();
  static JPanel[] summonGorillaPanel = new JPanel[4];

  static JPanel[] lionPanel = new JPanel[4];
  static int[] lionX = new int[4], lionY = new int[4];
  static double lionAngle = 0;

  static JPanel[] gorillaPanel = new JPanel[4];
  static int[] gorillaX = new int[4], gorillaY = new int[4];
  static double gorillaAngle = 0;
  static String[] fileName = {"./image/animal_Orangutan.png",
                  "./image/animal_gorilla.png",
                  "./image/character_gorilla_hardboiled2.png",
                  "./image/snow_yukiotoko.png"};
  static int countGorilla = 0;
  static Point[] gorillaPoint = new Point[4];
  static Point goalPoint = new Point();
  static Point[] gorillaNowPoint = new Point[4];
  static int countGorillaWaitTime = 0;

  static int wait = 0;

  static boolean isPlaying = false;
  //static int countPlaying = 0;

  static JPanel displayPanel = new JPanel();
  static JLabel statusLabel = new JLabel();

  static int countBath = 0;
  static int countBathSum = 0;
  static JLabel countBathLabel = new JLabel();
  static int countHighBath = 0;

  static int countAmazonDeath = 0;
  static JLabel countAmazonDeathLabel = new JLabel();
  static int countLionDeath = 0;
  static JLabel countLionDeathLabel = new JLabel();
  static int countGorilladeath = 0;
  static JLabel countGorilladeathLabel = new JLabel();

  static JButton btn0 = new JButton("もう一回や！");
  static JButton btn1 = new JButton("最初からや！");

  static boolean isDead = false;

  static int judge = 0;
  static int reverseJudge = 0;

  static boolean cheat = false;


  //static ImageIcon icon = new ImageIcon(".image/mori.png");

  public static void main(String[] args) {
    myframe.setLayout(null);
    myframe.setSize(5000,1000);
    myframe.setVisible(true);
    myframe.setBackground(new Color(255,235,224));

    //lion gaoooooooooooooooooooooooooooooooooooooooooooooooooooooo
    //gorilla uhouhouhouhouho
    for(int i=0; i<4; i++){
      lionPanel[i] = new JPanel();
      myframe.add(lionPanel[i]);
      lionPanel[i].setBackground(new Color(0,0,0,0));
      ImageIcon lionIcon = new ImageIcon("./image/animal_lion2.png");
      JLabel lionLabel = new JLabel(lionIcon);
      lionPanel[i].add(lionLabel);
      lionPanel[i].addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
      		lionHit(e);
      	}
      } );

      gorillaPanel[i] = new JPanel();
      myframe.add(gorillaPanel[i]);
      gorillaPanel[i].setBackground(new Color(0,0,0,0));
      ImageIcon gorillaIcon = new ImageIcon(fileName[i]);
      JLabel gorillaLabel = new JLabel(gorillaIcon);
      gorillaPanel[i].add(gorillaLabel);
      gorillaPanel[i].addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
      		gorillaHit(e);
      	}
      } );
    }
    for(int i=0; i<4; i++){
      gorillaPoint[i] = new Point();
      gorillaNowPoint[i] = new Point();
    }
    //oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo

    myframe.add(startPanel);
    //startPanel.setBounds(0,500,50,50);
    startPanel.setBackground(Color.blue);
    startPanel.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
    		irairastart(e);
    	}
    } );

    myframe.add(endPanel);
    //startPanel.setBounds(0,500,50,50);
    endPanel.setBackground(Color.red);
    endPanel.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
    		irairaend(e);
    	}
    } );
    ImageIcon icon = new ImageIcon("./image/ofuro_hinoki2.png");
    JLabel label = new JLabel(icon);
    endPanel.add(label);

    for(int i=0; i<4; i++){
      summonGorillaPanel[i] = new JPanel();
      myframe.add(summonGorillaPanel[i]);
      summonGorillaPanel[i].setBackground(new Color(0,0,0,0));
      summonGorillaPanel[i].addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
      		summonGorilla(e);
      	}
      } );
    }

    roadPanelSet();

    myframe.add(dangerousPanel);
    dangerousPanel.setBounds(0,0,1150,1100);
    dangerousPanel.setBackground(new Color(2,99,13));
    dangerousPanel.addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
    		death(e);
    	}
    } );
    //--------------------------------------------------------------------------

    //右側menu---------
    myframe.add(displayPanel);
    displayPanel.setBounds(1150,0,750,1100);
    displayPanel.setBackground(Color.white);
    displayPanel.setLayout(new GridLayout(7,1));

    statusLabel.setFont(new Font("メイリオ", Font.BOLD, 30));
    displayPanel.add(statusLabel);

    countBathLabel.setFont(new Font("メイリオ", Font.BOLD, 30));
    displayPanel.add(countBathLabel);

    countAmazonDeathLabel.setFont(new Font("メイリオ", Font.BOLD, 30));
    displayPanel.add(countAmazonDeathLabel);

    countLionDeathLabel.setFont(new Font("メイリオ", Font.BOLD, 30));
    displayPanel.add(countLionDeathLabel);

    countGorilladeathLabel.setFont(new Font("メイリオ", Font.BOLD, 30));
    displayPanel.add(countGorilladeathLabel);

    displayPanel.add(btn0);
    btn0.setBounds(1100,100,100,50);
    btn0.setFont(new Font("メイリオ", Font.BOLD, 30));
    btn0.addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent e){
          cntn(e);
        }
      }
    );

    displayPanel.add(btn1);
    btn1.setBounds(1100,100,100,50);
    btn1.setFont(new Font("メイリオ", Font.BOLD, 30));
    btn1.addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent e){
          reStart(e);
        }
      }
    );
    //---------------------

    //icon.setSize(50,50);
    //森森森森森森森森森森------------------
    for(int i = 0; i<12*12; i++){
      ImageIcon moriicon = new ImageIcon("./image/mori_jungle3.png");
      JLabel morilabel = new JLabel(moriicon);
      morilabel.setHorizontalAlignment(JLabel.LEFT);
      dangerousPanel.add(morilabel);

    }
    //-------------------------------------

    //roadPanalSet();

    //myframe.addWindowListener(new MyWindowAdapter());
    //閉じちゃおうね
    myframe.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent we) {
        System.exit(0);
      }
    });

    //道作 etc.....
    allSet();

    while(true){
      if(isDead) continue;
      if(wait++>10000000){
        lionMove();
        gorillaMove();
        wait=0;
      }
    }

  }

  static void lionMove(){
    for(int i=0; i<judge/2; i++){
      lionPanel[i].setBounds(lionX[i]+(int)(Math.cos(Math.toRadians(lionAngle))*100),lionY[i]+(int)(Math.sin(Math.toRadians(lionAngle))*100),50,50);
    }
    lionAngle++;
    if(lionAngle>360) lionAngle=0;
  }

  static void gorillaMove(){
    if(countGorillaWaitTime++<reverseJudge/2+3){
      return;
    }else{countGorillaWaitTime=0;}

    for(int i = 0; i<countGorilla; i++){
      int x = (int)gorillaNowPoint[i].getX();
      int y = (int)gorillaNowPoint[i].getY();
      if(gorillaNowPoint[i].equals(goalPoint)){
        continue;
      }else if(y<goalPoint.getY()){
        if(x == goalPoint.getX()){
          gorillaNowPoint[i].move(x,y+1);
        }else if((int)(Math.random()*2)==0){
          gorillaNowPoint[i].move(x,y+1);
        }else{
          gorillaNowPoint[i].move(x+1,y);
        }
      }else if(y>(int)goalPoint.getY()){
        if(x == goalPoint.getX()){
          gorillaNowPoint[i].move(x,y-1);
        }else if((int)(Math.random()*2)==0){
          gorillaNowPoint[i].move(x,y-1);
        }else{
          gorillaNowPoint[i].move(x+1,y);
        }
      }else{
        gorillaNowPoint[i].move(x+1,y);
      }
      gorillaPanel[i].setBounds(x,y,50,50);
    }
      //if(gorillaX[i])
      //gorillaPanel[i].setBounds(gorillaX[i]);
  }

  static void roadSet(){
    int[] relayPoint = new int[5];
    int roadSize = 50-countBath++*2;

    if(roadSize<36) roadSize = 36;

    int nowX = 0;
    int nowY = 0;
    int countUsedPanel = 0;

    //make relayPoint
    for(int i = 0; i<5; i++){
      relayPoint[i] = (int)(Math.random()*(20-reverseJudge*3));
    }
    startPanel.setBounds(0+50,relayPoint[0]*roadSize+50,roadSize,roadSize);

    for(int i = 1; i<5; i++){
      nowY = relayPoint[i-1];
      gorillaPoint[i-1].setLocation(nowX*roadSize+50,relayPoint[i-1]*roadSize+50);
      gorillaNowPoint[i-1].setLocation(nowX*roadSize+50,relayPoint[i-1]*roadSize+50);
      roadPanel[countUsedPanel++].setBounds(++nowX*roadSize+50,relayPoint[i-1]*roadSize+50,roadSize,roadSize);
      roadPanel[countUsedPanel++].setBounds(++nowX*roadSize+50,relayPoint[i-1]*roadSize+50,roadSize,roadSize);
      do{
        if(nowY<relayPoint[i] && (int)(Math.random()*2)!=0){
          roadPanel[countUsedPanel++].setBounds(nowX*roadSize+50,++nowY*roadSize+50,roadSize,roadSize);
        }else if(nowY>relayPoint[i] && (int)(Math.random()*2)!=0){
          roadPanel[countUsedPanel++].setBounds(nowX*roadSize+50,--nowY*roadSize+50,roadSize,roadSize);
        }else{
          roadPanel[countUsedPanel++].setBounds(++nowX*roadSize+50,nowY*roadSize+50,roadSize,roadSize);
        }
      }while(nowX<i*5);

      while(nowY!=relayPoint[i]){
        if(relayPoint[i-1]<relayPoint[i]){
          roadPanel[countUsedPanel++].setBounds(nowX*roadSize+50,++nowY*roadSize+50,roadSize,roadSize);
        }else{
          roadPanel[countUsedPanel++].setBounds(nowX*roadSize+50,--nowY*roadSize+50,roadSize,roadSize);
        }
      }
      summonGorillaPanel[i-1].setBounds(nowX*roadSize+50,nowY*roadSize+50,roadSize,roadSize);

    }
    //java.util.Vector<Point> v;
    goalPoint.setLocation(nowX*roadSize+50,relayPoint[4]*roadSize+50);
    endPanel.setBounds((int)goalPoint.getX(),(int)goalPoint.getY(),roadSize,roadSize);
    for(int i=0; i<4; i++){
      //lionX[i] = (25*roadSize+50)/2+(int)(Math.random()*200)-100;
      //lionY[i] = (relayPoint[0]+relayPoint[4])/2*roadSize+50 + (int)(Math.random()*200-100);
      lionX[i] = (5*roadSize*(i+1));
      lionY[i] = (relayPoint[i+1]*roadSize+50);
    }
  }

  static public void summonGorilla(MouseEvent e){
    //System.out.println("yeeeh");
    if(!isPlaying){
      return;
    }
    if(countGorilla<judge){
      gorillaPanel[countGorilla].setBounds((int)gorillaPoint[countGorilla].getX(),(int)gorillaPoint[countGorilla].getY(),50,50);
      countGorilla++;
    }
  }

  static public void death(MouseEvent e){
    if(isPlaying && !isDead && !cheat){
      statusLabel.setText("アマゾンに食われた");
    }else{return;}
    isDead = true;
    isPlaying = false;
    countAmazonDeath++;
//    System.out.println((int)(Math.random()*3));
  }

  static public void alive(MouseEvent e){
    //if(isPlaying) System.out.println("you alive");
  }

  static public void lionHit(MouseEvent e){
    if(isPlaying && !isDead && !cheat){
      statusLabel.setText("ライオンに見つかってもうた///");
    }else{return;}
    isDead = true;
    isPlaying = false;
    countLionDeath++;
  }

  static public void gorillaHit(MouseEvent e){
    if(isPlaying && !isDead ){
      statusLabel.setText("ゴリラに会っちゃった");
    }else{return;}
    isDead = true;
    isPlaying = false;
    countGorilladeath++;
  }

  //コンティニュー
  static public void cntn(ActionEvent e){
    countBath--;
    isDead = false;
    allSet();
  }

  //re:Start
  static public void reStart(ActionEvent e){
    countBath = 0;
    isDead = false;
    allSet();
  }

  //start
  static public void irairastart(MouseEvent e){
    if(isDead) return;
    //System.out.println("start!");
    statusLabel.setText("生きてる！！");
    startPanel.setBackground(Color.cyan);
    isPlaying = true;

  }

  //end
  static public void irairaend(MouseEvent e){
    if(!isPlaying || isDead) return;
    //System.out.println("end!");
    isPlaying = false;
    countBathSum++;
    if(countHighBath<=countBath){
      countHighBath = countBath;
    }
    allSet();
  }

  //道をとりあえず全部場外に放り投げる
  static void roadPanelReset(){
    for(int i = 0; i<roadPanel.length; i++){
      roadPanel[i].setBounds(0,-50,50,50);
    }
  }

  //道の材料を揃える
  static void roadPanelSet(){
    for(int i = 0; i<roadPanel.length; i++){
      roadPanel[i] = new JPanel();
      myframe.add(roadPanel[i]);
      roadPanel[i].setBounds(0,-50,50,50);
      roadPanel[i].setBackground(new Color(115,66,41));
      roadPanel[i].addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
      		alive(e);
      	}
      } );
    }
  }

  //ありがたいお言葉やぞ
  static void statusSet(){
    statusLabel.setText("青いやつ押すと…？？？");
    countBathLabel.setText(countBath + "回入浴！ / 今まで" + countBathSum + "回入浴！ / ハイ入浴：" + countHighBath);
    countAmazonDeathLabel.setText(countAmazonDeath + "回アマゾンに食われた");
    countLionDeathLabel.setText(countLionDeath + "回ライオンに見られた");
    countGorilladeathLabel.setText(countGorilladeath + "回うほうほ");
    startPanel.setBackground(Color.blue);
  }

  static void allSet(){
    if(countBath<7){
      judge = countBath+1;
      reverseJudge = 7-countBath;
    }else{
      judge = 8;
      reverseJudge = 0;
    }
    statusSet();
    roadPanelReset();
    roadSet();

    for(int i =0; i<4; i++){
      gorillaPanel[i].setBounds(0,-50,50,50);
      lionPanel[i].setBounds(0,-50,50,50);
    }
    countGorilla=0;
  }
}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * メインパネル
 * 
 * @author kenta
 *  
 */
/**
 * @author user
 *
 */
public class SidePanel extends JPanel implements Runnable{
	

	// 得点ラベル
	JLabel pointDispLabel;
	JLabel lifeDispLabel;
    // パネルサイズ
    public static final int PANEL_WIDTH = 200;
    public static final int PANEL_HEIGHT = 600;
    // ラベルサイズ
    public static final int LABEL_WIDTH = 190;
    public static final int LABEL_HEIGHT = 80;
    //マネージャ
    Manager manager;
    //ゲームループ
    Thread gameLoop;
    
    /**
     * コンストラクタ
     * @param manager
     */
    public SidePanel(Manager manager) {
    	// マネージャーを受け取る
        this.manager = manager;
        // パネルの推奨サイズを設定、pack()するときに必要
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        // パネルがキー入力を受け付けるようにする
        setFocusable(true);
        
        // 得点ラベルの生成と設定
        this.pointDispLabel = new JLabel("SCORE: " + new Integer(this.manager.getPoint()).toString());
        this.pointDispLabel.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        this.pointDispLabel.setHorizontalAlignment(JLabel.CENTER);
        this.pointDispLabel.setVerticalAlignment(JLabel.TOP);
        this.pointDispLabel.setBackground(Color.white);
        this.pointDispLabel.setFont(new Font("Impact", Font.BOLD, 30 ) );
        // ライフラベルの生成と設定
        this.lifeDispLabel = new JLabel("YOUR LIFE: " + new Integer(this.manager.getLife()).toString());
        this.pointDispLabel.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        this.pointDispLabel.setHorizontalAlignment(JLabel.CENTER);
        this.pointDispLabel.setVerticalAlignment(JLabel.CENTER);
        this.pointDispLabel.setBackground(Color.white);
        this.pointDispLabel.setFont(new Font("Impact", Font.BOLD, 30 ) );
        // ラベルの追加
        this.add(this.pointDispLabel);
        this.add(this.lifeDispLabel);
        
        // ゲームループ開始
        this.gameLoop = new Thread(this);
        this.gameLoop.start();
    }
    public void run() {
        while (true) {
        	
            //得点ラベルセット
            this.pointDispLabel.setText("SCORE: " + new Integer(this.manager.getPoint()).toString());
            this.lifeDispLabel.setText("YOUR LIFE: " + new Integer(this.manager.getLife()).toString());
            
            // 休止
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    


}
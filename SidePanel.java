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
	Label pointDispLabel; 
    // パネルサイズ
    public static final int WIDTH = 200;
    public static final int HEIGHT = 600;
    //マネージャ
    Manager manager;
    //ゲームループ
    Thread gameLoop;
    
    
    /**
     * コンストラクタ
     * @param manager
     */
    public SidePanel(Manager manager) {
        // パネルの推奨サイズを設定、pack()するときに必要
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // パネルがキー入力を受け付けるようにする
        setFocusable(true);
        // マネージャーを受け取る
        this.manager = manager;
        // ラベルの生成と追加
        this.pointDispLabel = new Label(new Integer(this.manager.getPoint()).toString());
        this.pointDispLabel.setBackground(Color.white);
        this.pointDispLabel.setFont(new Font("Impact", Font.BOLD, 30 ) );
        this.add(pointDispLabel);
        // ゲームループ開始
        this.gameLoop = new Thread(this);
        this.gameLoop.start();
    }
    public void run() {
        while (true) {
        	
            //得点ラベルセット
            this.pointDispLabel.setText(new Integer(this.manager.getPoint()).toString());
            
            // 休止
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    


}
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
import javax.swing.border.LineBorder;

/**
 * サイドパネル
 * 
 * @author kenta
 *  
 */
public class SidePanel extends JPanel implements Runnable{
	
	// サイドパネルの背景色
	private static final Color BACKGROUND_COLOR = new Color(40, 40, 40);
	
	// 得点ラベル
	JLabel pointLabel;
	JLabel lifeLabel;
	JLabel stageLabel;
	JLabel timeLabel;
    // パネルサイズ
    public static final int PANEL_WIDTH = 200;
    public static final int PANEL_HEIGHT = 600;
    public static final int PANEL_BORDER = 40;
    // ラベルサイズ
    public static final int LABEL_WIDTH = 150;
    public static final int LABEL_HEIGHT = 40;
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
        // パネルの設定
        // パネルの推奨サイズを設定、pack()するときに必要
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(BACKGROUND_COLOR);
        this.setBorder(new LineBorder(BACKGROUND_COLOR, PANEL_BORDER));
        // パネルがキー入力を受け付けるようにする
        setFocusable(true);
        
        // 得点ラベルの生成と設定
        this.pointLabel = new JLabel();
        this.pointLabel.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        this.pointLabel.setHorizontalAlignment(JLabel.LEFT);
        this.pointLabel.setVerticalAlignment(JLabel.TOP);
        this.pointLabel.setForeground(new Color(255, 255, 255));
        this.pointLabel.setFont(new Font("Impact", Font.PLAIN, 30 ));
        // ライフラベルの生成と設定
        this.lifeLabel = new JLabel();
        this.lifeLabel.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        this.lifeLabel.setHorizontalAlignment(JLabel.LEFT);
        this.lifeLabel.setVerticalAlignment(JLabel.CENTER);
        this.lifeLabel.setForeground(new Color(255, 255, 255));
        this.lifeLabel.setFont(new Font("Impact", Font.PLAIN, 30 ));
        // ステージラベルの生成と設定
        this.stageLabel = new JLabel();
        this.stageLabel.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        this.stageLabel.setHorizontalAlignment(JLabel.LEFT);
        this.stageLabel.setVerticalAlignment(JLabel.BOTTOM);
        this.stageLabel.setForeground(new Color(255, 255, 255));
        this.stageLabel.setFont(new Font("Impact", Font.PLAIN, 30 ));
        // ステージラベルの生成と設定
        this.timeLabel = new JLabel();
        this.timeLabel.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        this.timeLabel.setHorizontalAlignment(JLabel.LEFT);
        this.timeLabel.setVerticalAlignment(JLabel.TOP);
        this.timeLabel.setForeground(new Color(255, 255, 255));
        this.timeLabel.setFont(new Font("Impact", Font.PLAIN, 30 ));
        // ラベルの追加
        this.add(this.timeLabel);
        this.add(this.pointLabel);
        this.add(this.lifeLabel);
        this.add(this.stageLabel);
        
        // ゲームループ開始
        this.gameLoop = new Thread(this);
        this.gameLoop.start();
    }
    public void run() {
        while (true) {
        	
            // ラベルに文字列をセット
            this.pointLabel.setText("SCORE : " + new Integer(this.manager.getPoint()).toString());
            this.lifeLabel.setText("YOUR LIFE : " + new Integer(this.manager.getLife()).toString());
            this.stageLabel.setText("STAGE : " + new Integer(this.manager.getStage()).toString());
            this.timeLabel.setText("TIME : " + new Float(this.manager.getElapsedTime()).toString());
            
            // 休止
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    


}
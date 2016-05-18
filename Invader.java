import java.awt.Container;
import java.awt.Label;

import javax.swing.JFrame;

/**
 * インベーダーゲーム本体
 * 
 * @author anonimous
 *  
 */
public class Invader extends JFrame {
	
	Label pointDispLabel;
	
	// コンストラクタ
    public Invader() {
        // タイトルを設定
        setTitle("サウンドエンジンテスト");
        // サイズ変更不可
        setResizable(false);
        // メインパネルを作成
        MainPanel panel = new MainPanel();
        // 得点ラベル
        pointDispLabel = new Label(new Integer(panel.getManager().getPoint()).toString());
        panel.add(pointDispLabel);
        //フレームを作成してパネルを追加
        Container contentPane = this.getContentPane();
        contentPane.add(panel);
        // パネルサイズに合わせてフレームサイズを自動設定
        this.pack();
    }

    public static void main(String[] args) {
        Invader frame = new Invader();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public Label getPointDispLabel() {
    	return pointDispLabel;
    }
}
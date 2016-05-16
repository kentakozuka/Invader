import java.awt.Container;

import javax.swing.JFrame;

/**
 * インベーダーゲーム本体
 * 
 * @author mori
 *  
 */
public class Invader extends JFrame {
	
	// コンストラクタ
    public Invader() {
        // タイトルを設定
        setTitle("サウンドエンジンテスト");
        // サイズ変更不可
        setResizable(false);

        // メインパネルを作成してフレームに追加
        MainPanel panel = new MainPanel();
        Container contentPane = getContentPane();
        contentPane.add(panel);

        // パネルサイズに合わせてフレームサイズを自動設定
        pack();
    }

    public static void main(String[] args) {
        Invader frame = new Invader();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
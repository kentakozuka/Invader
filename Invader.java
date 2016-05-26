import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Label;

import javax.swing.JFrame;

/**
 * インベーダーゲーム本体
 * 
 * @author anonymous
 *  
 */
public class Invader extends JFrame {
	
	
	// コンストラクタ
    public Invader() {
        // タイトルを設定
        setTitle("インベーダー2016");
        // サイズ変更不可
        setResizable(false);
        // マネージャーを作成
        Manager manager = new Manager();
        // メインパネルを作成
        MainPanel mainPanel = new MainPanel(manager);
        // サイドパネルの作成と設定
        SidePanel sidePanel = new SidePanel(manager);
        sidePanel .setBackground(Color.gray);
        
        //フレームを作成してパネルを追加
        Container contentPane = this.getContentPane();
        contentPane.add(mainPanel, BorderLayout.WEST);
        contentPane.add(sidePanel, BorderLayout.EAST);
        // パネルサイズに合わせてフレームサイズを自動設定
        this.pack();
    }

    public static void main(String[] args) {
        Invader frame = new Invader();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
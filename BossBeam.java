import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

/*
 * Created on 2005/03/19
 *
 */

/**
 * ビームクラス
 * 
 * @author kozuka
 *  
 */
public class BossBeam {
    // ビームのスピード
    private static final int SPEED = 10;
    // ビームの保管座標（画面に表示されない場所）
    private static final Point STORAGE = new Point(-20, -20);
    // ビームの位置（x座標）
    private int x;
    // ビームの位置（y座標）
    private int y;
    // ビームの幅
    private int width;
    // ビームの高さ
    private int height;
    // ビームの画像
    private Image image;
    // メインパネルへの参照
    private MainPanel panel;
    //コンストラクタ
    public BossBeam(MainPanel panel) {
        x = STORAGE.x;
        y = STORAGE.y;
        this.panel = panel;

        // イメージをロード
        loadImage();
    }

    /**
     * ビームを移動する
     */
    public void move(int i) {
        // 保管庫に入っているなら何もしない
        if (this.isInStorage()) {
            return;
         // 画面外のビームは保管庫行き
        } else if (y > MainPanel.HEIGHT || y < 0 || x < 0 || x > MainPanel.WIDTH) {
        	this.store();
        // 画面内なら移動
        } else {
	        double degree = (double) Math.PI/4 + (double) ((double) Math.PI/2 / MainPanel.getNumBossBeam()) * (i + 1);
	        this.x += this.SPEED * (double) Math.cos(degree);
	        this.y += this.SPEED * (double) Math.sin(degree);
        }
    }

    /**
     * ビームの位置を返す
     * 
     * @return ビームの位置座標
     */
    public Point getPos() {
        return new Point(x, y);
    }

    /**
     * ビームの位置をセットする
     * 
     * @param x ビームのx座標
     * @param y ビームのy座標
     */
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * ビームの幅を返す。
     * 
     * @param width ビームの幅。
     */
    public int getWidth() {
        return width;
    }

    /**
     * ビームの高さを返す。
     * 
     * @return height ビームの高さ。
     */
    public int getHeight() {
        return height;
    }

    /**
     * ビームを保管庫に入れる
     */
    public void store() {
        x = STORAGE.x;
        y = STORAGE.y;
    }

    /**
     * ビームが保管庫に入っているか
     * 
     * @return 入っているならtrueを返す
     */
    public boolean isInStorage() {
        if (x == STORAGE.x && y == STORAGE.x) {
            return true;
        }
        return false;
    }

    /**
     * ビームを描画する
     * 
     * @param g 描画オブジェクト
     */
    public void draw(Graphics g) {
        // ビームを描画する
        g.drawImage(image, x, y, null);
    }

    /**
     * イメージをロードする
     *  
     */
    private void loadImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource("image/beam.gif"));
        image = icon.getImage();

        // 幅と高さをセット
        width = image.getWidth(panel);
        height = image.getHeight(panel);
    }
}
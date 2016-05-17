import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;


/**
 * 隕石クラス
 * 
 * @author kozuka
 *  
 */
public class Meteorite {
    // 隕石の移動範囲
    private static final int MOVE_WIDTH = 200;
    // 隕石の墓（画面に表示されない場所）
    private static final Point TOMB = new Point(-50, -50);
    // 移動スピード
    private int speed;
    // 隕石の位置（x, y座標）
    private int x;
    private int y;
    // 隕石の幅
    private int width;
    // 隕石の高さ
    private int height;
    // 隕石の画像
    private Image image;
    // 隕石の移動範囲
    private int left;
    private int right;
    // 隕石が生きてるかどうか
    private boolean isAlive;
    // メインパネルへの参照
    private MainPanel panel;
    // コンストラクタ
    public Meteorite(int x, int y, int speed, MainPanel panel) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.panel = panel;

        // 隕石の初期位置から移動範囲を求める
        left = x;
        right = x + MOVE_WIDTH;

        isAlive = true;

        // イメージをロード
        loadImage();
    }

    /**
     * 隕石を移動する
     *  
     */
    public void move() {
    	// 下に進む
        y += speed;

        // ウィンドウの下まで行ったら上に移動
        if (this.y > MainPanel.HEIGHT) {
            this.initMeteorite();
        }
    }

    /**
     * 隕石の位置の初期化
     */
    public void initMeteorite() {
    	this.x = (new Random()).nextInt(MainPanel.getWIDTH());
    	this.y = 0;
    }
    /**
     * 隕石と弾の衝突を判定する
     * 
     * @param shot 衝突しているか調べる弾オブジェクト
     * @return 衝突していたらtrueを返す
     */
    public boolean collideWith(Shot shot) {
        // 隕石の矩形を求める
        Rectangle rectMeteorite = new Rectangle(x, y, width, height);
        // 弾の矩形を求める
        Point pos = shot.getPos();
        Rectangle rectShot = new Rectangle(pos.x, pos.y, 
                shot.getWidth(), shot.getHeight());

        // 矩形同士が重なっているか調べる
        // 重なっていたら衝突している
        return rectMeteorite.intersects(rectShot);
    }

    /**
     * 隕石が死ぬ、墓へ移動
     *  
     */
    public void die() {
        setPos(TOMB.x, TOMB.y);
        isAlive = false;
    }

    /**
     * 隕石の幅を返す
     * 
     * @param width 隕石の幅
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * 隕石の高さを返す
     * 
     * @return height 隕石の高さ
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * 隕石の位置を返す
     * 
     * @return 隕石の位置座標
     */
    public Point getPos() {
        return new Point(this.x, this.y);
    }

    /**
     * 隕石の位置を(x,y)にセットする
     * 
     * @param x 移動先のx座標
     * @param y 移動先のy座標
     */
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 隕石が生きているか
     * 
     * @return 生きていたらtrueを返す
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * 隕石を描画する
     * 
     * @param g 描画オブジェクト
     */
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    /**
     * イメージをロードする
     *  
     */
    private void loadImage() {
        // 隕石のイメージを読み込む
        // ImageIconを使うとMediaTrackerを使わなくてすむ
        ImageIcon icon = new ImageIcon(getClass()
                .getResource("image/meteorite.gif"));
        image = icon.getImage();

        // 幅と高さをセット
        width = image.getWidth(panel);
        height = image.getHeight(panel);
    }
}
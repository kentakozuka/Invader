import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


/**
 * エイリアンクラス
 * 
 * @author koganezaki
 *  
 */
public class Boss {
    // ボスの移動範囲
    private static final int MOVE_WIDTH = 200;
    // ボスの墓（画面に表示されない場所）
    private static final Point TOMB = new Point(-50, -50);
    // 移動スピード
    private int speed;
    // ボスの位置（x座標）
    private int x;
    // ボスの位置（y座標）
    private int y;
    // ボスの幅
    private int width;
    // ボスの高さ
    private int height;
    // ボスの画像
    private Image image;
    // ボスの移動範囲
    private int left;
    private int right;
    // ボスが生きてるかどうか
    private boolean isAlive;
    // メインパネルへの参照
    private MainPanel panel;
    // コンストラクタ
    public Boss(int x, int y, int speed, MainPanel panel) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.panel = panel;

        // ボスの初期位置から移動範囲を求める
        left = x;
        right = x + MOVE_WIDTH;

        isAlive = true;

        // イメージをロード
        loadImage();
    }
    /**
     * ボスを移動する
     *  
     */
    public void move() {
        x += speed;

        // 移動範囲を超えていたら反転移動
        if (x < left) {
            speed = -speed;
        }
        if (x > right) {
            speed = -speed;
        }
    }

    /**
     * ボスと弾の衝突を判定する
     * 
     * @param shot 衝突しているか調べる弾オブジェクト
     * @return 衝突していたらtrueを返す
     */
    public boolean collideWith(Shot shot) {
        // ボスの矩形を求める
        Rectangle rectBoss = new Rectangle(x, y, width, height);
        // 弾の矩形を求める
        Point pos = shot.getPos();
        Rectangle rectShot = new Rectangle(pos.x, pos.y, 
                shot.getWidth(), shot.getHeight());

        // 矩形同士が重なっているか調べる
        // 重なっていたら衝突している
        return rectBoss.intersects(rectShot);
    }

    /**
     * ボスが死ぬ、墓へ移動
     *  
     */
    public void die() {
        setPos(TOMB.x, TOMB.y);
        isAlive = false;
        
    }

    /**
     * ボスの幅を返す
     * 
     * @param width ボスの幅
     */
    public int getWidth() {
        return width;
    }

    /**
     * ボスの高さを返す
     * 
     * @return height ボスの高さ
     */
    public int getHeight() {
        return height;
    }

    /**
     * ボスの位置を返す
     * 
     * @return　ボスの位置座標
     */
    public Point getPos() {
        return new Point(x, y);
    }

    /**
     * ボスの位置を(x,y)にセットする
     * 
     * @param x 移動先のx座標
     * @param y 移動先のy座標
     */
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * ボスが生きているか
     * 
     * @return 生きていたらtrueを返す
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * ボスを描画する
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
        // ボスのイメージを読み込む
        // ImageIconを使うとMediaTrackerを使わなくてすむ
        ImageIcon icon = new ImageIcon(getClass()
                .getResource("image/boss.gif"));
        image = icon.getImage();

        // 幅と高さをセット
        width = image.getWidth(panel);
        height = image.getHeight(panel);
    }
}
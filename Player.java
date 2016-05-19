import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * プレイヤークラス
 * 
 * @author mori
 *  
 */
public class Player {
    // 方向定数
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    // 移動スピード
    private static final int SPEED = 5;

    // プレイヤーの位置（x, y座標）
    private int x;
    private int y;
    // プレイヤーの幅
    private int width;
    // プレイヤーの高さ
    private int height;
    // プレイヤーの画像
    private Image image;

    // メインパネルへの参照
    private MainPanel panel;

    public Player(int x, int y, MainPanel panel) {
        this.x = x;
        this.y = y;
        this.panel = panel;

        // イメージをロード
        loadImage();
    }

    /**
     * プレイヤーを移動する
     * 
     * @param dir 移動方向
     */
    public void move(int dir) {
        if (dir == LEFT) {
            x -= SPEED;
        } else if (dir == RIGHT) {
            x += SPEED;
        } else if (dir == UP) {
        	y -= SPEED;
        } else if (dir == DOWN) {
        	y += SPEED;
        }

        // 画面の外に出ていたら中に戻す
        if (x < 0) {
            x = 0;
        }
        if (x > MainPanel.WIDTH - this.width) {
            x = MainPanel.WIDTH - this.width;
        }
        if (y < 0) {
        	y = 0;
        }
        if (y > MainPanel.HEIGHT - this.height) {
        	y = MainPanel.HEIGHT - this.height;
        }
    }

    /**
     * プレイヤーとビームの衝突を判定する
     * 
     * @param beam 衝突しているか調べるビームオブジェクト
     * @return 衝突していたらtrueを返す
     */
    public boolean collideWith(Beam beam) {
        // プレイヤーの矩形を求める
        Rectangle rectPlayer = new Rectangle(x, y, width, height);
        // ビームの矩形を求める
        Point pos = beam.getPos();
        Rectangle rectBeam = new Rectangle(pos.x, pos.y, beam.getWidth(), beam.getHeight());

        // 矩形同士が重なっているか調べる
        // 重なっていたら衝突している
        return rectPlayer.intersects(rectBeam);
    }
    public boolean collideWith(Meteorite meteorite) {
    	// プレイヤーの矩形を求める
        Rectangle rectPlayer = new Rectangle(this.x, this.y, this.width, this.height);
        // ビームの矩形を求める
        Point pos = meteorite.getPos();
        Rectangle rectMeteorite = new Rectangle(pos.x, pos.y, meteorite.getWidth(), meteorite.getHeight());

        // 矩形同士が重なっているか調べる
        // 重なっていたら衝突している
        return rectPlayer.intersects(rectMeteorite);
	}
    public boolean collideWith(Alien alien) {
    	// プレイヤーの矩形を求める
        Rectangle rectPlayer = new Rectangle(this.x, this.y, this.width, this.height);
        // ビームの矩形を求める
        Point pos = alien.getPos();
        Rectangle rectMeteorite = new Rectangle(pos.x, pos.y, alien.getWidth(), alien.getHeight());

        // 矩形同士が重なっているか調べる
        // 重なっていたら衝突している
        return rectPlayer.intersects(rectMeteorite);
	}
    /**
     * プレイヤーを描画する
     * 
     * @param g 描画オブジェクト
     */
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    /**
     * プレイヤーの位置を返す
     * 
     * @return プレイヤーの位置座標
     */
    public Point getPos() {
        return new Point(x, y);
    }

    /**
     * プレイヤーの幅を返す
     * 
     * @param width プレイヤーの幅
     */
    public int getWidth() {
        return width;
    }

    /**
     * プレイヤーの高さを返す
     * 
     * @return height プレイヤーの高さ
     */
    public int getHeight() {
        return height;
    }

    /**
     * イメージをロードする
     *  
     */
    private void loadImage() {
        // プレイヤーのイメージを読み込む
        // ImageIconを使うとMediaTrackerを使わなくてすむ
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "image/player.gif"));
        image = icon.getImage();

        // 幅と高さをセット
        width = image.getWidth(panel);
        height = image.getHeight(panel);
    }    
}
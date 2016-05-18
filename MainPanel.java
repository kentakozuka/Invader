import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
 * @author mori
 *  
 */
public class MainPanel extends JPanel implements Runnable, 
												KeyListener {
    // パネルサイズ
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    // 方向定数
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    // 連続発射できる弾の数
    private static final int NUM_SHOT = 50;
    // 発射できる間隔（弾の充填時間）
    private static final int FIRE_INTERVAL = 150;
    // エイリアンの数
    private static final int NUM_ALIEN = 50;
    //　ボスの数
    private static final int NUM_BOSS = 1;
    // 隕石の数
    private static final int NUM_METEORITE = 15;
    // ビームの数
    private static final int NUM_BEAM = 20;
    // ステージ数
    private static final int NUM_STAGE = 1;
    // 各インスタンスの宣言
    private Player player;
    private Shot[] shots;
    private Alien[] aliens;
    private Boss[] boss;
    private Meteorite[] meteorites;
    private Beam[] beams;
    private Explosion explosion;
    // 最後に発射した時間
    private long lastFire = 0;
    private int dieCount = 0;
    private int stage = 0;
    // 雑魚敵の全滅確認
    private boolean alienAllClear = false;
    // キーの状態（このキー状態を使ってプレイヤーを移動する）
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean spacePressed = false;
    // ゲームループ用スレッド
    private Thread gameLoop;
    // 乱数発生器
    private Random rand;
    // サウンド
    private static String[] soundNames = {"bom28_a.wav", "puu38.wav", "puu51.wav"};
    // コンストラクタ
    public MainPanel() {
        // パネルの推奨サイズを設定、pack()するときに必要
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // パネルがキー入力を受け付けるようにする
        setFocusable(true);

        // ゲームの初期化
        initGame();
    
        rand = new Random();

        // サウンドをロード
        for (int i=0; i<soundNames.length; i++) {
            try {
                WaveEngine.load("se/" + soundNames[i]);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }

        // BGMをロード
        try {
            MidiEngine.load("bgm/tam-g07.mid");
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // BGMを再生
        MidiEngine.play(0);
        
        // キーイベントリスナーを登録
        addKeyListener(this);

        // ゲームループ開始
        this.gameLoop = new Thread(this);
        this.gameLoop.start();
    }

	/**
     * ゲームループ
     * Runnableインターフェースの抽象メソッドrun()のオーバーライド
     */
    public void run() {
        while (stage <= NUM_STAGE) {
        	// 移動
            this.move();
            // 発射ボタンが押されたら弾を発射
            if (spacePressed) {
                tryToFire();
            }
            // エイリアンの攻撃
            if(alienAllClear == false){
            	this.alienAttack();
            } else {
            	//this.bossAttack();
            }
            // 衝突判定
            this.collisionDetection();
            // 音楽再生
            WaveEngine.render();
            // 再描画
            this.repaint();
            // 休止
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ゲームの初期化
     */
    private void initGame() {
        // プレイヤーを作成
        player = new Player(0, HEIGHT - 20, this);

        // 弾を作成
        shots = new Shot[NUM_SHOT];
        for (int i = 0; i < NUM_SHOT; i++) {
            shots[i] = new Shot(this);
        }

        // エイリアンを作成
        aliens = new Alien[NUM_ALIEN];
        for (int i = 0; i < NUM_ALIEN; i++) {
            aliens[i] = new Alien(20 + (i % 10) * 40, 20 + (i / 10) * 40, 3, this);
        }
        
        // ボスを作成
        boss = new Boss[NUM_BOSS];
    	for (int i = 0; i <NUM_BOSS; i++){
    		boss[i] = new Boss(250 , 30, 3,this);
    	}
    	
        // 隕石を作成
        meteorites = new Meteorite[NUM_METEORITE];
        for (int i = 0; i < NUM_METEORITE; i++) {
        	meteorites[i] = new Meteorite((new Random()).nextInt(this.WIDTH) , -(new Random()).nextInt(this.HEIGHT), 1, this);
        }
        
        // ビームを作成
        beams = new Beam[NUM_BEAM];
        for (int i = 0; i < NUM_BEAM; i++) {
            beams[i] = new Beam(this);
        }
    }
    
    /**
     * 移動処理
     */
    private void move() {
        // プレイヤーを移動する
        if (leftPressed) {
            player.move(LEFT);
        } else if (rightPressed) {
            player.move(RIGHT);
        }
        if (upPressed) {
        	player.move(UP);
        } else if (downPressed) {
        	player.move(DOWN);
        }

        // エイリアンを移動する
        if(alienAllClear == true){
        	//boss[stage].move();
        } else  {        	
	        for (int i = 0; i < NUM_ALIEN; i++) {
	            aliens[i].move();
	        }
        }
 
        // 隕石を移動する
        for (int i = 0; i < NUM_METEORITE; i++) {
            meteorites[i].move();
        }

        // 弾を移動する
        for (int i = 0; i < NUM_SHOT; i++) {
            shots[i].move();
        }

        // ビームを移動する
        for (int i = 0; i < NUM_BEAM; i++) {
            beams[i].move();
        }
    }

    /**
     * 弾を発射する
     */
    private void tryToFire() {
        // 前との発射間隔がFIRE_INTERVAL以下だったら発射できない
        if (System.currentTimeMillis() - lastFire < FIRE_INTERVAL) {
            return;
        }
        //発射時間を更新
        lastFire = System.currentTimeMillis();
        // 発射されていない弾を見つける
        for (int i = 0; i < NUM_SHOT; i++) {
            if (shots[i].isInStorage()) {
                // 弾が保管庫にあれば発射できる
                // 弾の座標をプレイヤーの座標にすれば発射される
                Point pos = player.getPos();
                shots[i].setPos(pos.x + player.getWidth() / 2, pos.y);
                // 発射音
                WaveEngine.play(2);
                // 1つ見つけたら発射してbreakでループをぬける
                break;
            }
        }
    }

    /**
     * エイリアンの攻撃
     */
    private void alienAttack() {
        // 1ターンでNUM_BEAMだけ発射する
        // つまりエイリアン1人になってもそいつがNUM_BEAM発射する
        for (int i = 0; i < NUM_BEAM; i++) {
            // エイリアンの攻撃
            // ランダムにエイリアンを選ぶ
            int n = rand.nextInt(NUM_ALIEN);
            // そのエイリアンが生きていればビーム発射
            if (aliens[n].isAlive()) {
                // 発射されていないビームを見つける
                // 1つ見つけたら発射してbreakでループをぬける
                for (int j = 0; j < NUM_BEAM; j++) {
                    if (beams[j].isInStorage()) {
                        // ビームが保管庫にあれば発射できる
                        // ビームの座標をエイリアンの座標にセットすれば発射される
                        Point pos = aliens[n].getPos();
                        beams[j].setPos(pos.x + aliens[n].getWidth() / 2, pos.y);
                        break;
                    }
                }
            }
        }
    }

    /**
     * 衝突検出
     *  
     */
    private void collisionDetection() {
    	if(alienAllClear == true){
		   for (int j = 0; j < NUM_SHOT; j++) {
	            if (boss[stage].collideWith(shots[j])) {
	                // i番目のエイリアンとj番目の弾が衝突
	                // 爆発エフェクト生成
	                explosion = new Explosion(boss[stage].getPos().x, boss[stage].getPos().y);
	                // ボスは死ぬ
	                boss[stage].die();
	                //　次のステージへ
	                alienAllClear = false;
	                stage ++;
	                // 断末魔
	                WaveEngine.play(1);
	                // 弾は保管庫へ（保管庫へ送らなければ貫通弾になる）
	                shots[j].store();
	                // エイリアンが死んだらもうループまわす必要なし
	                break;
	            }
	        }
    	} else {
	        // エイリアンと弾の衝突検出
	        for (int i = 0 ; i < NUM_ALIEN; i++) {
	            for (int j = 0; j < NUM_SHOT; j++) {
	                if (aliens[i].collideWith(shots[j])) {
	                    // i番目のエイリアンとj番目の弾が衝突
	                    // 爆発エフェクト生成
	                    explosion = new Explosion(aliens[i].getPos().x, aliens[i].getPos().y);
	                    // エイリアンは死ぬ
	                    aliens[i].die();
	                    dieCount++;
	                    // 全滅か判断
	                    if(dieCount == NUM_ALIEN){
	                    	alienAllClear = true;
	                    }
	                    // 断末魔
	                    WaveEngine.play(1);
	                    // 弾は保管庫へ（保管庫へ送らなければ貫通弾になる）
	                    shots[j].store();
	                    // エイリアンが死んだらもうループまわす必要なし
	                    break;
	                }
	            }
	        }
    	}
        
        
        
        // プレーヤーとビームの衝突検出
        for (int i = 0; i < NUM_BEAM; i++) {
            if (player.collideWith(beams[i])) {
                // 爆発エフェクト生成
                explosion = new Explosion(player.getPos().x, player.getPos().y);
                // 爆発音
                WaveEngine.play(0);
                // プレーヤーとi番目のビームが衝突
                // ビームは保管庫へ
                beams[i].store();
                // ゲームオーバー
                initGame();
            }
        }
        
        // プレーヤーと隕石の衝突検出
        for (int i = 0; i < NUM_METEORITE; i++) {
            if (player.collideWith(meteorites[i])) {
                // 爆発エフェクト生成
                explosion = new Explosion(player.getPos().x, player.getPos().y);
                // 爆発音
                WaveEngine.play(0);
                // ゲームオーバー、ゲームを初期化
                initGame();
            }
        }
        
        // 隕石と弾の衝突検出
        for (int i = 0; i < NUM_METEORITE; i++) {
            for (int j = 0; j < NUM_SHOT; j++) {
                if (meteorites[i].collideWith(shots[j])) {
                    // i番目の隕石とj番目の弾が衝突
                    // 爆発エフェクト生成
                    explosion = new Explosion(meteorites[i].getPos().x, meteorites[i].getPos().y);
                    // プレイヤーは死ぬ
                    meteorites[i].initMeteorite();
                    // 弾は保管庫へ（保管庫へ送らなければ貫通弾になる）
                    shots[j].store();
                    // 隕石が無くなったら、もうループまわす必要なし
                    break;
                }
            }
        }        
    }

    /**
     * 描画処理
     * 
     * @param 描画オブジェクト
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 背景を黒で塗りつぶす
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // プレイヤーを描画
        player.draw(g);

        // エイリアンを描画
        if(alienAllClear == false){
	        for (int i = 0; i < NUM_ALIEN; i++) {
	            aliens[i].draw(g);
	        }
    	} else {
	        for (int i = 0; i<NUM_BOSS ; i++){
	        	boss[i].draw(g);
	        }
    	}
        
        // 隕石を描画
        for (int i = 0; i < NUM_METEORITE; i++) {
            meteorites[i].draw(g);
        }

        // 弾を描画
        for (int i = 0; i < NUM_SHOT; i++) {
            shots[i].draw(g);
        }

        // ビームを描画
        for (int i = 0; i < NUM_BEAM; i++) {
            beams[i].draw(g);
        }
        
        // 爆発エフェクトを描画
        if (explosion != null) {
            explosion.draw(g);
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    /**
     * キーが押されたらキーの状態を「押された」に変える
     * 
     * @param e キーイベント
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            this.leftPressed = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (key == KeyEvent.VK_UP) {
        	upPressed = true;
        }
        if (key == KeyEvent.VK_DOWN) {
        	downPressed = true;
        }
        if (key == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
    }

    /**
     * キーが離されたらキーの状態を「離された」に変える
     * 
     * @param e キーイベント
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (key == KeyEvent.VK_UP) {
        	upPressed = false;
        }
        if (key == KeyEvent.VK_DOWN) {
        	downPressed = false;
        }
        if (key == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }

	
    public static int getWIDTH() {
		return WIDTH;
	}
}
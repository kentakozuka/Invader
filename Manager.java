
/**
 * @author user
 *
 */
public class Manager {
	
	// プレイヤーのライフ数
    public static final int LIFE = 5;
    // ステージ数
    public static final int NUM_STAGE = 1;
    
	
    //変数の宣言
	private int numDeadAlien;
	private int numDestroyedMeteorite;
	private int life;
	// 現在のステージ
    private int stage = 0;
	
	

	
	/**
	 * コンストラクタ
	 */
	public Manager(){
		this.initSetting();
	}
	public void initSetting() {
		this.setLife(Manager.LIFE);
		this.setNumDeadAlien(0);
		this.setNumDestroyedMeteorite(0);
	}
	
	/**
	 * 得点ゲッター
	 * 得点を計算して返す
	 * @return
	 */
	public int getPoint() {
		return this.numDeadAlien*10 + this.numDestroyedMeteorite*2;
	}
	/**
	 * 倒したエイリアンの数のインクリメント関数
	 */
	public void incrementNumDeadAlien() {
		this.numDeadAlien++;
	}
	/**
	 * 破壊した隕石のインクリメント関数
	 */
	public void incrementNumDestroyedMeteorite() {
		this.numDestroyedMeteorite++;
	}
	/**
	 * ライフのディクリメント関数
	 */
	public void decrementLife() {
		this.life--;
	}

	/**
	 * 倒したエイリアンの数ゲッター
	 * @return
	 */
	public int getNumDeadAlien() {
		return numDeadAlien;
	}

	/**
	 * 倒したエイリアンの数セッター
	 * @param numDeadAlien
	 */
	public void setNumDeadAlien(int numDeadAlien) {
		this.numDeadAlien = numDeadAlien;
	}

	/**
	 * 破壊した隕石の数ゲッター
	 * @return
	 */
	public int getNumDestroyedMeteorite() {
		return numDestroyedMeteorite;
	}

	/**
	 * 破壊した隕石の数セッター
	 * @param numDestroyedMeteorite
	 */
	public void setNumDestroyedMeteorite(int numDestroyedMeteorite) {
		this.numDestroyedMeteorite = numDestroyedMeteorite;
	}
	/**
	 * ライフのゲッター
	 * @return
	 */
	public int getLife() {
		return this.life;
	}

	/**
	 * ライフのセッター
	 * @param life
	 */
	public void setLife(int life) {
		this.life = life;
	}	
	/**
	 * ステージ数のゲッター
	 * @return
	 */
	public int getStage() {
		return stage;
	}
}

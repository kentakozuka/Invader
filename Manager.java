
/**
 * @author user
 *
 */
public class Manager {
	
	private int numDeadAlien;
	private int numDestroyedMeteorite;
	private int point;
	
<<<<<<< HEAD
	
=======
>>>>>>> refs/remotes/origin/kenta
	/**
	 * コンストラクタ
	 */
	public Manager(){
		numDeadAlien = 0;
		numDestroyedMeteorite = 0;
		point = 0;
	}
	
	/**
	 * 得点ゲッター
	 * 得点を計算して返す
	 * @return
	 */
	public int getPoint() {
		countPoint();
		return this.point;
	}
	/**
	 * 得点計算メソッド
	 */
	public void countPoint() {
		point = numDeadAlien*10 + numDestroyedMeteorite*2;
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
}

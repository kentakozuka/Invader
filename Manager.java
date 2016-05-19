
public class Manager {
	
	private int numDeadAlien;
	private int numDestroyedMeteorite;
	private int point;
	
	
	// コンストラクタ
	public Manager(){
		numDeadAlien = 0;
		numDestroyedMeteorite = 0;
		point = 0;
	}
	
	// pointのゲッター／セッター
	public int getPoint() {
		countPoint();
		return this.point;
	}
	public void setPoint(int point) {
		this.point = point;
	}

	// 得点の計算
	public void countPoint() {
		point = numDeadAlien*10 + numDestroyedMeteorite*2;
	}
	
	public void incrementNumDeadAlien() {
		this.numDeadAlien++;
	}
	public void incrementNumDestroyedMeteorite() {
		this.numDestroyedMeteorite++;
	}

	public int getNumDeadAlien() {
		return numDeadAlien;
	}

	public void setNumDeadAlien(int numDeadAlien) {
		this.numDeadAlien = numDeadAlien;
	}

	public int getNumDestroyedMeteorite() {
		return numDestroyedMeteorite;
	}

	public void setNumDestroyedMeteorite(int numDestroyedMeteorite) {
		this.numDestroyedMeteorite = numDestroyedMeteorite;
	}
}

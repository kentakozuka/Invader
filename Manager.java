
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
	
	public void countPoint() {
		point = numDeadAlien*10 + numDestroyedMeteorite*2;
	}
	public int getPoint() {
		return point;
	}
	public void incrementNumDeadAlien() {
		this.numDeadAlien++;
	}
	public void incrementNumDestroyedMeteorite() {
		this.numDestroyedMeteorite++;
	}
}

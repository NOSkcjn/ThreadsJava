package ru.rsreu.koklyukov0807;

public class Tunnel {

	private int id;
	private static int currentId = 1;
	private Train train1;
	private Train train2;

	public int getId() {
		return id;
	}

	public Train getTrain1() {
		return train1;
	}
	
	public void setTrain1(Train train) {
			train1 = train;
			
			//if(train1 == null) {
			//	notify();
			//}
	}

	public Train getTrain2() {
		return train2;
	}
	
	public void setTrain2(Train train) {
			train2 = train;
			
			//if(train2 == null) {
			//	notify();
			//}
	}

	public Tunnel() {
		id = currentId++;
	}
	
	public void trainGoing(Train train) throws InterruptedException {
		if((train1 != null && train1.equals(train)) ||
				(train2 != null && train2.equals(train))) {
			
			Thread.sleep(500);
		}
	}
}

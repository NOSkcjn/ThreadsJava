package ru.rsreu.koklyukov0807;

import java.util.Random;

public class Train implements Runnable {

	private final static int MAX_TRAVEL_TIME = 400;

	private int id;
	private static int currentId = 1;
	private TunnelsSeries tunnelsSeries;
	Direction direction;

	public int getId() {
		return id;
	}

	public Train(TunnelsSeries tunnelsSeries, Direction direction) {
		id = currentId++;
		this.tunnelsSeries = tunnelsSeries;
		this.direction = direction;
	}

	public void setToTunnel(Tunnel tunnel, Direction direction) {
		if (direction == Direction.FORWARD) {
			tunnel.setTrain1(this);
		} else {
			tunnel.setTrain2(this);
		}
	}

	public void goingInTunnel() throws InterruptedException {
		Thread.sleep(new Random().nextInt(MAX_TRAVEL_TIME));
	}

	public void run() {

		Tunnel tunnel = null;
		try {
			while (tunnel == null) {
				System.out.printf("Train %d trying to go in tunnel direction %s\n",
						id, direction.toString());
				tunnel = tunnelsSeries.tryGetTunnel(this, direction);
			}
			System.out.printf(
					"Train %d started going in tunnel %d direction %s\n", id,
					tunnel.getId(), direction);
			goingInTunnel();
			//tunnelsSeries.goingInTunnel(tunnel, this);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (tunnel != null) {
				tunnelsSeries.freeTunnel(tunnel, direction);
			}
		}
	}
}

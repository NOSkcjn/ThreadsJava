package ru.rsreu.koklyukov0807;

import java.util.List;

public class TunnelsSeries {

	private List<Tunnel> tunnels;
	private final static int WAIT_TIME = 500;
	
	public TunnelsSeries(List<Tunnel> tunnels) {
		this.tunnels = tunnels;
	}

	public synchronized Tunnel tryGetTunnel(Train train, Direction direction) throws InterruptedException {
		for (Tunnel tunnel : tunnels) {
			if ((direction == Direction.FORWARD && tunnel.getTrain1() == null)) {
				tunnel.setTrain1(train);
				return tunnel;
			}
			if ((direction == Direction.BACKWARD && tunnel.getTrain2() == null)) {
				tunnel.setTrain2(train);
				return tunnel;
			}
			
			System.out.printf("Train %d try redirects to next tunnel\n", train.getId());
			wait(WAIT_TIME);
		}

		return null;
	}

	public synchronized void freeTunnel(Tunnel tunnel, Direction direction) {
		for (Tunnel current : tunnels) {
			if (current.equals(tunnel)) {
				setTunnelTrainNull(tunnel, direction);
				System.out.printf("Tunnel %d direction %s is free\n",
						tunnel.getId(), direction);
				break;
			}
		}
		
		notify();
	}

	public void goingInTunnel(Tunnel tunnel, Train train) throws InterruptedException {
		tunnel.trainGoing(train);
		//wait();
	}
	
	private void setTunnelTrainNull(Tunnel tunnel, Direction direction) {
		if (direction == Direction.FORWARD) {
			tunnel.setTrain1(null);
		} else {
			tunnel.setTrain2(null);
		}
	}
}

package ru.rsreu.koklyukov0807;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Runner {

	private final static int TRAINS_COUNT = 6;
	private final static int TUNNELS_COUNT = 2;
	
	public static void main(String[] args) {

		List<Tunnel> tunnels = new ArrayList<Tunnel>();
		for (int i = 0; i < TUNNELS_COUNT; i++) {
			tunnels.add(new Tunnel());
		}

		TunnelsSeries tunnelsSeries = new TunnelsSeries(tunnels);

		for (int i = 0; i < TRAINS_COUNT; i++) {

			Thread thread = new Thread(new Train(tunnelsSeries,
					(new Random().nextInt(2) == 1) ? Direction.FORWARD : Direction.BACKWARD));
			thread.start();
		}
	}
}

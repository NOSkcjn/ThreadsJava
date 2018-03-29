package ru.rsreu.koklyukov0807;

public enum Direction {

	FORWARD {
		@Override
		public String toString() {
			return "forward";
		}
	},
	BACKWARD {
		@Override
		public String toString() {
			return "backward";
		}
	}
}

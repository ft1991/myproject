package entity;

import java.sql.Time;

public class Lineinfor {
	private String rnumber;
	private String departure;
	private String destination;
	private Time startTime;
	private Time endTime;

	public Lineinfor(String rnumber, String departure, String destination, Time startTime, Time endTime) {
		super();
		this.rnumber = rnumber;
		this.departure = departure;
		this.destination = destination;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Lineinfor() {
		super();
	}

	public String getRnumber() {
		return rnumber;
	}

	public void setRnumber(String rnumber) {
		this.rnumber = rnumber;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Lineinfor [rnumber=" + rnumber + ", departure=" + departure + ", destination=" + destination
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

}

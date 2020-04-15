package io.kulkarni.model;

public class LocationStats {

	private String State;
	private String Country;
	private int LatestTotalCases;
	private int ChangeSincePreviousDay;

	public int getChangeSincePreviousDay() {
		return ChangeSincePreviousDay;
	}

	public void setChangeSincePreviousDay(int changeSincePreviousDay) {
		ChangeSincePreviousDay = changeSincePreviousDay;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public int getLatestTotalCases() {
		return LatestTotalCases;
	}

	public void setLatestTotalCases(int latestTotalCases) {
		LatestTotalCases = latestTotalCases;
	}

	@Override
	public String toString() {
		return "LocationStats [State=" + State + ", Country=" + Country + ", LatestTotalCases=" + LatestTotalCases
				+ ", ChangeSincePreviousDay=" + ChangeSincePreviousDay + "]";
	}

}

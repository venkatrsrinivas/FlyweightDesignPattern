package FlightDemo;

import java.util.HashMap;
import java.util.Random;

enum PlaneType {
	PRIVATE, DOMESTIC, INTERNATIONAL;
}

interface Flight {
	public void setMiles(Double milesToTravel);
	public void startFlying();
}

class InternationalFlight implements Flight {
	
	public InternationalFlight() {
		for(int k=0; k<100; k++) {
			System.out.println("Getting International Flight Ready For Take-Off");
		}
	}
	
	public void setMiles(Double milesToTravel) {
		this.currentMiles = milesToTravel;
	}
	
	public void startFlying() {
		System.out.println("International Flight About To Take-Off For " + this.currentMiles + " Miles!");
	}
	
	final PlaneType currentType = PlaneType.INTERNATIONAL;
	Double currentMiles = 0.0;
}


class DomesticFlight implements Flight {
	
	public DomesticFlight() {
		for(int k=0; k<100; k++) {
			System.out.println("Getting Domestic Flight Ready For Take-Off");
		}
	}
	
	public void setMiles(Double milesToTravel) {
		this.currentMiles = milesToTravel;
	}
	
	public void startFlying() {
		System.out.println("Domestic Flight About To Take-Off For " + this.currentMiles + " Miles!");
	}
	
	final PlaneType currentType = PlaneType.DOMESTIC;
	Double currentMiles = 0.0;
}

class PrivateFlight implements Flight {
	
	public PrivateFlight() {
		for(int k=0; k<100; k++) {
			System.out.println("Getting Private Flight Ready For Take-Off");
		}
	}
	
	public void setMiles(Double milesToTravel) {
		this.currentMiles = milesToTravel;
	}
	
	public void startFlying() {
		System.out.println("Private Flight About To Take Off For " + this.currentMiles + " Miles!");
	}
	
	final PlaneType currentType = PlaneType.PRIVATE;
	Double currentMiles = 0.0;
}


class AirportFlightFactory {
	//Can Extend To Reflect The True Flights In An Actual Airport.
	//Right Now, Only 3 Flights, But In Reality Many More. 
	private static final HashMap<PlaneType, Flight> allFlightsMap = new HashMap<>();

	public static Flight getMyFlight(Double milesToTravel) {
		if(milesToTravel >= 0 && milesToTravel <= 7500) {
			return getPrivateFlight(milesToTravel);
		}
		else if(milesToTravel >= 7500 && milesToTravel <= 20000) {
			return getDomesticFlight(milesToTravel);
		}
		else if(milesToTravel >= 20000 && milesToTravel <= 70000) {
			return getInternationalFlight(milesToTravel);
		}
		else{
			System.out.println("Invalid Number of Miles To Travel");
			return null;
		}		
	}
	
	private static Flight getPrivateFlight(Double milesToTravel) {
//		Flight correctFlight = allFlightsMap.get(PlaneType.PRIVATE);
//		if(correctFlight == null) {
//			correctFlight = new PrivateFlight();
//			correctFlight.setMiles(milesToTravel);
//			allFlightsMap.put(PlaneType.PRIVATE, correctFlight);
//		}
//		return correctFlight;

		Flight correctFlight = new PrivateFlight();
		correctFlight.setMiles(milesToTravel);
		return correctFlight;
	}
	
	private static Flight getDomesticFlight(Double milesToTravel) {
//		Flight correctFlight = allFlightsMap.get(PlaneType.DOMESTIC);
//		if(correctFlight == null) {
//			correctFlight = new DomesticFlight();
//			correctFlight.setMiles(milesToTravel);
//			allFlightsMap.put(PlaneType.DOMESTIC, correctFlight);
//		}
//		return correctFlight;
		
		Flight correctFlight = new DomesticFlight();
		correctFlight.setMiles(milesToTravel);
		return correctFlight;
	}
	
	private static Flight getInternationalFlight(Double milesToTravel) {
//		Flight correctFlight = allFlightsMap.get(PlaneType.INTERNATIONAL);
//		if(correctFlight == null) {
//			correctFlight = new InternationalFlight();
//			correctFlight.setMiles(milesToTravel);
//			allFlightsMap.put(PlaneType.INTERNATIONAL, correctFlight);
//		}
//		return correctFlight;
		
		Flight correctFlight = new InternationalFlight();
		correctFlight.setMiles(milesToTravel);
		return correctFlight;
	}
	
}

public class FlightRequest {
	public static void main(String[] args) {
		AirportFlightFactory currentAirport = new AirportFlightFactory();
		Double averageTime = 0.0;
		for(int k=0; k<23; k++) {
			long startTime = System.currentTimeMillis();
			for(int m=0; m<100; m++) {
				Flight currentFlight = currentAirport.getMyFlight(getRandomMiles());
				currentFlight.startFlying();  
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Took Approximately " + (float)(endTime - startTime)/1000 + " Seconds!");
			averageTime += (float)(endTime - startTime)/1000;
		}
		averageTime /= 23;
		System.out.println("Average Time: " + averageTime + " Seconds!");
		
	}
	
	private static Double getRandomMiles() {
		Random currentRandomGenerator = new Random();
		return currentRandomGenerator.nextDouble() * 70000;
	}
}

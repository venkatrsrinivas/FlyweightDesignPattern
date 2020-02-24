package FlightDemo;

import java.util.HashMap;
import java.util.Random;

/*This is a Simplified Example About Flights + Planes.
Premise of Example:
Imagine you want to travel 6000 miles say to Italy, 
and your friend is a new owner of an Airport.
You tell your friend that you would like to go on
a flight that is 6000 miles and so your friend creates
an International Flight with a new International Plane,
and configures the Flight to last 6000 miles. 
You now go on your trip to Italy. 
When you come back, you now tell your friend that you want
to go on a trip for 6200 miles to Greece.
Rather than creating a whole new International Flight with a 
completely new International Plane, your friend gives you 
the same Flight with the Same Plane that you took to Italy,
but now simply configures the miles to be 6200. 
In this way, this Airport essentially only holds 3 Planes + 3 Flights.
However, this simplified model can be extended to reflect 
the true Flights and Planes that are present in an Airport
at a given time. 
This is the example of the Flyweight Pattern. 
That is, rather than creating a new Flight and Plane every time,
we intern the shareable resource, the Plane, and explictly represent
or configure the miles.
*/

//Holds All Information/Characteristics About Planes.
enum PlaneType {
	PRIVATE, DOMESTIC, INTERNATIONAL;
}

//This is the Interface For A Generic Flight.
interface Flight {
	public void setMiles(Double milesToTravel);
	public void startFlying();
}

//Case 1: InternationalFlight.
class InternationalFlight implements Flight {
	
	public InternationalFlight() {
		/*Setup For Flight Take-Off:
		This Random Code Is Present To Illustrate 
		The Effect of Invoking The Creation of a New Flight.
		*/
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
	
	//Private Fields:
	//Immutable Shared Intrinsic State:
	final PlaneType currentType = PlaneType.INTERNATIONAL;
	//Context-Dependent Extrinsic State:
	Double currentMiles = 0.0;
}

//Case 2: DomesticFlight.
class DomesticFlight implements Flight {
	
	public DomesticFlight() {
		/*Setup For Flight Take-Off:
		This Random Code Is Present To Illustrate 
		The Effect of Invoking The Creation of a New Flight.
		*/
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
	
	//Private Fields:
	//Immutable Shared Intrinsic State:
	final PlaneType currentType = PlaneType.DOMESTIC;
	//Context-Dependent Extrinsic State:
	Double currentMiles = 0.0;
}

//Case 3: Private Flight.
class PrivateFlight implements Flight {
	
	public PrivateFlight() {
		/*Setup For Flight Take-Off:
		This Random Code Is Present To Illustrate 
		The Effect of Invoking The Creation of a New Flight.
		*/
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
	
	//Private Fields:
	//Immutable Shared Intrinsic State:
	final PlaneType currentType = PlaneType.PRIVATE;
	//Context-Dependent Extrinsic State:
	Double currentMiles = 0.0;
}

//Class To Actually Get Flights:
class AirportFlightFactory {
	//Can Extend To Reflect The True Flights In An Actual Airport.
	//Right Now, Only 3 Flights, But In Reality Many More. 
	private static final HashMap<PlaneType, Flight> allFlightsMap = new HashMap<>();

	public static Flight getMyFlight(Double milesToTravel) {
		//Request Private Flight:
		if(milesToTravel >= 0 && milesToTravel <= 1500) {
			return getPrivateFlight(milesToTravel);
		}
		//Request Domestic Flight:
		else if(milesToTravel > 1500 && milesToTravel <= 5000) {
			return getDomesticFlight(milesToTravel);
		}
		//Request International Flight:
		else if(milesToTravel > 5000 && milesToTravel <= 10000) {
			return getInternationalFlight(milesToTravel);
		}
		//Invalid Number of Miles:
		//That is, no plane can travel this far by this definition.
		else{
			System.out.println("Invalid Number of Miles To Travel");
			return null;
		}		
	}
	
	//Return Private Flight:
	/*Note: 
	If we uncomment the commented code and comment the uncommented code 
	for all the functions below,
	then we can see that the run time of the program is much slower. 
	*/
	private static Flight getPrivateFlight(Double milesToTravel) {
		Flight correctFlight = allFlightsMap.get(PlaneType.PRIVATE);
		if(correctFlight == null) {
			correctFlight = new PrivateFlight();
			correctFlight.setMiles(milesToTravel);
			allFlightsMap.put(PlaneType.PRIVATE, correctFlight);
		}
		return correctFlight;

//		Flight correctFlight = new PrivateFlight();
//		correctFlight.setMiles(milesToTravel);
//		return correctFlight;
	}
	
	//Return Domestic Flight:
	/*Note: 
	If we uncomment the commented code and comment the uncommented code 
	for all the functions below,
	then we can see that the run time of the program is much slower. 
	*/
	private static Flight getDomesticFlight(Double milesToTravel) {
		Flight correctFlight = allFlightsMap.get(PlaneType.DOMESTIC);
		if(correctFlight == null) {
			correctFlight = new DomesticFlight();
			correctFlight.setMiles(milesToTravel);
			allFlightsMap.put(PlaneType.DOMESTIC, correctFlight);
		}
		return correctFlight;
		
//		Flight correctFlight = new DomesticFlight();
//		correctFlight.setMiles(milesToTravel);
//		return correctFlight;
	}
	
	//Return International Flight:
	/*Note: 
	If we uncomment the commented code and comment the uncommented code 
	for all the functions below,
	then we can see that the run time of the program is much slower. 
	*/
	private static Flight getInternationalFlight(Double milesToTravel) {
		Flight correctFlight = allFlightsMap.get(PlaneType.INTERNATIONAL);
		if(correctFlight == null) {
			correctFlight = new InternationalFlight();
			correctFlight.setMiles(milesToTravel);
			allFlightsMap.put(PlaneType.INTERNATIONAL, correctFlight);
		}
		return correctFlight;
		
//		Flight correctFlight = new InternationalFlight();
//		correctFlight.setMiles(milesToTravel);
//		return correctFlight;
	}
	
}

//Main Driver Class:
public class FlightRequest {
	public static void main(String[] args) {
		//Create Instance of AirportFlightFactory To Get Flights:
		AirportFlightFactory currentAirport = new AirportFlightFactory();
		Double averageTime = 0.0;
		//Run 23 Samples/Trials of 100 Flights Each:
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
		//Compute Correct Average For 23 Trials of 100 Each:
		averageTime /= 23;
		System.out.println("Average Time: " + averageTime + " Seconds!");
		
	}
	
	private static Double getRandomMiles() {
		Random currentRandomGenerator = new Random();
		return currentRandomGenerator.nextDouble() * 10000;
	}
}

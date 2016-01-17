package gps.locator.api.google;

import gps.locator.calc.Coordinate;
import gps.locator.calc.DegreeCoordinate;
import gps.locator.calc.EarthCalc;
import gps.locator.calc.Point;

public class Calculator {
	
	public static double calculate(Double lat1, Double log1, Double lat2, Double log2){
		
		System.out.println("entro aqui"+lat1+ "--" +log1 +  "------- " + lat2+  "--" +log2) ;
		
		Coordinate lat = new DegreeCoordinate(lat1);
		Coordinate lng = new DegreeCoordinate(log1);
		Point kew = new Point(lat, lng);

		//Richmond, London
		lat = new DegreeCoordinate(lat2);
		lng = new DegreeCoordinate(log2);
		Point richmond = new Point(lat, lng);

		double distance = EarthCalc.getDistance(richmond, kew); //in meter
		System.out.println(distance);
		return distance;
	}

}

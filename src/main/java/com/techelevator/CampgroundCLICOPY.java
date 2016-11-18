package com.techelevator;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;

public class CampgroundCLICOPY {

	private static final String MAIN_MENU_OPTION_VIEW_PARKS= "View National Parks";
	private static final String MAIN_MENU_OPTION_MAKE_RESERVATION = "Make a Reservation";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_VIEW_PARKS, 
																	 MAIN_MENU_OPTION_MAKE_RESERVATION,  
																	 MAIN_MENU_OPTION_EXIT };
	
	private static final String MAIN_MENU_OPTION_PARKS= "Select a Park";
	private static final String MAIN_MENU_OPTION_ACADIA = "Acadia";
	private static final String MAIN_MENU_OPTION_ARCHES = "Arches";
	private static final String MAIN_MENU_OPTION_CUYAHOGAVALLEY = "Cuyahoga Valley";
	private static final String MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS_PARKS = new String[] { MAIN_MENU_OPTION_ACADIA, 
																	 MAIN_MENU_OPTION_ARCHES, 
																	 MAIN_MENU_OPTION_CUYAHOGAVALLEY, 
																	 MAIN_MENU_OPTION_EXIT };

	
	
	
	private CampgroundMenu menu;
	private ParkDAO parkDAO;
	private CampgroundDAO campgroundDAO;
	private CampsiteDAO campsiteDAO;
	
	
	public static void main(String[] args) {
		CampgroundCLI application = new CampgroundCLI();
		application.run();	
	}
	
	public CampgroundCLICOPY() {
		this.menu = new CampgroundMenu(System.in, System.out);
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		parkDAO = new JDBCParkDAO(dataSource);
		campgroundDAO= new JDBCCampgroundDAO(dataSource);
		campsiteDAO= new JDBCCampsiteDAO(dataSource);
		
	}
	
	public void run() {
		displayApplicationTitle();
		printHeading("Main Menu");
		System.out.println("Please Make A Selection");
		String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
		if(choice.equals(MAIN_MENU_OPTION_VIEW_PARKS)) {
				parkDAO.listAllParks();
				handleChoiceOfPark();
		} else if(choice.equals(MAIN_MENU_OPTION_MAKE_RESERVATION)) {
			makeAreservation();
		}else{
			System.out.println(MAIN_MENU_OPTION_EXIT);;
		}

	}

	
	private void displayApplicationTitle(){
		System.out.println("Welcome to the National Parks Campsite Reservation Site"+"\n" );
		System.out.println("------------------------------------------"+"\n");
		System.out.println("\n"+ "Please Make A Selection");
		
	}
	private void listParks() {
		List<Park> allParks = parkDAO.listAllParks();
		listParkNames(allParks);
	}
	
	private void handleChoiceOfPark(){
		printHeading("Which Park would you like to learn more about?");
		String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTION_PARKS);
		if(choice.equals(MAIN_MENU_OPTION_ACADIA)) {
			getAcadiaParkInfo();
		} else if(choice.equals(MAIN_MENU_OPTION_ARCHES)) {
			getArchesParkInfo();
		} else if(choice.equals(MAIN_MENU_OPTION_CUYAHOGAVALLEY)) {
			getCuyahogaValleyInfo();
		} else if(choice.equals(MENU_OPTION_EXIT)) {
			//return to main menu;
		} else{
			System.out.println("Invalid Selection. Please Try Again");
		}
	}
	
	private void makeAreservation(){
		System.out.println("Which campground (Press 0 to exit)? ");
		System.out.println ("What is the arrival date? __/__/____");
		System.out.println ("What is the departure date? __/__/____");
	
	}
	
	private void getAcadiaParkInfo() {
		printHeading("Park Information - Learn More!!!!!");
		List<Park> miscPark = parkDAO.selectParkInfoByName("Acadia");
		
	}
	
	private void getArchesParkInfo() {
		printHeading("Park Information - Learn More!!!!!");
		List<Park> miscPark = parkDAO.selectParkInfoByName("Arches");
		System.out.println("Name : " + miscPark.get(0).getParkName()+"\n"+ "Location  : "+ miscPark.get(0).getParkLocation()+"\n" +"Established  :  "+miscPark.get(0).getEstablishedDate()+"\n"+"Area  : "+ miscPark.get(0).getSquareFootage()+"\n"+"Annual Visitors  : "+miscPark.get(0).getAnnualVisitorCount()+"\n"+"\n"+"\n"+miscPark.get(0).getParkDescription());

	}
	
	private void getCuyahogaValleyInfo() {
		printHeading("Park Information - Learn More!!!!!");
		List<Park> miscPark = parkDAO.selectParkInfoByName("Cuyahoga Valley");
		System.out.println("Name : " + miscPark.get(0).getParkName()+"\n"+ "Location  : "+ miscPark.get(0).getParkLocation()+"\n" +"Established  :  "+miscPark.get(0).getEstablishedDate()+"\n"+"Area  : "+ miscPark.get(0).getSquareFootage()+"\n"+"Annual Visitors  : "+miscPark.get(0).getAnnualVisitorCount()+"\n"+"\n"+"\n"+miscPark.get(0).getParkDescription());

	}
	
	private void printHeading(String headingText) {
		System.out.println("\n"+headingText);
		for(int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	private void listParkNames(List<Park> parks) {
		System.out.println();
		if (parks.size() > 0) {
			for (Park park : parks) {
				System.out.println(park.getParkName());
			}
		}
		else {
			System.out.println("\n***NO RESULTS***");
		}
	}
	
	private String getUserInput(String prompt) {
		System.out.print(prompt + " >>> ");
		return new Scanner(System.in).nextLine();
	}

	
}

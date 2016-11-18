package com.techelevator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.postgresql.jdbc3.Jdbc3ConnectionPool;
import org.springframework.jdbc.support.rowset.SqlRowSet;




public class CampgroundCLI {
	
	
	private static final String MAIN_MENU_OPTION_VIEW_LIST_PARKS= "View List of National Parks";
	private static final String MAIN_MENU_OPTION_MAKE_SELECT_PARK = "Please Select a Park";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_VIEW_LIST_PARKS, 
																	 MAIN_MENU_OPTION_MAKE_SELECT_PARK,  
																	 MAIN_MENU_OPTION_EXIT };
	
	private static final String MENU_OPTION_RETURN_TO_MAIN = "Return to Main Menu";
	
	
	private static final String MAIN_MENU_OPTION_VIEW_CAMPGROUNDS = "View Campgrounds";
	private static final String[] MENU_OPTIONS_CAMPGROUND_OPTIONS = new String[] {
																		   MAIN_MENU_OPTION_VIEW_CAMPGROUNDS,																															
																		   MENU_OPTION_RETURN_TO_MAIN};
	
	
	private static final String MENU_OPTION_CAMPGROUND_RESERVATION = "Please Make a Campsite Reservation";
	private static final String MENU_OPTION_SEARCH_AVAILABLE_RESERVATION = "Search for an Available Reservation";
	private static final String[] MENU_OPTIONS_RESERVATIONS = new String[] {
																	 MENU_OPTION_CAMPGROUND_RESERVATION,
																	 MENU_OPTION_SEARCH_AVAILABLE_RESERVATION,
																	 MENU_OPTION_RETURN_TO_MAIN};
	
	
	
	
	private CampgroundMenu menu;
	private ParkDAO parkDAO;
	private CampgroundDAO campgroundDAO;
	private CampsiteDAO campsiteDAO;
	
	
	public static void main(String[] args) {
		CampgroundCLI application = new CampgroundCLI();
		application.run();	
	}
	
	public CampgroundCLI() {
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
			System.out.println("");
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if(choice.equals(MAIN_MENU_OPTION_VIEW_LIST_PARKS)) {
				listParks();
				returnToMainMenu();
			} else if(choice.equals(MAIN_MENU_OPTION_MAKE_SELECT_PARK )) {
				listParks();
				searchParksByName();
				CampgroundMenu();
			} else {
				run();
			}
	}

	private void searchParksByName(){
		printHeading("Please Select A Park");
		String selectPark = getUserInput("Search for Park by Name");
		List<Park> parkInfo = parkDAO.selectParkInfoByName(selectPark);
		System.out.println("Name : " + parkInfo.get(0).getParkName()+"\n"+ "Location  : "+ parkInfo.get(0).getParkLocation()+"\n" +"Established  :  "+parkInfo.get(0).getEstablishedDate()+"\n"+"Area  : "+ parkInfo.get(0).getSquareFootage()+"\n"+"Annual Visitors  : "+parkInfo.get(0).getAnnualVisitorCount()+"\n"+"\n"+"\n"+parkInfo.get(0).getParkDescription());

	}
	private void CampgroundMenu(){
		printHeading("Please Make A Selection or Press 0 to return to Main Menu");
		String selection= (String)menu.getChoiceFromOptions(MENU_OPTIONS_CAMPGROUND_OPTIONS);
		if(selection.equals(MAIN_MENU_OPTION_VIEW_CAMPGROUNDS)) {
			listParks();
			listCampgrounds();
			returnToMainMenu();
		} else if(selection.equals("0")) {
			run();
		} else {
			System.out.println("Invalid Selction. Please Try Again");
			CampgroundMenu();
		}
	}
	private void handleCampgroundMenu() {
		while(true) {
			printFancyHeading("Select a Command");
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTION_CAMPGROUNDS);
			if(choice.equals(MAIN_MENU_OPTION_VIEW_CAMPGROUNDS)) {
				handleCampgroundList();
			}
			else if (choice.equals(MAIN_MENU_OPTION_SEARCH_RESERVATIONS)) {
				handleSearchReservation();
				handleCreateReservation(); 
			}
			else if (choice.equals(MAIN_MENU_OPTION_RETURN_FROM_CAMPGROUNDS)) {
				run();
			}
		}
	}
	
	private void handleCampgroundList() {
	    printFancyHeading("Enter Park Name to Display its Campgrounds (Enter 0 to Exit): ");
		String parkCampgrounds = getUserInput("");
		if(parkCampgrounds.equals("0")){
			//handleCampgroundMenu();
		}
		List<Campground> allCampgrounds = campgroundDAO.getCampgroundByParkName(parkCampgrounds);
		listCampgrounds(allCampgrounds);
		
	}
	
	private void printFancyHeading(String headingText) {
		System.out.println("\n"+headingText);
		for(int i = 0; i < headingText.length(); i++) {
			System.out.print("_");
		}
		System.out.println();
	}

	private void makeAReservation(){
		System.out.println("Which campground (Press 0 to exit)? ");
		System.out.println ("What is the arrival date? __/__/____");
		System.out.println ("What is the departure date? __/__/____");
		
	}
	private void returnToMainMenu(){
		System.out.println("\n"+"\n"+"Press any key to exit");
		String userAnswer= getUserInput("  ");
			if(userAnswer!=null){
				run();
			}
	}
	
	private void listParks() {
		List<Park> allParks = parkDAO.listAllParks();
		listParkNames(allParks);
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
	private void listCampgrounds(){
		printHeading("Select A Campground or Press 0 to return to Main Menu");
		String parkCampgrounds= getUserInput("");
		if(parkCampgrounds.equals("0")){
			run();
		}else{
			//somthing
		}
	}
	
	private void listCampgrounds(List<Campground> allCampgrounds) { //
		System.out.println();
		System.out.println("Name\tOpen\tClose\tDaily Fee");
		if (allCampgrounds.size() > 0) {
			for (Campground campground : allCampgrounds) {
				System.out.println(campground.getCampGroundName()+"\t"+campground.getOpenMonth()+"\t"+campground.getClosedMonth()+"\t$"+campground.getDailyFee()+"0");
			}
		}
		else {
			System.out.println("\n***NO RESULTS***");
			String input = getUserInput("Press 1 to try again OR 'Exit' to exit to main menu:").toUpperCase();
			if (input.equals("1")) {
				//handleCampgroundList();
			} else if (input.equals("Exit")) {
				run();
			} else {
				System.out.println("Please enter valid input.");
				//handleCampgroundList();
			}
		}
		
	}
	private void createReservation(){
		printHeading("Make a Reservation or Press 0 to Exit");
		String createReservation= getUserInput("Choose Campground where you want to create a reservation");
		String startingDate= getUserInput("What is the arrival date? (YYYY-MM-DD format");
		String endDate= getUserInput("What is the departure date? (YYYY-MM-DD format)");
		if(createReservation.equals("0")) {
			//handleCampgroundMenu();
		}
		
		
		
	}
//	private void listAvailableReservations(List<Reservation> rev){
//		System.out.println();
//		System.out.print("Site No. \t \t Max Occup. \t\t Accessible \t\t  Max RV Length \t\t Utility \t\t Cost");
//		if(rev.size() > 0){
//			for(Reservation reservation : rev){
//				System.out.println();
//				System.out.print(reservation.getSiteId() + "\t\t");
//				System.out.print(reservation.getOccupancy() + "\t\t");
//				System.out.print(reservation.getAccessible() + "\t\t");
//				System.out.print(reservation.getMaxRVLength() + "\t\t");
//				System.out.print(reservation.getUtilites() + "\t\t");
//				System.out.print("$"+reservation.getDailyFee()+"0\t\t");
//			}
//		}
//	}
	
	private void displayApplicationTitle(){
		System.out.println("Welcome to the National Parks Campsite Reservation Site"+"\n" );
		System.out.println("------------------------------------------"+"\n");
		System.out.println("\n"+ "Please Make A Selection");
		
	}
	private String getUserInput(String prompt) {
		System.out.print(prompt + " >>> ");
		return new Scanner(System.in).nextLine();
	}
	
	
	
	private void printHeading(String headingText) {
		System.out.println("\n"+headingText);
		for(int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}
}
		


	






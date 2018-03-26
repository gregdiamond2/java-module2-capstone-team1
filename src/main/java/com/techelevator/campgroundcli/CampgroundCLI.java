package com.techelevator.campgroundcli;

import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.Menu;
import com.techelevator.projects.model.Campground;
import com.techelevator.projects.model.Campsite;
import com.techelevator.projects.model.Park;
import com.techelevator.projects.model.Reservation;
import com.techelevator.projects.model.daos.CampgroundDAO;
import com.techelevator.projects.model.daos.CampsiteDAO;
import com.techelevator.projects.model.daos.ParkDAO;
import com.techelevator.projects.model.daos.ReservationDAO;
import com.techelevator.projects.model.jdbcs.CampgroundJdbcDAO;
import com.techelevator.projects.model.jdbcs.CampsiteJdbcDAO;
import com.techelevator.projects.model.jdbcs.ParkJdbcDAO;
import com.techelevator.projects.model.jdbcs.ReservationJdbcDAO;

public class CampgroundCLI {

	// Menu options
	private static final String PARK_MENU_OPTION_VIEW_CAMPGROUNDS = "View Campgrounds";
	private static final String PARK_MENU_OPTION_SEARCH_FOR_CAMPSITES = "Search for Available Campsites";
	private static final String PARK_OPTION_RETURN_TO_PREVIOUS_MENU = "Return to Previous Menu";
	private static final String[] PARK_MENU_OPTIONS = new String[] { PARK_MENU_OPTION_VIEW_CAMPGROUNDS,
			PARK_MENU_OPTION_SEARCH_FOR_CAMPSITES, PARK_OPTION_RETURN_TO_PREVIOUS_MENU };

	private static final String CAMPGROUND_MENU_OPTION_SEARCH_FOR_CAMPSITES = "Search for Available Campsites";
	private static final String CAMPGROUND_MENU_OPTION_RETURN_TO_PREVIOUS_MENU = "Return to Previous Menu";
	private static final String[] CAMPGROUND_MENU_OPTIONS = new String[] { CAMPGROUND_MENU_OPTION_SEARCH_FOR_CAMPSITES,
			CAMPGROUND_MENU_OPTION_RETURN_TO_PREVIOUS_MENU };

	// Initialize Menu variables for CLI
	private Menu menu;
	private static BasicDataSource dataSource;
	private boolean parkMenuOn = false;
	private boolean cgMenuOn = false;
	private boolean reservationMenuOn = false;
	private boolean getDateOn = false;
	private boolean checkDateOn = false;
	private boolean checkReservationsOn = false;

	// initialize UserReservation
	private Reservation userReservation = new Reservation();
	

	// initialize DAOs
	private ParkDAO ParkDAO;
	private CampgroundDAO campgroundDAO;
	private CampsiteDAO campsiteDAO;
	private ReservationDAO reservationDAO;

	public static void main(String[] args) {
		dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");

		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource datasource) {
		this.menu = new Menu(System.in, System.out);

		ParkDAO = new ParkJdbcDAO(dataSource);
		campgroundDAO = new CampgroundJdbcDAO(dataSource);
		campsiteDAO = new CampsiteJdbcDAO(dataSource);
		reservationDAO = new ReservationJdbcDAO(dataSource);

	}

	// RUN THE PROGRAM
	public void run() {
		displayApplicationBanner();

		while (true) {
			// DISPLAY PARK OPTIONS:
			handleParksChoiceList();
			parkMenuOn = true;
			handleParkMenu();
			handleCampgroundMenu();

			//Search for Available Reservation
			while (reservationMenuOn) {
				handleCampgroundChoiceList();

				handleUserDateSelection();
				while (checkDateOn) {
					checkReservationValidity();
					while (checkReservationsOn) {
						handleCampsiteChoiceList(); 
						if(checkReservationsOn == false) {
							break;
						} else {
						createAndConfirmReservation(); 
						}
					}

				}

			}
		}

	}

	

	// PARK DISPLAY METHODS
	private void handleParksChoiceList() {//Menu
		printHeading("Welcome to the National Parks Main Menu");
		List<Park> allParks = ParkDAO.getAllParks();
		if (allParks.size() > 0) {
			System.out.println("\n*** Select a Park for Further Details ***");
			Park choice = (Park) menu.getChoiceFromOptions(allParks.toArray());
			userReservation.setSelectedPark(choice);
			printHeading("\nPark Information");
			System.out.println(userReservation.getSelectedPark().createDisplayOfParkInfo());
		} else {
			System.out.println("\n*** No Parks Available ***");
		}
	}



	private void handleParkMenu() {//Menu
		while (parkMenuOn) {
			printHeading("What would you like to do?");
			String choice = (String) menu.getChoiceFromOptions(PARK_MENU_OPTIONS);
			if (choice.equals(PARK_MENU_OPTION_VIEW_CAMPGROUNDS)) {
				System.out.println(displayCampgroundInfo());
				cgMenuOn = true;
				parkMenuOn = false;
			} else if (choice.equals(PARK_MENU_OPTION_SEARCH_FOR_CAMPSITES)) {
				reservationMenuOn = true;

				parkMenuOn = false;
			} else if (choice.equals(PARK_OPTION_RETURN_TO_PREVIOUS_MENU)) {
				parkMenuOn = false;
			}
		}
	}

	// CAMPGROUND METHODS

	private String displayCampgroundInfo() {//Display
		Park selectedPark = userReservation.getSelectedPark();
		List<Campground> campgroundsInPark = campgroundDAO.getCampgroundsByParkId(selectedPark.getId());
		String display = selectedPark.createDisplayOfCampgroundsInPark(campgroundsInPark);
		return display;
	}

	private void handleCampgroundMenu() {//Menu
		while (cgMenuOn) {
			printHeading("What would you like to do?");
			String choice = (String) menu.getChoiceFromOptions(CAMPGROUND_MENU_OPTIONS);
			if (choice.equals(CAMPGROUND_MENU_OPTION_SEARCH_FOR_CAMPSITES)) {
				reservationMenuOn = true;

				cgMenuOn = false;
			} else if (choice.equals(CAMPGROUND_MENU_OPTION_RETURN_TO_PREVIOUS_MENU)) {
				cgMenuOn = false;
			}
		}
	}

	private void handleCampgroundChoiceList() {//Menu
		Park selectedPark = userReservation.getSelectedPark();
		printHeading("\nCampgrounds Located In " + selectedPark.getName() + " National Park");
		List<Campground> allCampgrounds = campgroundDAO.getCampgroundsByParkId(selectedPark.getId());
		if (!allCampgrounds.isEmpty()) {
			System.out.println("\n*** Which Campground Would You Like To Explore? ***");
			Campground choice = (Campground) menu.getChoiceFromOptions(allCampgrounds.toArray());
			userReservation.setSelectedCampground(choice);
			getDateOn = true;
		} else {
			System.out.println("\n***Sorry, no Campgrounds available in this Park.  ***");
			reservationMenuOn = false;
		}
	}

	// CAMPSITE RESERVATION METHODS

	private void handleCampsiteChoiceList() { //MENU 
		Campground selectedCampground = userReservation.getSelectedCampground();
		printHeading("Campsites Located At " + selectedCampground.getName() + " Campground");
		List<Campsite> allCampsites = campsiteDAO.getAvailableCampsites(userReservation, selectedCampground);
		if (!allCampsites.isEmpty()) {
			System.out.println("\n*** Which Campsite Would You Like To Reserve? ***");
			System.out.println("Site No.       Max Occup.   Accessible?    MaxR/vLength      Utility          Cost");
			Campsite choice = (Campsite) menu.getChoiceFromOptions(allCampsites.toArray());
			choice.setCost(userReservation);
			userReservation.setSelectedCampsite(choice);
			userReservation.setIdFKSite(userReservation.getSelectedCampsite().getId());
			getDateOn = true;
		} else {
			System.out.println("\n***Sorry, no Campsites available at this Campground.  ***");
			parkMenuOn = true;
			reservationMenuOn = false;
			checkDateOn = false;
			checkReservationsOn = false;
		}
	}

	private void handleUserDateSelection() {
		while (getDateOn) {
			setFromDate(); 
			if (getDateOn == false) {
				break;
			} else
				setToDate(); 
			if (getDateOn == false) {
				break;
			} else {
				checkDateOn = true;
				getDateOn = false;
			}
		}
	}

	private void setFromDate() { 
		String questionArrive = ("When do you plan to arrive? Please use format 'yyyy-mm-dd': "
				+ "\nOr enter 0 to go back to previous menu: ");
		userReservation.setFromDate(menu.getUserDateInput(questionArrive));
		if (userReservation.getFromDate().equals(LocalDate.parse("1000-01-01"))) {
			getDateOn = false;
		}
	}

	private void setToDate() { 
		String questionLeave = ("When will you be leaving? Please use format 'yyyy-mm-dd': "
				+ "\nOr enter 0 to go back to previous menu: ");
		userReservation.setToDate(menu.getUserDateInput(questionLeave));
		LocalDate toDate = userReservation.getToDate();
		LocalDate fromDate = userReservation.getFromDate();
		userReservation.setLengthOfStay();
		if (toDate.equals(LocalDate.parse("1000-01-01"))) {
			getDateOn = false;

		} else if (toDate.compareTo(fromDate) < 0) {
			System.out.println("\n*** Come on, Joe. " + toDate + " is before your arrival on " + fromDate
					+ ". Unless you are Benjamin Button, that date won't work.  Please enter another date:  ***\n");
			setToDate();

		}

	}
	
	private void checkReservationValidity() {
		if (reservationDAO.checkIfReservationValidByCampground(userReservation)) {
			checkReservationsOn = true;
		} else {
			System.out.println("Sorry, that Campground is not available during those dates.");
			parkMenuOn = true;
			checkDateOn = false;
			reservationMenuOn = false;
		}

	}
	
	private void createAndConfirmReservation() {
		userReservation.setNameRsrv(menu.getNameFromUserInput());
		Long reservationId = reservationDAO.createReservation(userReservation);
		System.out.println("The reservation has been made and the confirmation id is " + reservationId);
		checkReservationsOn = false;
		System.exit(0);

	}
	
	

	

	// DISPLAY METHODS
	private void printHeading(String headingText) {
		System.out.println("\n" + headingText);
		for (int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	private void displayApplicationBanner() {
		//System.out.println("YEAAAAHHHH!!!! IT'S PARK TIME WITH SMOKEY BEAR!!!");

		
		String aaa = "	____   ____.__                __________               __             .___        __                 _____                     ";
		String bbb = "	\\   \\ /   /|__| ______  _  __ \\______   \\_____ _______|  | __  ______ |   | _____/  |_  ____________/ ____\\____    ____  ____  ";
		String ccc = "	 \\   Y   / |  |/ __ \\ \\/ \\/ /  |     ___/\\__  \\\\_  __ \\  |/ / /  ___/ |   |/    \\   __\\/ __ \\_  __ \\   __\\\\__  \\ _/ ___\\/ __ \\ ";
		String ddd = "	  \\     /  |  \\  ___/\\     /   |    |     / __ \\|  | \\/    <  \\___ \\  |   |   |  \\  | \\  ___/|  | \\/|  |   / __ \\\\  \\__\\  ___/ ";
		String eee = "	   \\___/   |__|\\___  >\\/\\_/    |____|    (____  /__|  |__|_ \\/____  > |___|___|  /__|  \\___  >__|   |__|  (____  /\\___  >___  >";
		String fff = "	                   \\/                         \\/           \\/     \\/           \\/          \\/                  \\/     \\/    \\/ ";
		
		System.out.println(aaa);
		System.out.println(bbb);
		System.out.println(ccc);
		System.out.println(ddd);
		System.out.println(eee);
		System.out.println(fff);
	}
}

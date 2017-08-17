public class IsraelTour {
    // -------------------------------------------------------------------- Properties ---------------------------------------------------------------------------------------------
    private Trip[] _data; // the details of the trips the company offers
    private int _noOfTrips; // number of current scheduled trips in the company
    private final int MAX_TRIPS = 100;

    // -------------------------------------------------------------------- Constructors ---------------------------------------------------------------------------------------------
    /**
     * Initialize the object
     */
    public IsraelTour() {
        _data = new Trip[MAX_TRIPS];
        _noOfTrips = 0; // initialize the no of trips to the default value of no trips
    }

    // -------------------------------------------------------------------- Getters ---------------------------------------------------------------------------------------------
    /**
     * @return array of company trip data
     */
    public Trip[] getCompanyTripData() {
        return _data;
    }
    /**
     * @return the number of trips currently available
     */
    // Comment - name of method should be different  -3 points
    public int getNoOfTrips() {
        return _noOfTrips;
    }

    // -------------------------------------------------------------------- Setters ---------------------------------------------------------------------------------------------

    /**
     * @param trips Sets the companyTripData
     */
    public void setCompanyTripData(Trip[] trips) {
        _data = trips;
    }

    /**
     * @param noOfTrips Sets the number of trips in the company. currently void because there is no reason for any user to access this method
     */
    private void setNumberOfTrips(int noOfTrips) {
        _noOfTrips = noOfTrips;
    }

    // -------------------------------------------------------------------- Methods ---------------------------------------------------------------------------------------------
    /**
     * @param guideName
     * @param dayOfDeparture
     * @param monthOfDeparture
     * @param yearOfDeparture
     * @param dayOfReturn
     * @param monthOfReturn
     * @param yearOfReturn
     * @param noOfCountries
     * @param noOfTravellers
     * @return true if the trip was added successfully and false otherwise
     */
     public boolean addTrip(String guideName, int dayOfDeparture, int monthOfDeparture, int yearOfDeparture, int dayOfReturn, int monthOfReturn, int yearOfReturn, int noOfCountries, int noOfTravellers) {
         Trip trip;
         if (_noOfTrips < MAX_TRIPS) {
             trip = new Trip(guideName, dayOfDeparture, monthOfDeparture, yearOfDeparture, dayOfReturn, monthOfReturn, yearOfReturn, noOfCountries, noOfTravellers);
             if (trip.getDepartureDate().equals(new Date(dayOfDeparture,monthOfDeparture,yearOfDeparture)) && trip.getReturningDate().equals(new Date(dayOfReturn,monthOfReturn,yearOfReturn))) {
                 _data[_noOfTrips] = trip;
                 _noOfTrips ++; // Counts the number of trips this company have. as long as the method receive the right parameters then a trip will be created.
                 return true;
             }
         }
         return false;
     }
     /* Another optional addTrip method */
//     public boolean addTrip(Trip trip) {
//        if (_noOfTrips >= _data.length) {
//            return false;
//        }
//        _data [_noOfTrips] = new Trip(trip);
//        _noOfTrips++;
//        return true;
//    }

    /**
     * @param removeTrip
     * @return true if the method executed correctly and false otherwise
     * Only one trip can be removed at a time because each trip has a different ID.
     */
    public boolean removeTrip(Trip removeTrip) {
        Trip[] newArrayOfTrips = new Trip[MAX_TRIPS];
        int numOfTrips = 0;
        boolean isRemoved = false;
        if (getNoOfTrips() > 0) {
            for (int i = 0; i < getNoOfTrips(); i++) {
                if (!_data[i].equals(removeTrip)) {
                    newArrayOfTrips[numOfTrips] = _data[i];
                    numOfTrips ++;
                }
                else{
                    isRemoved = true;
                }
            }
        }
        // If one array element has been removed so both arrays won't be at the same length, which means that one Trip has been removed. otherwise nothing has been removed and
        // the method return false.
        if (isRemoved == true) {
            setCompanyTripData(newArrayOfTrips);
            setNumberOfTrips(getNoOfTrips() - 1);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param newTrip adds a new Trip object
     *                There is no need to check anything because all the tests were already solved inside the Trip Class
     */
    public void addExistingTrip(Trip newTrip) {
        _data[getNoOfTrips()] = newTrip;
        setNumberOfTrips(getNoOfTrips() + 1);
    }

    /**
     * @return the sum of all travellers in all the trips
     */
    public int howManyTravellers() {
        int howManyTravellers = 0;

        // Checks if there is at least one trip in the company
        if (getNoOfTrips() > 0) {
            for (int i = 0; i <= getNoOfTrips() - 1; i++) {
                howManyTravellers += getCompanyTripData()[i].getNoOfTravellers();
            }
        }
        return howManyTravellers;
    }

    /**
     * @param inputDate
     * @return how many trips are departure at the same date
     */
    public int howManyTripsDeparture(Date inputDate) {
        int howManyTrips = 0;
        Date departureDate;

        if (getNoOfTrips() > 0) {
            for (int i = 0; i < getNoOfTrips(); i++) {
                departureDate = _data[i].getDepartureDate();
                if (departureDate.equals(inputDate)) {
                    howManyTrips++;
                }
            }
        }
        return howManyTrips;
    }

    /**
     * @param inputDate
     * @return the amount of cars require for all trip that departure on the inputDate
     */
    public int howManyCars(Date inputDate) {
        int howManyCars = 0;
        Date departureDate;

        if (getNoOfTrips() > 0) {
            for (int i = 0; i < getNoOfTrips(); i++) {
                departureDate = _data[i].getDepartureDate();
                if (departureDate.equals(inputDate)) {
                    howManyCars = howManyCars + _data[i].howManyCars();
                }
            }
        }
        return howManyCars;
    }

    /**
     * @return the longest trip
     */
    public Trip longestTrip() {
        Trip longestTrip = null; // Default value is null to make sure the algorithm easier to understand
        if (getNoOfTrips() > 0) {
            for (int i = 0; i < getNoOfTrips(); i++) {
                if (longestTrip == null || longestTrip.tripDuration() < _data[i].tripDuration()) {
                    longestTrip = _data[i];
                }
            }
        }
        return new Trip(longestTrip);
    }

    /**
     * @return the most popular guide (the one that guides in most tours)
     */
    public String mostPopularGuide() {
        String tempGuide;
        String popularGuide = "";
        int numOfTempGuide;
        int numOfPopularGuide = 0;
        if (getNoOfTrips() > 0) {
            for (int i = 0; i < getNoOfTrips(); i++) {
                numOfTempGuide = 0;
                tempGuide = _data[i].getGuideName();
                for (int j = 0; j < getNoOfTrips(); j++) {
                    if (tempGuide.equals( _data[j].getGuideName())) {
                        numOfTempGuide++;
                    }
                }
                if (numOfPopularGuide < numOfTempGuide) {
                    popularGuide = tempGuide;
                    numOfPopularGuide = numOfTempGuide;
                }
            }
            return popularGuide;
        }
        else return null;
    }

    /**
     * @return the earliest trip in Date format. if there is no trip
     */
    public Date earliestTrip() {
        if(getNoOfTrips() > 0){
        Date earliestTripDate = _data[0].getDepartureDate();
        Date checkThisDate;
            for (int i = 0; i < getNoOfTrips(); i++) {
                checkThisDate = _data[i].getDepartureDate();
                if (earliestTripDate.after(checkThisDate)) {
                    earliestTripDate = checkThisDate;
                }
            }
            return new Date(earliestTripDate);
        }
        // If there are no trips in the tour then null is returned
        else {
            return null;
        }
    }

    /**
     * @return the most expensive trip
     */
    public Trip mostExpensiveTrip() {
        Trip mostExpensiveTrip = null;
        if (getNoOfTrips() > 0) {
            mostExpensiveTrip = _data[0];
            for (int i = 0; i < getNoOfTrips(); i++) {
                if (mostExpensiveTrip.calculatePrice() < _data[i].calculatePrice()) {
                    mostExpensiveTrip = _data[i];
                }
            }
        }
        return new Trip(mostExpensiveTrip);
    }
}

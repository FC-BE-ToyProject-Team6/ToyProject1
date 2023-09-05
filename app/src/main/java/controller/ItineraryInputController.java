package controller;

import model.Itinerary;

import model.dao.TripDAO;
import view.ItineraryInputView;

import java.io.IOException;

public class ItineraryInputController {
    private TripDAO tripDAO;
    private ItineraryInputView itineraryInputView;

    public ItineraryInputController() {
        this.tripDAO = tripDAO;
        this.itineraryInputView = new ItineraryInputView();
    }

    public void run(int tripId) throws IOException {
        do {
            Itinerary itinerary = itineraryInputView.run();
            tripDAO.insertItinerary(tripId, itinerary);
        } while(itineraryInputView.wantToContinue());

        /**
         * view는 입출력만 함.
         * getInput,wantToContinue => Controller나 DAO에서 구현해야 됨.
         */
    }

    public void addItinerary(Itinerary itinerary, int tripID) {
    }
}

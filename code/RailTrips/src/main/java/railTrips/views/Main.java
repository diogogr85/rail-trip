package railTrips.views;

import railTrips.data.contracts.FunctionsContract;
import railTrips.data.presenters.FunctionsPresenter;
import railTrips.utils.Constants;

import java.io.IOException;

public class Main {

    private static final String INPUT_FILE_DATA = "";
    private static int outputIndex;

    public static void main(String[] args) {
        System.out.println("Rail trips");
        System.out.println();

//        FileManager.newInstance().prepareData(INPUT_FILE_DATA);
        String data = "";
        int ch;
        try {
            System.out.println("Write here your data input: (i.e. ab4, cd8, ea6)");
            while ((ch = System.in.read ()) != '\n') {
                data += (char) ch;
            }
            System.out.println();

            final FunctionsPresenter presenter = new FunctionsPresenter(data.toUpperCase().trim());
            presenter.bindView(sOnFunctionsOutputSuccess);

            //Problem #1
            outputIndex++;
            presenter.calculateRouteDistance("A-B-C");

            //Problem #2
            outputIndex++;
            presenter.calculateRouteDistance("A-D");

            //Problem #3
            outputIndex++;
            presenter.calculateRouteDistance("A-D-C");

            //Problem #4
            outputIndex++;
            presenter.calculateRouteDistance("A-E-B-C-D");

            //Problem #5
            outputIndex++;
            presenter.calculateRouteDistance("A-E-D");

            //Problem #6
            outputIndex++;
            presenter.numberOfTrips("C", "C", 3);

            //Problem #7
            outputIndex++;
            presenter.countTrips("A", "C", 4);

            //Problem #8
            outputIndex++;
            presenter.findShortestRouteLength("A", "C");

            //Problem #9
            outputIndex++;
            presenter.findShortestRouteLength("B", "B");

            //Problem #10
            outputIndex++;
            presenter.differentRoutes("C", "C", 30);

            System.out.println("* For output details/explanations check out output_description.txt file in resources package");
            presenter.unbindView();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Input graph malformed");
        } catch (NumberFormatException e) {
            System.out.println("Input graph malformed");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Input graph malformed");
        }
    }

    private static final FunctionsContract.View sOnFunctionsOutputSuccess = new FunctionsContract.View() {
        @Override
        public void onOutputSuccess(String output) {
            System.out.println(String.format(Constants.OUTPUT_ANWSER_PREFIX, outputIndex, output));
        }
    };

}

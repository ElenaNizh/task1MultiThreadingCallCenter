public class Calls {

    private final int callsNumber;


    public Calls(int callsNumber) {

        this.callsNumber = callsNumber;
    }

    @Override
    public String toString() {
        return "звонок номер " + callsNumber;
    }
}
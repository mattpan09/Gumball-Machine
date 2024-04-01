package edu.iu.habahram.GumballMachine.model;

public class GumballMachine implements IGumballMachine {
    final String SOLD_OUT = GumballMachineState.OUT_OF_GUMBALLS.name();
    final String NO_QUARTER = GumballMachineState.NO_QUARTER.name();
    final String HAS_QUARTER = GumballMachineState.HAS_QUARTER.name();
    final String SOLD = GumballMachineState.GUMBALL_SOLD.name();
    private String id;
    String state = SOLD_OUT;
    int count = 0;

    public GumballMachine(String id, String state, int count) {
        this.id = id;
        this.state = state;
        this.count = count;
    }

    @Override
    public TransitionResult insertQuarter() {
        boolean succeeded = false;
        String message = "";
        if (state.equalsIgnoreCase(HAS_QUARTER)) {
            message = "You can't insert another quarter";
        } else if (state.equalsIgnoreCase(NO_QUARTER)) {
            state = HAS_QUARTER;
            message = "You inserted a quarter";
            succeeded = true;
        } else if (state.equalsIgnoreCase(SOLD_OUT)) {
            message = "You can't insert a quarter, the machine is sold out";
        } else if (state.equalsIgnoreCase(SOLD)) {
            message = "Please wait, we're already giving you a gumball";
        }
        return new TransitionResult(succeeded, message, state, count);
    }

    @Override
    public TransitionResult ejectQuarter() {

        boolean succeeded = false;
        String message = "";
        if (state.equalsIgnoreCase(HAS_QUARTER)) {
            state = NO_QUARTER;
            message = "Quarter returned";
            succeeded = true;
        } else if (state.equalsIgnoreCase(NO_QUARTER)) {
            message = "You haven't inserted a quarter";
        } else if (state.equalsIgnoreCase(SOLD_OUT)) {
            message = "You can't eject, you haven't inserted a quarter yet";
        } else if (state.equalsIgnoreCase(SOLD)) {
            message = "Sorry, you already turned the crank";
        }
        return new TransitionResult(succeeded, message, state, count);


    }

    @Override
    public TransitionResult turnCrank() {
        boolean succeeded = false;
        String message = "";
        if (state.equalsIgnoreCase(HAS_QUARTER)) {
            state = SOLD;

            message = "You turned...";
            succeeded = true;
            return dispense();
        } else if (state.equalsIgnoreCase(NO_QUARTER)) {
            message = "You turned but there's no quarter";
        } else if (state.equalsIgnoreCase(SOLD_OUT)) {
            message = "You turned, but there are no gumballs";
        } else if (state.equalsIgnoreCase(SOLD)) {
            message = "Turning twice doesn't get you another gumball!";
        }


        return new TransitionResult(succeeded, message, state, count);
    }


    public TransitionResult dispense() {
        if (state.equalsIgnoreCase(SOLD)) {
            System.out.println("A gumball comes rolling out the slot...");
            count = count - 1;
            if (count == 0) {
                System.out.println("Oops, out of gumballs!");
                state = SOLD_OUT;
            } else {
                state = NO_QUARTER;
            }
        } else if (state.equalsIgnoreCase(NO_QUARTER)) {
            System.out.println("You need to pay first");
        } else if (state.equalsIgnoreCase(SOLD_OUT)) {
            System.out.println("No gumball dispensed");
        } else if (state.equalsIgnoreCase(HAS_QUARTER)) {
            System.out.println("No gumball dispensed");
        }

        return new TransitionResult(true, "Gumball dispensed", state, count);
    }

    @Override
    public void changeTheStateTo(GumballMachineState name) {

    }

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public String getTheStateName() {
        return null;
    }

    @Override
    public void releaseBall() {

    }


}

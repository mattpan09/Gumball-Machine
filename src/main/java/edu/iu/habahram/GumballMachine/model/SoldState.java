package edu.iu.habahram.GumballMachine.model;

public class SoldState implements IState {

    IGumballMachine gumballMachine;

    public SoldState(IGumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public TransitionResult insertQuarter() {
//        return new TransitionResult(false, "Please wait, we're already giving you a gumball", gumballMachine.getTheStateName(), gumballMachine.getCount());
        String message = "Please wait, we're already giving you a gumball";
        boolean succeeded = false;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public TransitionResult ejectQuarter() {
//        return new TransitionResult(false, "Sorry, you already turned the crank", gumballMachine.getTheStateName(), gumballMachine.getCount());
        String message = "Sorry, you already turned the crank";
        boolean succeeded = false;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public TransitionResult turnCrank() {
//        return new TransitionResult(false, "Turning twice doesn't get you another gumball!", gumballMachine.getTheStateName(), gumballMachine.getCount());
        String message = "Turning twice doesn't get you another gumball!";
        boolean succeeded = false;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }


    @Override
    public TransitionResult dispense() {
        String message = "A gumball comes rolling out the slot...";
        boolean succeeded = true;
//        gumballMachine.setCount(gumballMachine.getCount() - 1);
        int count = gumballMachine.getCount() - 1;
        if (count == 0) {
            message = "Oops, out of gumballs!";
            gumballMachine.changeTheStateTo(GumballMachineState.OUT_OF_GUMBALLS);
        }
        else {
            gumballMachine.changeTheStateTo(GumballMachineState.NO_QUARTER);
        }

        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), count);
    }

    @Override
    public String getTheName() {
        return GumballMachineState.GUMBALL_SOLD.name();
    }
}

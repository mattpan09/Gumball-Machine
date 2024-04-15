package edu.iu.habahram.GumballMachine.model;

public class HasQuarterState implements IState {
    IGumballMachine gumballMachine;
    IState soldState;

    public HasQuarterState(IGumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
        soldState = new SoldState(gumballMachine);
    }

    @Override
    public TransitionResult insertQuarter() {
        String message = "You can't insert another quarter";
        boolean succeeded = false;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), count);
    }

    @Override
    public TransitionResult ejectQuarter() {
        gumballMachine.changeTheStateTo(GumballMachineState.NO_QUARTER);
        boolean succeeded = true;
        String message = "Quarter returned";
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), count);
    }

    @Override
    public TransitionResult turnCrank() {
        gumballMachine.changeTheStateTo(GumballMachineState.GUMBALL_SOLD);
        boolean succeeded = true;
        String message = "You turned...";
        int count = gumballMachine.getCount();

        //Call the dispense method to dispense the gumball
        return soldState.dispense();
//        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), count);
    }

    @Override
    public TransitionResult dispense() {
//        return new TransitionResult("No gumball dispensed");
        String message = "No gumball dispensed";
        boolean succeeded = false;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), count);
    }

    @Override
    public String getTheName() {
        return GumballMachineState.HAS_QUARTER.name();
    }
}

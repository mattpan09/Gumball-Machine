package edu.iu.habahram.GumballMachine.model;

public class HasQuarterState implements IState {
    IGumballMachine gumballMachine;

    public HasQuarterState(IGumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
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
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), count);
    }

    @Override
    public TransitionResult dispense() {
        return new TransitionResult("No gumball dispensed");
    }

    @Override
    public String getTheName() {
        return GumballMachineState.HAS_QUARTER.name();
    }
}

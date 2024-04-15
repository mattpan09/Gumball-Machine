package edu.iu.habahram.GumballMachine.service;

import edu.iu.habahram.GumballMachine.model.*;
import edu.iu.habahram.GumballMachine.repository.IGumballRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GumballService implements IGumballService{
    final String INSERT_QUARTER = Transition.INSERT_QUARTER.name();
    final String EJECT_QUARTER = Transition.EJECT_QUARTER.name();
    final String TURN_CRANK = Transition.TURN_CRANK.name();
    final String REFILL = Transition.REFILL.name();

    IGumballRepository gumballRepository;

    public GumballService(IGumballRepository gumballRepository) {
        this.gumballRepository = gumballRepository;
    }

    @Override
    public TransitionResult insertQuarter(String id) throws IOException {
        return transition(id, INSERT_QUARTER);
    }

    @Override
    public TransitionResult ejectQuarter(String id) throws IOException {
        return transition(id, EJECT_QUARTER);
    }

    @Override
    public TransitionResult turnCrank(String id) throws IOException {
        return transition(id, TURN_CRANK);
    }

    //Refactor insertQuarter, ejectQuarter, and turnCrank methods into a single method called transition
    private TransitionResult transition(String id, String transition) throws IOException {
//        GumballMachineRecord record = gumballRepository.findById(id);
//        IGumballMachine machine = new GumballMachine(record.getId(), record.getState(), record.getCount());
//        TransitionResult result = null;
//        if(transition.equals("insertQuarter")) {
//            result = machine.insertQuarter();
//        } else if(transition.equals("ejectQuarter")) {
//            result = machine.ejectQuarter();
//        } else if(transition.equals("turnCrank")) {
//            result = machine.turnCrank();
//        }
//        if(result.succeeded()) {
//            record.setState(result.stateAfter());
//            record.setCount(result.countAfter());
//            save(record);
//        }
//        return result;


        //Using GumballMachine2 class instead of GumballMachine class
        GumballMachineRecord record = gumballRepository.findById(id);
        IGumballMachine machine = new GumballMachine2(record.getId(), record.getState(), record.getCount());
        TransitionResult result = null;
        if (transition.equalsIgnoreCase(INSERT_QUARTER)) {
            result = machine.insertQuarter();
        } else if(transition.equalsIgnoreCase(EJECT_QUARTER)) {
            result = machine.ejectQuarter();
        } else if(transition.equalsIgnoreCase(TURN_CRANK)) {
            result = machine.turnCrank();
        }
        if( result.succeeded() ) {
            record.setState(result.stateAfter());
            record.setCount(result.countAfter());
            save(record);
        }
        return result;
    }

    

    @Override
    public List<GumballMachineRecord> findAll() throws IOException {
        return gumballRepository.findAll();
    }

    @Override
    public GumballMachineRecord findById(String id) throws IOException {
        return gumballRepository.findById(id);
    }

    @Override
    public String save(GumballMachineRecord gumballMachineRecord) throws IOException {
        return gumballRepository.save(gumballMachineRecord);
    }

    @Override
    public TransitionResult refill(String id, int gumballsToAdd) throws IOException {
        return transit(id, REFILL, gumballsToAdd);
    }

    private TransitionResult transit(String id, String transition, int gumballsToAdd) throws IOException {
        GumballMachineRecord record = gumballRepository.findById(id);
        IGumballMachine machine = new GumballMachine2(record.getId(), record.getState(), record.getCount());
        TransitionResult result = null;
        if (transition.equalsIgnoreCase(REFILL)) {
            result = machine.refill(gumballsToAdd);
        }
        if( result.succeeded() ) {
            record.setState(result.stateAfter());
            record.setCount(result.countAfter());
            save(record);
        }
        return result;
    }
}

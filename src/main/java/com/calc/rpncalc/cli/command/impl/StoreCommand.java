package com.calc.rpncalc.cli.command.impl;

import com.calc.rpncalc.cli.command.RPNCalculatorCommand;
import com.calc.rpncalc.cli.receiver.CommandReceiver;
import com.calc.rpncalc.cli.receiver.factory.CommandReceiverFactory;
import com.calc.rpncalc.cli.exception.RPNCalculatorException;

import java.math.BigDecimal;

/**
 * Created by sundas on 4/16/2017.
 */
public class StoreCommand implements RPNCalculatorCommand {

  private BigDecimal value;

  @Override
  public void execute() throws RPNCalculatorException {
    if(value != null) {
      CommandReceiver commandReceiver = CommandReceiverFactory.getDefaultReceiver();
      commandReceiver.addElement(value);
    }
  }

  @Override
  public void setState(Object state) {
    if(state instanceof String){
      value = new BigDecimal((String)state).setScale(15,BigDecimal.ROUND_HALF_UP);
    }
  }

  @Override
  public void undo() {
    if(value != null){
      CommandReceiver commandReceiver = CommandReceiverFactory.getDefaultReceiver();
      commandReceiver.fetchElement();
    }
  }
}

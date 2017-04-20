package com.calc.rpncalc.cli.command.impl;

import com.calc.rpncalc.cli.receiver.CommandReceiver;
import com.calc.rpncalc.cli.command.RPNCalculatorCommand;
import com.calc.rpncalc.cli.exception.RPNCalculatorException;
import com.calc.rpncalc.cli.receiver.factory.CommandReceiverFactory;

/**
 * Created by sundas on 4/16/2017.
 */
public class PrintStateCommand implements RPNCalculatorCommand {
  @Override
  public void execute() throws RPNCalculatorException {
    CommandReceiver commandReceiver = CommandReceiverFactory.getDefaultReceiver();
    if(commandReceiver != null){
      commandReceiver.printState();
    }
  }

  @Override
  public void setState(Object state) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void undo() {
    throw new UnsupportedOperationException();
  }
}

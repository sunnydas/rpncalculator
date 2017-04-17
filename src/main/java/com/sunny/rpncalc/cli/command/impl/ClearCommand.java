package com.sunny.rpncalc.cli.command.impl;

import com.sunny.rpncalc.cli.command.RPNCalculatorCommand;
import com.sunny.rpncalc.cli.receiver.CommandReceiver;
import com.sunny.rpncalc.cli.receiver.factory.CommandReceiverFactory;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Created by sundas on 4/15/2017.
 */
public class ClearCommand implements RPNCalculatorCommand {
  @Override
  public void execute() {
    CommandReceiver<BigDecimal,Stack> commandReceiver = CommandReceiverFactory.getDefaultReceiver();
    if(commandReceiver != null){
      commandReceiver.clearUnderlyingState();
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

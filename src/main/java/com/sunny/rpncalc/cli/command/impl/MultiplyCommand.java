package com.sunny.rpncalc.cli.command.impl;

import com.sunny.rpncalc.cli.command.RPNCalculatorCommand;
import com.sunny.rpncalc.cli.controller.util.RPNCalcUtil;
import com.sunny.rpncalc.cli.exception.RPNCalculatorException;
import com.sunny.rpncalc.cli.receiver.CommandReceiver;
import com.sunny.rpncalc.cli.receiver.factory.CommandReceiverFactory;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Created by sundas on 4/15/2017.
 */
public class MultiplyCommand implements RPNCalculatorCommand {

  private BigDecimal operand1;

  private BigDecimal operand2;

  @Override
  public void execute() throws RPNCalculatorException{
    CommandReceiver<BigDecimal,Stack> commandReceiver = CommandReceiverFactory.getDefaultReceiver();
    BigDecimal mult = null;
    if(commandReceiver != null){
      if(commandReceiver.getInternaDSSize() >= 2) {
        operand1 = commandReceiver.fetchElement();
        if (operand1 != null) {
          operand2 = commandReceiver.fetchElement();
          if (operand2 != null) {
            mult = operand1.multiply(operand2).setScale(15, BigDecimal.ROUND_HALF_UP);
          } else {
            throw RPNCalcUtil.createIvalidOperandsError();
          }
        } else {
          throw RPNCalcUtil.createIvalidOperandsError();
        }
        if (mult != null) {
          commandReceiver.addElement(mult);
        }
      }
      else{
        throw RPNCalcUtil.createInsuffParamOnStackError();
      }
    }

  }

  @Override
  public void setState(Object state) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void undo() {
    if(operand1 != null && operand2 != null) {
      RPNCalcUtil.undoOperationDefault(operand1, operand2);
    }
  }
}

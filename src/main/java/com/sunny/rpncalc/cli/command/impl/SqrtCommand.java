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
public class SqrtCommand implements RPNCalculatorCommand{

  private BigDecimal operand1;

  @Override
  public void undo() {

  }

  @Override
  public void execute() throws RPNCalculatorException{
    CommandReceiver<BigDecimal,Stack> commandReceiver = CommandReceiverFactory.getDefaultReceiver();
    BigDecimal sqrt = null;
    if(commandReceiver != null){
      if(commandReceiver.getInternaDSSize() >= 1) {
        operand1 = commandReceiver.fetchElement();
        if (operand1 != null) {
          BigDecimal x = new BigDecimal(Math.sqrt(operand1.doubleValue()));
          sqrt = x.add(new BigDecimal(operand1.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
        } else {
          throw RPNCalcUtil.createIvalidOperandsError();
        }
        if (sqrt != null) {
          commandReceiver.addElement(sqrt.setScale(15,BigDecimal.ROUND_HALF_UP));
        }
      }
      else{
        throw RPNCalcUtil.createInsuffParamOnStackError();
      }
    }
  }

  @Override
  public void setState(Object state) {
    if(operand1 != null) {
      RPNCalcUtil.undoOperationDefault(operand1);
    }
  }
}

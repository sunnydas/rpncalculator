package com.sunny.rpncalc.cli.controller.util;

import com.sunny.rpncalc.cli.exception.RPNCalcError;
import com.sunny.rpncalc.cli.exception.RPNCalculatorException;
import com.sunny.rpncalc.cli.receiver.CommandReceiver;
import com.sunny.rpncalc.cli.receiver.factory.CommandReceiverFactory;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Created by sundas on 4/16/2017.
 */
public class RPNCalcUtil {

  private RPNCalcUtil(){

  }

  public static boolean isNumeric(String str)
  {
    return str.matches("-?\\d+(\\.\\d+)?");
  }

  public static void undoOperationDefault(BigDecimal ... operands){
    CommandReceiver<BigDecimal, Stack> commandReceiver = CommandReceiverFactory.getDefaultReceiver();
    commandReceiver.fetchElement();
    for(int i = operands.length - 1 ; i >= 0 ; i--){
      commandReceiver.addElement(operands[i]);
    }
  }

  public static RPNCalculatorException createInsuffParamOnStackError(){
    RPNCalculatorException rpnCalculatorException = new RPNCalculatorException();
    rpnCalculatorException.setStatusCode(RPNCalcError.INSUFFICIENT_PARAMETERS);
    return rpnCalculatorException;
  }

  public static RPNCalculatorException createIvalidOperandsError(){
    RPNCalculatorException rpnCalculatorException = new RPNCalculatorException();
    rpnCalculatorException.setStatusCode(RPNCalcError.INVALID_OPERANDS);
    return rpnCalculatorException;
  }


  /**
   *
   * @param row
   * @param indexOfOperand
   * @return
   */
  public static int findPositionOfOperand(String row, int indexOfOperand){
    int actualIndex = 0;
    int operandIndex = 0;
    for(int i = 0 ; i < row.length() ; i++){
      if(operandIndex == indexOfOperand){
        break;
      }
      if(row.charAt(i) == ' '){
        actualIndex++;
      }
      else{
        operandIndex++;
        actualIndex++;
      }
    }
    return actualIndex + 2;
  }


}

package com.calc.rpncalc.cli.controller.util;

import com.calc.rpncalc.cli.receiver.CommandReceiver;
import com.calc.rpncalc.cli.exception.RPNCalcError;
import com.calc.rpncalc.cli.exception.RPNCalculatorException;
import com.calc.rpncalc.cli.receiver.factory.CommandReceiverFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.math.BigDecimal;
import java.util.Properties;
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
      commandReceiver.addElement(operands[i].setScale(15,BigDecimal.ROUND_HALF_UP));
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

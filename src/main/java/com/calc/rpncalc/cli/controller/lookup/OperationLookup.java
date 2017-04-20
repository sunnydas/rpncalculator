package com.calc.rpncalc.cli.controller.lookup;

import com.calc.rpncalc.cli.command.impl.*;
import com.calc.rpncalc.cli.command.RPNCalculatorCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sundas on 4/15/2017.
 */
public class OperationLookup {

  /**
   *
   */
  private static Map<String,RPNCalculatorCommand> lookupMap;

  /**
   *
   */
  private OperationLookup(){

  }


  /**
   * Works like simple factory
   *
   *
   * @param rawOp
   * @return
   */
  public static RPNCalculatorCommand getOperation(String rawOp){
    RPNCalculatorCommand rpnCalculatorCommand = null;
    if(lookupMap == null){
      lookupMap = new HashMap<>();
    }
    if(lookupMap.containsKey(rawOp)){
      rpnCalculatorCommand = lookupMap.get(rawOp);
    }
    else{
      switch(rawOp){
        case "+":
          rpnCalculatorCommand = new AddCommand();
          break;
        case "-":
          rpnCalculatorCommand = new MinusCommand();
          break;
        case "*":
          rpnCalculatorCommand = new MultiplyCommand();
          break;
        case "/":
          rpnCalculatorCommand = new DivideCommand();
          break;
        case "SQRT":
          rpnCalculatorCommand = new SqrtCommand();
          break;
        case "CLEAR":
          rpnCalculatorCommand = new ClearCommand();
          break;
      }
      if(rpnCalculatorCommand != null){
        lookupMap.put(rawOp,rpnCalculatorCommand);
      }
    }
    return rpnCalculatorCommand;
  }


  /**
   * Insurance for programmatic extensions
   *
   */
  public static void updateOperationsMap(String operation,RPNCalculatorCommand command){
    if(lookupMap == null){
      lookupMap = new HashMap<>();
    }
    lookupMap.put(operation,command);
  }


}

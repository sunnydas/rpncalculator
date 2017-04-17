package com.sunny.rpncalc.cli.command.invoker.impl;

import com.sunny.rpncalc.cli.RPNCalculator;
import com.sunny.rpncalc.cli.command.RPNCalculatorCommand;
import com.sunny.rpncalc.cli.command.invoker.CommandInvoker;
import com.sunny.rpncalc.cli.exception.RPNCalculatorException;

/**
 * Created by sundas on 4/15/2017.
 */
public class CommandCentral implements CommandInvoker{

  private RPNCalculatorCommand rpnCalculatorCommand;


  @Override
  public void setCommand(RPNCalculatorCommand command) {
    this.rpnCalculatorCommand = command;
  }

  @Override
  public void executeCommand() throws RPNCalculatorException{
    if(rpnCalculatorCommand != null){
      rpnCalculatorCommand.execute();
    }
  }

}

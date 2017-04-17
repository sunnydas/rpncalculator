package com.sunny.rpncalc.cli.command.invoker;

import com.sunny.rpncalc.cli.command.RPNCalculatorCommand;
import com.sunny.rpncalc.cli.exception.RPNCalculatorException;

/**
 * Created by sundas on 4/15/2017.
 */
public interface CommandInvoker<P> {

  public void setCommand(RPNCalculatorCommand command);

  public void executeCommand() throws RPNCalculatorException;
}

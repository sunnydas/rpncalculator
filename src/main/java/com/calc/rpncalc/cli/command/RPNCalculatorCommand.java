package com.calc.rpncalc.cli.command;

import com.calc.rpncalc.cli.exception.RPNCalculatorException;

/**
 * Created by sundas on 4/15/2017.
 */
public interface RPNCalculatorCommand<S> {


  /**
   *
   */
  public void execute() throws RPNCalculatorException;


  public void setState(S state);


  public void undo();


}

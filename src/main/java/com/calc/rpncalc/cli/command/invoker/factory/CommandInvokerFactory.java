package com.calc.rpncalc.cli.command.invoker.factory;

import com.calc.rpncalc.cli.command.invoker.CommandInvoker;
import com.calc.rpncalc.cli.command.invoker.impl.DefaultCommandInvoker;

/**
 * Created by sundas on 4/16/2017.
 */
public class CommandInvokerFactory {

  private CommandInvokerFactory(){

  }

  public static CommandInvoker getDefaultInvoker(){
    return new DefaultCommandInvoker();
  }


}

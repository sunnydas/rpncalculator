package com.sunny.rpncalc.cli.command.invoker.factory;

import com.sunny.rpncalc.cli.command.invoker.CommandInvoker;
import com.sunny.rpncalc.cli.command.invoker.impl.CommandCentral;

/**
 * Created by sundas on 4/16/2017.
 */
public class CommandInvokerFactory {

  private CommandInvokerFactory(){

  }

  public static CommandInvoker getDefaultInvoker(){
    return new CommandCentral();
  }


}

package com.calc.rpncalc.cli.receiver.factory;

import com.calc.rpncalc.cli.receiver.CommandReceiver;
import com.calc.rpncalc.cli.receiver.impl.StackBasedCommandReceiver;

/**
 * Created by sundas on 4/16/2017.
 */
public class CommandReceiverFactory {

  private CommandReceiverFactory(){

  }

  /**
   *
   * @return
   */
  public static CommandReceiver getDefaultReceiver(){
    return StackBasedCommandReceiver.getReceiver();
  }


}

package com.sunny.rpncalc.cli.receiver.factory;

import com.sunny.rpncalc.cli.receiver.CommandReceiver;
import com.sunny.rpncalc.cli.receiver.impl.StackBasedCommandReceiver;

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

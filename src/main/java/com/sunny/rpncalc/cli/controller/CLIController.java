package com.sunny.rpncalc.cli.controller;

import com.sunny.rpncalc.cli.command.RPNCalculatorCommand;
import com.sunny.rpncalc.cli.command.impl.PrintStateCommand;
import com.sunny.rpncalc.cli.command.impl.StoreCommand;
import com.sunny.rpncalc.cli.command.impl.UndoCommand;
import com.sunny.rpncalc.cli.command.invoker.CommandInvoker;
import com.sunny.rpncalc.cli.command.invoker.factory.CommandInvokerFactory;
import com.sunny.rpncalc.cli.controller.lookup.OperationLookup;
import com.sunny.rpncalc.cli.controller.util.RPNCalcUtil;
import com.sunny.rpncalc.cli.exception.RPNCalcError;
import com.sunny.rpncalc.cli.exception.RPNCalculatorException;

import java.math.BigInteger;
import java.util.Stack;

/**
 * Loose singleton
 *
 * Created by sundas on 4/15/2017.
 */
public class CLIController {


  /*
  Maintains history of commands for undo
   */
  private Stack<RPNCalculatorCommand> undoStack;

  /**
   * Reuse intance not strictly singleton
   */
  private static CLIController cliController;



  /**
   *
   */
  private CLIController(){
    this.undoStack = new Stack<>();
  }


  /**
   *
   * @return
   */
  public static CLIController getCLIController(){
    if(cliController == null){
      cliController = new CLIController();
    }
    return cliController;
  }

  /**
   * Perform work for the row
   *
   * @param row
   */
  public void processRow(String row)throws RPNCalculatorException{
    if(row != null){
      String[] tokens = row.trim().split("\\s+");
      CommandInvoker commandInvoker = CommandInvokerFactory.getDefaultInvoker();
      String token = null;
      int i = 0;
      try {
        for(;i < tokens.length ; i++){
            token = tokens[i];
            if (RPNCalcUtil.isNumeric(token)) {
              RPNCalculatorCommand<String> storeCommand = new StoreCommand();
              storeCommand.setState(token.trim());
              commandInvoker.setCommand(storeCommand);
              commandInvoker.executeCommand();
              undoStack.push(storeCommand);
            } else {
              RPNCalculatorCommand rpnCalculatorCommand = OperationLookup.getOperation(token.toUpperCase());
              //Undo command handling
              if(rpnCalculatorCommand instanceof UndoCommand){
                if(!undoStack.isEmpty()){
                  rpnCalculatorCommand = undoStack.pop();
                  rpnCalculatorCommand.undo();
                }
              }
              else {
                commandInvoker.setCommand(rpnCalculatorCommand);
                commandInvoker.executeCommand();
                undoStack.push(rpnCalculatorCommand);
              }
            }

        }
        //Print state
        RPNCalculatorCommand rpnCalculatorCommand = new PrintStateCommand();
        rpnCalculatorCommand.execute();
      } catch(RPNCalculatorException e){
        if(e.getStatusCode().equals(RPNCalcError.INSUFFICIENT_PARAMETERS)){
          System.out.println("operator " + token + " (position: " + RPNCalcUtil.findPositionOfOperand(row,i) + "): insufficient parameters");
          //Print state
          RPNCalculatorCommand rpnCalculatorCommand = new PrintStateCommand();
          rpnCalculatorCommand.execute();
        }
      }
    }
  }




}

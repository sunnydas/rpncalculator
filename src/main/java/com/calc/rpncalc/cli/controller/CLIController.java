package com.calc.rpncalc.cli.controller;

import com.calc.rpncalc.cli.command.RPNCalculatorCommand;
import com.calc.rpncalc.cli.command.impl.PrintStateCommand;
import com.calc.rpncalc.cli.command.impl.StoreCommand;
import com.calc.rpncalc.cli.command.invoker.CommandInvoker;
import com.calc.rpncalc.cli.command.invoker.factory.CommandInvokerFactory;
import com.calc.rpncalc.cli.controller.lookup.OperationLookup;
import com.calc.rpncalc.cli.controller.util.RPNCalcUtil;
import com.calc.rpncalc.cli.exception.RPNCalcError;
import com.calc.rpncalc.cli.exception.RPNCalculatorException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.Stack;

/**
 * Loose singleton
 *
 * Created by sundas on 4/15/2017.
 */
public class CLIController {


  private Logger logger = Logger.getLogger(CLIController.class);

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
              RPNCalculatorCommand rpnCalculatorCommand = null;
              if(token.toUpperCase().equals("UNDO")){
                if(!undoStack.isEmpty()){
                  rpnCalculatorCommand = undoStack.pop();
                  rpnCalculatorCommand.undo();
                }
              }
              else {
                rpnCalculatorCommand = OperationLookup.getOperation(token.toUpperCase());
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
        if(e.getStatusCode().equals(RPNCalcError.INSUFFICIENT_PARAMETERS)) {
          logger.warn("operator " + token + " (position: " + RPNCalcUtil.findPositionOfOperand(row, i) + "): insufficient parameters");
          //Print state
          RPNCalculatorCommand rpnCalculatorCommand = new PrintStateCommand();
          rpnCalculatorCommand.execute();
        }
      }
    }
  }




}

package com.calc.rpncalc.cli;

import com.calc.rpncalc.cli.controller.CLIController;
import com.calc.rpncalc.cli.controller.util.RPNCalcUtil;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.util.Scanner;

/**
 * Created by sundas on 4/15/2017.
 */
public class RPNCalculator {

  private static Logger logger = Logger.getLogger(RPNCalculator.class);


  /**
   *
   */
  private RPNCalculator(){

  }


  /**
   * Application entry point
   *
   */
  public static void runCLI(){
    Scanner commandScanner = new Scanner(System.in);
    try {
      while (commandScanner.hasNextLine()) {
        String input = commandScanner.nextLine();
        CLIController.getCLIController().processRow(input);
      }
    } catch (Exception e) {
      logger.error("Error : during claculator execution = " + e.getMessage());
    } finally{
       commandScanner.close();
    }
  }


  /**
   *
   * @param args
   */
  public static void main(String[] args) {
    runCLI();
  }


}

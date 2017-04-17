package com.sunny.rpncalc.cli;

import com.sunny.rpncalc.cli.controller.CLIController;

import java.util.Scanner;

/**
 * Created by sundas on 4/15/2017.
 */
public class RPNCalculator {




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
    } catch (Exception e){
      System.err.println("Error : during claculator execution = " + e.getMessage());
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

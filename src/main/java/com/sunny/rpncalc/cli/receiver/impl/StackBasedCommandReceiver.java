package com.sunny.rpncalc.cli.receiver.impl;


import com.sunny.rpncalc.cli.receiver.CommandReceiver;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Created by sundas on 4/16/2017.
 */
public class StackBasedCommandReceiver implements CommandReceiver<BigDecimal,Stack>{


  @Override
  public String toString() {
    StringBuilder contents = new StringBuilder("stack: ");
    int size = this.operationStack.size();
    for(int i = 0 ; i < size ; i++){
      contents.append(this.operationStack.get(i).setScale(10,BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
      contents.append(" ");
    }
    return contents.toString();
  }

  private Stack<BigDecimal> operationStack;


  private static StackBasedCommandReceiver stackBasedCommandReceiver;

  /**
   *
   */
   private StackBasedCommandReceiver(){
     operationStack = new Stack<>();
   }

  /**
   *
   * @return
   */
   public static StackBasedCommandReceiver getReceiver(){
     if(stackBasedCommandReceiver == null){
       stackBasedCommandReceiver = new StackBasedCommandReceiver();
     }
     return stackBasedCommandReceiver;
   }


   public void addElement(BigDecimal element){
     this.operationStack.push(element);
   }

   public BigDecimal fetchElement(){
     BigDecimal element = null;
     if(!this.operationStack.isEmpty()){
       element = this.operationStack.pop();
     }
     return element;
   }

  @Override
  public void clearUnderlyingState() {
    this.operationStack.clear();
  }

  @Override
  public void printState() {
    System.out.println(this.toString());
  }

  @Override
  public int getInternaDSSize() {
    return this.operationStack.size();
  }

}
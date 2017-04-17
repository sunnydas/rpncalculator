package com.sunny.rpncalc.cli.receiver;

/**
 * Created by sundas on 4/16/2017.
 */
public interface CommandReceiver<E,T> {

   public void addElement(E element);

   public E fetchElement();

   public void clearUnderlyingState();

   public void printState();

   public int getInternaDSSize();

}

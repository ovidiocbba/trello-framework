package com.ovidiomiranda.framework.hooks;

/**
 * Class that contains the order of execution of the hooks.
 *
 * @author Ovidio Miranda
 */
public class HookOrder {

  // [OM] Order of execution for the '@Before' hooks
  // This runs in increment order, means value 0 would run first and 1 would be after 0.
  public static final int CREATE_WORKSPACE = 1;
  public static final int CREATE_BOARD = 2;
  public static final int CREATE_LIST = 3;

  // [OM] Order of execution for the '@After' hooks.
  // This runs in decrements order, means apposite of @Before.
  public static final int SCREENSHOT = 99;
  public static final int DELETE_BOARD = 20;
  public static final int DELETE_WORKSPACE = 10;
  public static final int DISPOSE_ALL_ACTIVE_WINDOWS = 1;
}

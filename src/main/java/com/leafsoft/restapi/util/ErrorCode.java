/* $Id : $*/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leafsoft.restapi.util;

/**
 *
 * @author udhaya-zu282
 */
public enum ErrorCode {
  
  URI(3000, "Invalid URI"),//No I18N
  METHOD(3001, "Method Not Supported"),//No I18N
  TYPE(3002, "Content-Type Mismatch"),//No I18N
  INVALID_PROFILEID(3003,"Invalid ProfileID"),//No I18N
  INVALID_PARAM(3004,"Invalid Params"),//No I18N
  PARSING_JSON(3005,"Invalid JSON Format"),//No I18N
  JSON_SIZE(3006,"Invalid Number of Params in JSONString"),//No I18N
  JSON_EMPTY(3007,"Params in JSONString may be null or empty"),//No I18N
  INVALID_ACCESS(3008,"Unauthorized access, Please check your data."),//No I18N
  UNHANDLEDSWITCH(3009,"can't handle this switch type"),//NO I18N
  INTERNAL_ERROR(3010,"Internal Error");//NO I18N

  private final int id;
  private final String message;

  private ErrorCode(int id, String message) {
     this.id = id;
     this.message = message;
  }

  public int getId() { return id; }
  public String getMessage() { return message; }
}

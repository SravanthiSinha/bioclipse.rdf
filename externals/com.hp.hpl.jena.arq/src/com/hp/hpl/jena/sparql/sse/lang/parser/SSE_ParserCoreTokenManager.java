/* Generated By:JavaCC: Do not edit this line. SSE_ParserCoreTokenManager.java */
/*
 * (c) Copyright 2007 Hewlett-Packard Development Company, LP
 * All rights reserved.
 */

package com.hp.hpl.jena.sparql.sse.lang.parser ;


/** Token Manager. */
public class SSE_ParserCoreTokenManager implements SSE_ParserCoreConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x1800000L) != 0L)
            return 65;
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 40:
         return jjStopAtPos(0, 21);
      case 41:
         return jjStopAtPos(0, 22);
      case 91:
         return jjStopAtPos(0, 25);
      case 93:
         return jjStopAtPos(0, 26);
      case 94:
         return jjMoveStringLiteralDfa1_0(0x8000000L);
      case 123:
         return jjStartNfaWithStates_0(0, 23, 65);
      case 125:
         return jjStartNfaWithStates_0(0, 24, 65);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 94:
         if ((active0 & 0x8000000L) != 0L)
            return jjStopAtPos(1, 27);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec2 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec3 = {
   0xfffe7000fffffff6L, 0xffffffffffffffffL, 0xffffffffffffffffL, 0x7e00000000ffffffL
};
static final long[] jjbitVec4 = {
   0x0L, 0x0L, 0x0L, 0xff7fffffff7fffffL
};
static final long[] jjbitVec5 = {
   0x0L, 0xbfff000000000000L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec6 = {
   0x3000L, 0xffff000000000000L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec7 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffL, 0x0L
};
static final long[] jjbitVec8 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffL
};
static final long[] jjbitVec9 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0x3fffffffffffffffL
};
static final long[] jjbitVec10 = {
   0x0L, 0x0L, 0x80000000000000L, 0xff7fffffff7fffffL
};
static final long[] jjbitVec11 = {
   0xffffffffffffffffL, 0xbfffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec12 = {
   0x8000000000003000L, 0xffff000000000001L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 93;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xfffffc7affffc9ffL & l) != 0L)
                  {
                     if (kind > 41)
                        kind = 41;
                     jjCheckNAdd(65);
                  }
                  else if ((0x100003600L & l) != 0L)
                  {
                     if (kind > 1)
                        kind = 1;
                  }
                  else if (curChar == 34)
                     jjstateSet[jjnewStateCnt++] = 58;
                  else if (curChar == 39)
                     jjstateSet[jjnewStateCnt++] = 47;
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 10)
                        kind = 10;
                     jjCheckNAddStates(0, 6);
                  }
                  else if ((0x280000000000L & l) != 0L)
                     jjCheckNAddStates(7, 10);
                  else if (curChar == 46)
                     jjCheckNAddTwoStates(76, 89);
                  else if (curChar == 34)
                     jjCheckNAddStates(11, 13);
                  else if (curChar == 39)
                     jjCheckNAddStates(14, 16);
                  else if (curChar == 58)
                  {
                     if (kind > 5)
                        kind = 5;
                     jjstateSet[jjnewStateCnt++] = 18;
                  }
                  else if (curChar == 60)
                     jjCheckNAddTwoStates(12, 13);
                  else if (curChar == 59)
                  {
                     if (kind > 3)
                        kind = 3;
                     jjCheckNAddStates(17, 19);
                  }
                  else if (curChar == 35)
                  {
                     if (kind > 2)
                        kind = 2;
                     jjCheckNAddStates(20, 22);
                  }
                  else if (curChar == 63)
                  {
                     if (kind > 7)
                        kind = 7;
                     jjCheckNAddTwoStates(67, 69);
                  }
                  break;
               case 1:
                  if (curChar != 35)
                     break;
                  if (kind > 2)
                     kind = 2;
                  jjCheckNAddStates(20, 22);
                  break;
               case 2:
                  if ((0xffffffffffffdbffL & l) == 0L)
                     break;
                  if (kind > 2)
                     kind = 2;
                  jjCheckNAddStates(20, 22);
                  break;
               case 3:
                  if ((0x2400L & l) != 0L && kind > 2)
                     kind = 2;
                  break;
               case 4:
                  if (curChar == 10 && kind > 2)
                     kind = 2;
                  break;
               case 5:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 4;
                  break;
               case 6:
                  if (curChar != 59)
                     break;
                  if (kind > 3)
                     kind = 3;
                  jjCheckNAddStates(17, 19);
                  break;
               case 7:
                  if ((0xffffffffffffdbffL & l) == 0L)
                     break;
                  if (kind > 3)
                     kind = 3;
                  jjCheckNAddStates(17, 19);
                  break;
               case 8:
                  if ((0x2400L & l) != 0L && kind > 3)
                     kind = 3;
                  break;
               case 9:
                  if (curChar == 10 && kind > 3)
                     kind = 3;
                  break;
               case 10:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 9;
                  break;
               case 11:
                  if (curChar == 60)
                     jjCheckNAddTwoStates(12, 13);
                  break;
               case 12:
                  if ((0xaffffffe00000000L & l) != 0L)
                     jjCheckNAddTwoStates(12, 13);
                  break;
               case 13:
                  if (curChar == 62 && kind > 4)
                     kind = 4;
                  break;
               case 15:
                  if ((0x3ff600000000000L & l) != 0L)
                     jjAddStates(23, 24);
                  break;
               case 16:
                  if ((0x3ff200000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 17:
                  if (curChar != 58)
                     break;
                  if (kind > 5)
                     kind = 5;
                  jjstateSet[jjnewStateCnt++] = 18;
                  break;
               case 18:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 5)
                     kind = 5;
                  jjCheckNAddTwoStates(19, 20);
                  break;
               case 19:
                  if ((0x3ff600000000000L & l) != 0L)
                     jjCheckNAddTwoStates(19, 20);
                  break;
               case 20:
                  if ((0x3ff200000000000L & l) != 0L && kind > 5)
                     kind = 5;
                  break;
               case 21:
                  if (curChar != 58)
                     break;
                  if (kind > 6)
                     kind = 6;
                  jjstateSet[jjnewStateCnt++] = 22;
                  break;
               case 22:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 6)
                     kind = 6;
                  jjCheckNAddTwoStates(23, 24);
                  break;
               case 23:
                  if ((0x3ff600000000000L & l) != 0L)
                     jjCheckNAddTwoStates(23, 24);
                  break;
               case 24:
                  if ((0x3ff200000000000L & l) != 0L && kind > 6)
                     kind = 6;
                  break;
               case 27:
                  if ((0x8400000000L & l) != 0L && kind > 16)
                     kind = 16;
                  break;
               case 28:
                  if (curChar == 39)
                     jjCheckNAddStates(14, 16);
                  break;
               case 29:
                  if ((0xffffff7fffffdbffL & l) != 0L)
                     jjCheckNAddStates(14, 16);
                  break;
               case 31:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAddStates(14, 16);
                  break;
               case 32:
                  if (curChar == 39 && kind > 17)
                     kind = 17;
                  break;
               case 33:
                  if (curChar == 34)
                     jjCheckNAddStates(11, 13);
                  break;
               case 34:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     jjCheckNAddStates(11, 13);
                  break;
               case 36:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAddStates(11, 13);
                  break;
               case 37:
                  if (curChar == 34 && kind > 18)
                     kind = 18;
                  break;
               case 38:
                  if (curChar == 39)
                     jjCheckNAddStates(25, 28);
                  break;
               case 39:
               case 44:
                  if (curChar == 39)
                     jjCheckNAddTwoStates(40, 41);
                  break;
               case 40:
                  if ((0xffffff7fffffffffL & l) != 0L)
                     jjCheckNAddStates(25, 28);
                  break;
               case 42:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAddStates(25, 28);
                  break;
               case 43:
                  if (curChar == 39)
                     jjAddStates(29, 30);
                  break;
               case 45:
                  if (curChar == 39 && kind > 19)
                     kind = 19;
                  break;
               case 46:
                  if (curChar == 39)
                     jjstateSet[jjnewStateCnt++] = 45;
                  break;
               case 47:
                  if (curChar == 39)
                     jjstateSet[jjnewStateCnt++] = 38;
                  break;
               case 48:
                  if (curChar == 39)
                     jjstateSet[jjnewStateCnt++] = 47;
                  break;
               case 49:
                  if (curChar == 34)
                     jjCheckNAddStates(31, 34);
                  break;
               case 50:
               case 55:
                  if (curChar == 34)
                     jjCheckNAddTwoStates(51, 52);
                  break;
               case 51:
                  if ((0xfffffffbffffffffL & l) != 0L)
                     jjCheckNAddStates(31, 34);
                  break;
               case 53:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAddStates(31, 34);
                  break;
               case 54:
                  if (curChar == 34)
                     jjAddStates(35, 36);
                  break;
               case 56:
                  if (curChar == 34 && kind > 20)
                     kind = 20;
                  break;
               case 57:
                  if (curChar == 34)
                     jjstateSet[jjnewStateCnt++] = 56;
                  break;
               case 58:
                  if (curChar == 34)
                     jjstateSet[jjnewStateCnt++] = 49;
                  break;
               case 59:
                  if (curChar == 34)
                     jjstateSet[jjnewStateCnt++] = 58;
                  break;
               case 62:
                  if (curChar == 45)
                     jjCheckNAdd(63);
                  break;
               case 63:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 28)
                     kind = 28;
                  jjCheckNAddTwoStates(62, 63);
                  break;
               case 64:
               case 65:
                  if ((0xfffffc7affffc9ffL & l) == 0L)
                     break;
                  if (kind > 41)
                     kind = 41;
                  jjCheckNAdd(65);
                  break;
               case 66:
                  if (curChar != 63)
                     break;
                  if (kind > 7)
                     kind = 7;
                  jjCheckNAddTwoStates(67, 69);
                  break;
               case 67:
               case 68:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 7)
                     kind = 7;
                  jjCheckNAdd(68);
                  break;
               case 69:
                  if ((0xfffffc7affffc9ffL & l) == 0L)
                     break;
                  if (kind > 8)
                     kind = 8;
                  jjCheckNAdd(69);
                  break;
               case 70:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAddStates(7, 10);
                  break;
               case 71:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 10)
                     kind = 10;
                  jjCheckNAdd(71);
                  break;
               case 72:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(72, 73);
                  break;
               case 73:
                  if (curChar != 46)
                     break;
                  if (kind > 11)
                     kind = 11;
                  jjCheckNAdd(74);
                  break;
               case 74:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 11)
                     kind = 11;
                  jjCheckNAdd(74);
                  break;
               case 75:
                  if (curChar == 46)
                     jjCheckNAdd(76);
                  break;
               case 76:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 11)
                     kind = 11;
                  jjCheckNAdd(76);
                  break;
               case 77:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(77, 78);
                  break;
               case 78:
                  if (curChar == 46)
                     jjCheckNAddTwoStates(79, 80);
                  break;
               case 79:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(79, 80);
                  break;
               case 81:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(82);
                  break;
               case 82:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(82);
                  break;
               case 83:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 10)
                     kind = 10;
                  jjCheckNAddStates(0, 6);
                  break;
               case 84:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(84, 85);
                  break;
               case 86:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(87);
                  break;
               case 87:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(87);
                  break;
               case 88:
                  if (curChar == 46)
                     jjCheckNAddTwoStates(76, 89);
                  break;
               case 89:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(89, 90);
                  break;
               case 91:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(92);
                  break;
               case 92:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 12)
                     kind = 12;
                  jjCheckNAdd(92);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xffffffff97fffffeL & l) != 0L)
                  {
                     if (kind > 41)
                        kind = 41;
                     jjCheckNAdd(65);
                  }
                  else if (curChar == 64)
                     jjCheckNAdd(61);
                  if ((0x7fffffe07fffffeL & l) != 0L)
                     jjCheckNAddStates(37, 39);
                  else if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 27;
                  else if (curChar == 95)
                     jjstateSet[jjnewStateCnt++] = 21;
                  break;
               case 2:
                  if (kind > 2)
                     kind = 2;
                  jjAddStates(20, 22);
                  break;
               case 7:
                  if (kind > 3)
                     kind = 3;
                  jjAddStates(17, 19);
                  break;
               case 12:
                  jjAddStates(40, 41);
                  break;
               case 14:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                     jjCheckNAddStates(37, 39);
                  break;
               case 15:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAddTwoStates(15, 16);
                  break;
               case 16:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAdd(17);
                  break;
               case 18:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 5)
                     kind = 5;
                  jjCheckNAddTwoStates(19, 20);
                  break;
               case 19:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAddTwoStates(19, 20);
                  break;
               case 20:
                  if ((0x7fffffe87fffffeL & l) != 0L && kind > 5)
                     kind = 5;
                  break;
               case 22:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 6)
                     kind = 6;
                  jjCheckNAddTwoStates(23, 24);
                  break;
               case 23:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAddTwoStates(23, 24);
                  break;
               case 24:
                  if ((0x7fffffe87fffffeL & l) != 0L && kind > 6)
                     kind = 6;
                  break;
               case 25:
                  if (curChar == 95)
                     jjstateSet[jjnewStateCnt++] = 21;
                  break;
               case 26:
                  if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 27;
                  break;
               case 27:
                  if ((0x14404410000000L & l) != 0L && kind > 16)
                     kind = 16;
                  break;
               case 29:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStates(14, 16);
                  break;
               case 30:
                  if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 31;
                  break;
               case 31:
                  if ((0x14404410000000L & l) != 0L)
                     jjCheckNAddStates(14, 16);
                  break;
               case 34:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStates(11, 13);
                  break;
               case 35:
                  if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 36;
                  break;
               case 36:
                  if ((0x14404410000000L & l) != 0L)
                     jjCheckNAddStates(11, 13);
                  break;
               case 40:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStates(25, 28);
                  break;
               case 41:
                  if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 42;
                  break;
               case 42:
                  if ((0x14404410000000L & l) != 0L)
                     jjCheckNAddStates(25, 28);
                  break;
               case 51:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStates(31, 34);
                  break;
               case 52:
                  if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 53;
                  break;
               case 53:
                  if ((0x14404410000000L & l) != 0L)
                     jjCheckNAddStates(31, 34);
                  break;
               case 60:
                  if (curChar == 64)
                     jjCheckNAdd(61);
                  break;
               case 61:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 28)
                     kind = 28;
                  jjCheckNAddTwoStates(61, 62);
                  break;
               case 63:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 28)
                     kind = 28;
                  jjCheckNAddTwoStates(62, 63);
                  break;
               case 64:
                  if ((0xffffffff97fffffeL & l) == 0L)
                     break;
                  if (kind > 41)
                     kind = 41;
                  jjCheckNAdd(65);
                  break;
               case 65:
                  if ((0xffffffffd7ffffffL & l) == 0L)
                     break;
                  if (kind > 41)
                     kind = 41;
                  jjCheckNAdd(65);
                  break;
               case 67:
               case 68:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 7)
                     kind = 7;
                  jjCheckNAdd(68);
                  break;
               case 69:
                  if ((0xffffffffd7ffffffL & l) == 0L)
                     break;
                  if (kind > 8)
                     kind = 8;
                  jjstateSet[jjnewStateCnt++] = 69;
                  break;
               case 80:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(42, 43);
                  break;
               case 85:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(44, 45);
                  break;
               case 90:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(46, 47);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (jjCanMove_1(hiByte, i1, i2, l1, l2))
                     jjCheckNAddStates(37, 39);
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                  {
                     if (kind > 41)
                        kind = 41;
                     jjCheckNAdd(65);
                  }
                  break;
               case 2:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 2)
                     kind = 2;
                  jjAddStates(20, 22);
                  break;
               case 7:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 3)
                     kind = 3;
                  jjAddStates(17, 19);
                  break;
               case 12:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(40, 41);
                  break;
               case 14:
                  if (jjCanMove_1(hiByte, i1, i2, l1, l2))
                     jjCheckNAddStates(37, 39);
                  break;
               case 15:
                  if (jjCanMove_2(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(15, 16);
                  break;
               case 16:
                  if (jjCanMove_2(hiByte, i1, i2, l1, l2))
                     jjCheckNAdd(17);
                  break;
               case 18:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 5)
                     kind = 5;
                  jjCheckNAddTwoStates(19, 20);
                  break;
               case 19:
                  if (jjCanMove_2(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(19, 20);
                  break;
               case 20:
                  if (jjCanMove_2(hiByte, i1, i2, l1, l2) && kind > 5)
                     kind = 5;
                  break;
               case 22:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 6)
                     kind = 6;
                  jjCheckNAddTwoStates(23, 24);
                  break;
               case 23:
                  if (jjCanMove_2(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(23, 24);
                  break;
               case 24:
                  if (jjCanMove_2(hiByte, i1, i2, l1, l2) && kind > 6)
                     kind = 6;
                  break;
               case 29:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(14, 16);
                  break;
               case 34:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(11, 13);
                  break;
               case 40:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(25, 28);
                  break;
               case 51:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(31, 34);
                  break;
               case 64:
               case 65:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 41)
                     kind = 41;
                  jjCheckNAdd(65);
                  break;
               case 67:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 7)
                     kind = 7;
                  jjCheckNAdd(68);
                  break;
               case 68:
                  if (!jjCanMove_2(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 7)
                     kind = 7;
                  jjCheckNAdd(68);
                  break;
               case 69:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 8)
                     kind = 8;
                  jjstateSet[jjnewStateCnt++] = 69;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 93 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   71, 72, 73, 77, 78, 84, 85, 71, 72, 75, 77, 34, 35, 37, 29, 30, 
   32, 7, 8, 10, 2, 3, 5, 15, 16, 39, 40, 41, 43, 44, 46, 50, 
   51, 52, 54, 55, 57, 15, 16, 17, 12, 13, 81, 82, 86, 87, 91, 92, 
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      default :
         if ((jjbitVec0[i1] & l1) != 0L)
            return true;
         return false;
   }
}
private static final boolean jjCanMove_1(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec4[i2] & l2) != 0L);
      case 3:
         return ((jjbitVec5[i2] & l2) != 0L);
      case 32:
         return ((jjbitVec6[i2] & l2) != 0L);
      case 33:
         return ((jjbitVec7[i2] & l2) != 0L);
      case 47:
         return ((jjbitVec8[i2] & l2) != 0L);
      case 48:
         return ((jjbitVec0[i2] & l2) != 0L);
      case 255:
         return ((jjbitVec9[i2] & l2) != 0L);
      default :
         if ((jjbitVec3[i1] & l1) != 0L)
            return true;
         return false;
   }
}
private static final boolean jjCanMove_2(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec10[i2] & l2) != 0L);
      case 3:
         return ((jjbitVec11[i2] & l2) != 0L);
      case 32:
         return ((jjbitVec12[i2] & l2) != 0L);
      case 33:
         return ((jjbitVec7[i2] & l2) != 0L);
      case 47:
         return ((jjbitVec8[i2] & l2) != 0L);
      case 48:
         return ((jjbitVec0[i2] & l2) != 0L);
      case 255:
         return ((jjbitVec9[i2] & l2) != 0L);
      default :
         if ((jjbitVec3[i1] & l1) != 0L)
            return true;
         return false;
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, "\50", "\51", "\173", "\175", 
"\133", "\135", "\136\136", null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x2001fff1df3L, 
};
static final long[] jjtoSkip = {
   0xcL, 
};
static final long[] jjtoSpecial = {
   0xcL, 
};
protected JavaCharStream input_stream;
private final int[] jjrounds = new int[93];
private final int[] jjstateSet = new int[186];
protected char curChar;
/** Constructor. */
public SSE_ParserCoreTokenManager(JavaCharStream stream){
   if (JavaCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public SSE_ParserCoreTokenManager(JavaCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(JavaCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 93; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(JavaCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token specialToken = null;
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      matchedToken.specialToken = specialToken;
      return matchedToken;
   }

   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         matchedToken.specialToken = specialToken;
         return matchedToken;
      }
      else
      {
         if ((jjtoSpecial[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
         {
            matchedToken = jjFillToken();
            if (specialToken == null)
               specialToken = matchedToken;
            else
            {
               matchedToken.specialToken = specialToken;
               specialToken = (specialToken.next = matchedToken);
            }
         }
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}

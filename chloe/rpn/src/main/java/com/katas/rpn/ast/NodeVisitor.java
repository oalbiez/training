/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.katas.rpn.ast;

/**
 *
 * @author Chloé Mahalin
 */
public interface NodeVisitor {

    public void visitBinaryOperation(BinaryOperation aThis);


    public void visitLiteral(Literal aThis);


    public void visitUnaryOperation(UnaryOperation aThis);

}

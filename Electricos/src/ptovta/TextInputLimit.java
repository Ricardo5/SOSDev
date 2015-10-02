/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ptovta;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author augus
 */
public class TextInputLimit extends PlainDocument{
    private int limit;
    
    public TextInputLimit(int length){
        super();
        limit = length;
    }
    
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException{
        int len = 0;
        if(str == null) return;
        
        if((this.getLength() + str.length()) <= limit){
            super.insertString(offset, str, attr);
        }else{
            len = limit - this.getLength();
            str = str.substring(0, len);
            super.insertString(offset,str,attr);
        }
    }
    
}

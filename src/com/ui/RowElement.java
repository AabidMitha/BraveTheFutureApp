package com.ui;

import com.main.Vector;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;

public class RowElement implements IListContainer{

    private ArrayList<IElement> elements;
    private double seperation;
    
    public RowElement(){
        elements = new ArrayList<>();
    }
    
    public RowElement addElement(IElement e){
        elements.add(e);
        return this;
    }
    public RowElement setSeperation(double s){
        seperation = s;
        return this;
    }
    
    public Vector getSize() {
        Vector size = new Vector();
        for(IElement e : elements){
            if(e.getSize().y > size.y){ size.y = e.getSize().y; }
            size.x += e.getSize().x;
        }
        size.x += seperation * (elements.size()-1);
        return size;
    }

    public void render(Graphics2D g) {
        AffineTransform save = g.getTransform();
        for(IElement e : elements){
            e.render(g);
            g.translate(e.getSize().x+seperation, 0);
        }
        g.setTransform(save);
    }
     
    public int getElementCount() {
        return elements.size();
    }

    public Iterator<IElement> getElements() {
        return elements.iterator();
    }

    public void applyTrasform(AffineTransform at, int index) {
        for(int i = 0; i < index; i++){
            at.translate(elements.get(i).getSize().x + seperation, 0);
        }
    }
}
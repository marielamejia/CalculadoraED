/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pilas;

/**
 *
 * @author Pablo
 */
public interface PilaADT <T> {
    
    public void push(T dato);
    public T pop();
    public boolean isEmpty();
    public T peek();
    public T[] multiPop(int n);
    
}

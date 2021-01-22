/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Utilities.Interfaces;

/**
 *
 * @author Chahir Chalouati
 * @param <A>
 * @param <B>
 * @param <C>
 */
public interface CrudService<A, B, C> {

    C create(A a);

    C delete(B b);

    C update(A a, B b);
}

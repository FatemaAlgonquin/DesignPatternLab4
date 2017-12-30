/* File: ValidationException.java
 * Author: fatema
 * Date: 2017
 * Description: Demonstration of DAO Design Pattern with Servlet website
 */
package business;

public class ValidationException_1 extends Exception {

    public ValidationException_1() {
        super("Data not in valid format");
    }

    public ValidationException_1(String message) {
        super(message);
    }

    public ValidationException_1(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ValidationException_1(Throwable throwable) {
        super(throwable);
    }
}

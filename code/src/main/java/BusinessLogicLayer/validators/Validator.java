package BusinessLogicLayer.validators;

/**
 * Created by Boros on 3/27/2018.
 */
public interface Validator<T> {

    void validate(T t);
}

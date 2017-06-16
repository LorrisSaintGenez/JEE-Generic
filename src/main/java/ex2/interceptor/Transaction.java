package ex2.interceptor;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Lorris on 16/06/2017.
 */

@Retention(RetentionPolicy.RUNTIME)
@InterceptorBinding
@Inherited
public @interface Transaction {
}

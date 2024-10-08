package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class UncheckedTest {

    @Test
    void unchecked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void unchecked_throw() {
        Service service = new Service();
        service.callThrow();
        assertThatThrownBy(() -> service.callThrow()).isInstanceOf(MyUncheckedException.class);
    }

    /**
     * RuntimeException을 상속받은 예외는 언체크 예외가 된다
     */
    static class MyUncheckedException extends RuntimeException {

        public MyUncheckedException(String messsage) {
            super(messsage);
        }
    }

    /**
     * Unchecked 예외는 예외를 잡거나 던지지 않아도 된다. 예외를 잡지 않으면 자동으로 밖으로 던진다.
     */
    static class Service {
        Respository respository = new Respository();

        /**
         * 필요한 경우 예외를 잡아서 처리하면 된다.
         */
        public void callCatch() {
            try {
                respository.call();
            } catch (MyUncheckedException e) {
                //예외 처리 로직
                log.info("예외 처리, message={}", e.getMessage(), e);
            }

        }

        /**
         * 예외를 잡지 않아도 되낟. 자연스럽게 상위로 넘어간다.
         * 체크 예외와 다르게 throws 예외 선언을 하지 않아도 된다.
         */
        public void callThrow() {
            respository.call();
        }
    }

    static class Respository {
        public void call() { //언체크 예외는 throws를 생략할 수 있다.
            throw new MyUncheckedException("ex");
        }
    }


}

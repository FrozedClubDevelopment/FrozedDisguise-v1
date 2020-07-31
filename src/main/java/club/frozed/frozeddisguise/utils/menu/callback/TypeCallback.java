package club.frozed.frozeddisguise.utils.menu.callback;

import java.io.Serializable;

public interface TypeCallback<T> extends Serializable {

    void callback(T data);

}

package co.edureka.edurekasession2;

import java.io.Serializable;

/**
 * Created by ishantkumar on 20/01/18.
 */

// Serialization
public class User implements Serializable{

    String name;
    String age;

    boolean validateUser(){
        boolean flag = true;

        if(name.isEmpty()){
            flag = false;
        }

        if(age.isEmpty()){
            flag = false;
        }

        return flag;
    }

}

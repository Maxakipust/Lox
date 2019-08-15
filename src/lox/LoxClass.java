package lox;

import java.util.List;
import java.util.Map;

public class LoxClass implements LoxCallable{
    final String name;
    private final Map<String, LoxFunction> methods;
    final LoxClass superclass;

    LoxClass(String name, LoxClass superclass, Map<String, LoxFunction> methods){
        this.superclass = superclass;
        this.name = name;
        this.methods = methods;
    }

    LoxFunction findMethod(String name) {
        if(methods.containsKey(name)){
            return methods.get(name);
        }

        if(superclass != null){
            return superclass.findMethod(name);
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        LoxInstance instance = new LoxInstance(this);
        LoxFunction initalizer = findMethod("init");
        if(initalizer != null){
            initalizer.bind(instance).call(interpreter, arguments);
        }

        return instance;
    }

    @Override
    public int arity() {
        LoxFunction initalizer = findMethod("init");
        if(initalizer == null){
            return 0;
        }
        return initalizer.arity();
    }
}

package me.tianshuang.agent;

import java.net.URL;
import java.net.URLClassLoader;

public class AgentClassLoader extends URLClassLoader {

    static {
        ClassLoader.registerAsParallelCapable();
    }

    public AgentClassLoader(URL[] urls) {
        // parent: Bootstrap ClassLoader
        super(urls, null);
    }

}

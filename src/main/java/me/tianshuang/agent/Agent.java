package me.tianshuang.agent;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;


@Log4j2
public class Agent {

    public static void premain(String agentArgs) {
        try {
            ClassLoader agentClassLoader = new AgentClassLoader(new URL[]{getJavaAgentFile().toURL()});
            Class<?> agentClass = agentClassLoader.loadClass("me.tianshuang.agent.Agent");
            Method agentStartListenerMethod = agentClass.getMethod("startAgent");

            agentStartListenerMethod.invoke(null);
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | MalformedURLException e) {
            log.error("Agent exception: " + e.getMessage(), e);
        }
    }

    private static File getJavaAgentFile() {
        try {
            CodeSource codeSource = Agent.class.getProtectionDomain().getCodeSource();
            if (codeSource != null) {
                File javaagentFile = new File(codeSource.getLocation().toURI());
                if (javaagentFile.isFile()) {
                    return javaagentFile;
                }
            }

            throw new RuntimeException("Cannot get java agent file");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void startAgent() {
        new Thread(() -> {
            while (true) {
                log.info("I'm agent test thread...");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    // ignore
                }
            }
        }, "agent-test-thread").start();
    }

}
